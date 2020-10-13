package io.github.codingzhengsz.aspect;

import io.github.codingzhengsz.annotation.Process;
import io.github.codingzhengsz.processor.AbstractProcessor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ProcessAspect {

  private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

  @Pointcut("@annotation(io.github.codingzhengsz.annotation.Process)")
  public void process() {}

  @After("process()")
  public void doAfter(JoinPoint joinPoint) throws Exception {
    String targetName = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    Class<?> targetClass = Class.forName(targetName);
    Method[] methods = targetClass.getMethods();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        Process process = method.getAnnotation(Process.class);
        AbstractProcessor abstractProcessor =
            process.processors()[0].getDeclaredConstructor().newInstance();
        abstractProcessor.process();
      }
    }
  }
}
