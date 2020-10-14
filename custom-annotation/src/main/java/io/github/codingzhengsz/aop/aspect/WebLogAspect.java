package io.github.codingzhengsz.aop.aspect;

import com.google.gson.Gson;
import io.github.codingzhengsz.aop.WebLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class WebLogAspect {

  private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

  @Pointcut("@annotation(io.github.codingzhengsz.aop.WebLog)")
  public void webLog() {}

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    String methodDescription = getAspectLogDescription(joinPoint);
    logger.info("=========doBefore==========");
    logger.info("Description: {}", methodDescription);
    logger.info("=========doBefore End==========");
  }

  @After("webLog()")
  public void doAfter() throws Throwable {
    logger.info("=========doAfter==========");
    logger.info("After");
    logger.info("=========doAfter End==========");
  }

  @Around("webLog()")
  public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    logger.info("=========doAround==========");
    Object result = proceedingJoinPoint.proceed();
    logger.info("Response Args: {}", new Gson().toJson(result));
    logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
    logger.info("=========doAround End==========");
    return result;
  }

  @AfterThrowing(value = "webLog()", throwing = "e")
  public void afterThrowing(JoinPoint joinPoint, Throwable e) throws Exception {
    logger.info("==========Throwing=========");
    logger.info("Exception msg: {}", e.getMessage());
    logger.info("Description: {}", getAspectLogDescription(joinPoint));
    logger.info("==========Throwing End=========");
  }

  public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
    String targetName = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    Object[] arguments = joinPoint.getArgs();
    Class<?> targetClass = Class.forName(targetName);
    Method[] methods = targetClass.getMethods();
    StringBuilder description = new StringBuilder("");
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        Class<?>[] clazzs = method.getParameterTypes();
        if (clazzs.length == arguments.length) {
          description.append(method.getAnnotation(WebLog.class).description());
          break;
        }
      }
    }
    return description.toString();
  }
}
