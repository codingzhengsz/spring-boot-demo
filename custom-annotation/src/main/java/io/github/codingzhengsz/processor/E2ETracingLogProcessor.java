package io.github.codingzhengsz.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class E2ETracingLogProcessor extends AbstractProcessor<Map<String, String>> {

  private static final Logger logger = LoggerFactory.getLogger(E2ETracingLogProcessor.class);

  @Override
  public void onProcess(ProceedingJoinPoint joinPoint, Map<String, String> firstParam) {
    // TODO: Call API with header appKey and tracingKey
    logger.info("start to process: E2ETracingLogProcessor");
    logger.info("[E2ETracingLogProcessor] Hello : {}", firstParam.get("Hello"));
  }
}
