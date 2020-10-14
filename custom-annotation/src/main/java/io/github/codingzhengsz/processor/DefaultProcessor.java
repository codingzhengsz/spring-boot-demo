package io.github.codingzhengsz.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultProcessor<T> extends AbstractProcessor<T> {
  private static final Logger logger = LoggerFactory.getLogger(DefaultProcessor.class);

  @Override
  public void process(ProceedingJoinPoint joinPoint, T firstParam) {
    logger.info("[DefaultProcessor] Default Processor");
    super.process(joinPoint, firstParam);
  }
}
