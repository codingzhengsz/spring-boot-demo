package io.github.codingzhengsz.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IncomingMessageProcessor extends AbstractProcessor<Map<String, String>> {

  private static final Logger logger = LoggerFactory.getLogger(IncomingMessageProcessor.class);

  @Override
  public void process(ProceedingJoinPoint joinPoint, Map<String, String> incomingMessage) {
    logger.info("[IncomingMessageProcessor] start to process Incoming Message");
  }
}
