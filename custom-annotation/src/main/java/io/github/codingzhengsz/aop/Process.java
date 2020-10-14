package io.github.codingzhengsz.aop;

import io.github.codingzhengsz.processor.AbstractProcessor;
import io.github.codingzhengsz.processor.DefaultProcessor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface Process {
  Class<? extends AbstractProcessor> processor() default DefaultProcessor.class;

  Class<? extends AbstractProcessor>[] processors() default {};
}
