package io.github.codingzhengsz.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public abstract class AbstractProcessor<T> {

  private static final Logger logger = LoggerFactory.getLogger(AbstractProcessor.class);

  private T getArg0FromJoinPoint(ProceedingJoinPoint joinPoint) {
    if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
      try {
        return (T) joinPoint.getArgs()[0];
      } catch (Exception e) {
        logger.error("Occurs Exception", e);
        return null;
      }
    }
    return null;
  }

  public void process(ProceedingJoinPoint joinPoint) {
    this.onProcess(joinPoint, getArg0FromJoinPoint(joinPoint));
  }

  public void process(ProceedingJoinPoint joinPoint, T firstParam) {
    this.onProcess(joinPoint, firstParam);
  }

  public <A extends Annotation> A getAnnotation(
      ProceedingJoinPoint joinPoint, Class<A> annotationClass) {
    return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(annotationClass);
  }

  protected String getTargetMethod(ProceedingJoinPoint joinPoint) {
    return joinPoint.getTarget().getClass().getName()
        + "."
        + joinPoint.getSignature().getName()
        + "()";
  }

  public void onProcess(ProceedingJoinPoint joinPoint, T firstParam) {
    logger.info("[AbstractProcessor] : {}", getTargetMethod(joinPoint));
    logger.info("[AbstractProcessor] : FirstParam {}", firstParam);
  }
}
