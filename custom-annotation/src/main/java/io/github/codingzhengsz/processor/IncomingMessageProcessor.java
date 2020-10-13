package io.github.codingzhengsz.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IncomingMessageProcessor extends AbstractProcessor {

  private static final Logger logger = LoggerFactory.getLogger(IncomingMessageProcessor.class);

  @Override
  public void process() {
      logger.info("[IncomingMessageProcessor] start to process Incoming Message");
  }
}
