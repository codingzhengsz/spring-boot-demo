package io.github.codingzhengsz.annotation;

import io.github.codingzhengsz.processor.AbstractProcessor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Process {

  Class<? extends AbstractProcessor>[] processors();
}
