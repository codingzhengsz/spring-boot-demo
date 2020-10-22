package io.github.codingzhengsz.service;

import org.springframework.stereotype.Service;

@Service
public class GenericTypeService {

  public <T> T invokeMethod(Class<T> clazz) {
      if (clazz.getName().equals("java.lang.String")) {
      return clazz.cast(invoke(2));
    } else {
      return clazz.cast(invoke("Martin"));
    }
  }

  private String invoke(String str) {
    return "Hello , " + str;
  }

  private int invoke(Integer integer) {
    return 1 + integer;
  }

  public static void main(String[] args) {
    GenericTypeService service = new GenericTypeService();
    service.invokeMethod(Integer.class);
  }
}
