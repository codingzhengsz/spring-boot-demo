package io.github.codingzhengsz.judge_empty;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Example {

  public static void main(String[] args) {
    Logger log = Logger.getLogger("martin");

    User martin = new User();
    User.School school = new User.School();
    martin.setName("Hello");
    martin.setSchool(school);

    // 1. Basic invoke
    String value =
        OptionalBean.ofNullable(martin)
            .getBean(User::getSchool)
            .getBean(User.School::getAddress)
            .get();
    log.info(value);

    // 2. isPresent
    boolean present =
        OptionalBean.ofNullable(martin)
            .getBean(User::getSchool)
            .getBean(User.School::getAddress)
            .isPresent();
    log.log(Level.INFO, "isPresent: {0} ", present);

    // 3. ifPresent
    OptionalBean.ofNullable(martin)
        .getBean(User::getSchool)
        .getBean(User.School::getAddress)
        .ifPresent(address -> log.log(Level.INFO, String.format("address: %s", address)));

    // 4. orElse
    String value2 =
        OptionalBean.ofNullable(martin)
            .getBean(User::getSchool)
            .getBean(User.School::getAddress)
            .orElse("Home");
    log.log(Level.INFO, "orElse: {0} ", value2);

    // 5. exception orElseThrow
    try {
      String value3 =
          OptionalBean.ofNullable(martin)
              .getBean(User::getSchool)
              .getBean(User.School::getAddress)
              .orElseThrow(() -> new RuntimeException("Error"));
      log.log(Level.INFO, "value3: {0} ", value3);
    } catch (Exception e) {
      log.log(Level.SEVERE, "message: {0} ", e.getMessage());
    }
  }
}
