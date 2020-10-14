package io.github.codingzhengsz.aop;

import io.github.codingzhengsz.processor.E2ETracingLogProcessor;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Process(processor = E2ETracingLogProcessor.class)
public @interface E2ETracing {

  @AliasFor("tracingKey")
  String value() default "";

  @AliasFor("value")
  String tracingKey() default "";

  boolean isPassed() default true;
}
