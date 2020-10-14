package io.github.codingzhengsz.aop.aspect;

import io.github.codingzhengsz.aop.Process;
import io.github.codingzhengsz.processor.AbstractProcessor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class ProcessAspect implements ApplicationContextAware {

  private static final Logger logger = LoggerFactory.getLogger(ProcessAspect.class);

  private ApplicationContext applicationContext;

  @Around("@annotation(io.github.codingzhengsz.aop.Process)")
  public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
    logger.info("[ProcessAspect] doAround");
    List<AbstractProcessor<?>> processors = this.collectAbstractProcessor(proceedingJoinPoint);
    return handleMultiOrderedPostMonitors(proceedingJoinPoint, processors);
  }

  private List<AbstractProcessor<?>> collectAbstractProcessor(ProceedingJoinPoint joinPoint) {
    List<AbstractProcessor<?>> processors = new ArrayList<>();

    Annotation[] annotations =
        ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotations();

    for (Annotation annotation : annotations) {
      Process process =
          annotation instanceof Process
              ? (Process) annotation
              : annotation.annotationType().getAnnotation(Process.class);
      if (null != process) {
        AbstractProcessor<?> tempMonitor = this.getProcessor(process);
        List<AbstractProcessor<?>> tempMonitors = this.getProcessors(process);
        if (!CollectionUtils.isEmpty(tempMonitors)) {
          processors.addAll(tempMonitors);
        } else {
          processors.add(tempMonitor);
        }
      }
    }
    return processors;
  }

  private AbstractProcessor<?> getProcessor(Process processAnnotation) {
    return this.applicationContext.getBean(processAnnotation.processor());
  }

  private List<AbstractProcessor<?>> getProcessors(Process processAnnotation) {
    return Arrays.stream(processAnnotation.processors())
        .map(clazz -> (AbstractProcessor<?>) this.applicationContext.getBean(clazz))
        .collect(Collectors.toList());
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  private Object handleMultiOrderedPostMonitors(
      ProceedingJoinPoint joinPoint, List<AbstractProcessor<?>> processors) {
    Object result;
    try {
      result = joinPoint.proceed();
      for (AbstractProcessor<?> processor : processors) {
        processor.process(joinPoint);
      }
      return result;
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable throwable) {
      throw new ProcessException(throwable);
    }
  }

  private static class ProcessException extends RuntimeException {
    public ProcessException(Throwable cause) {
      super(cause);
    }
  }
}
