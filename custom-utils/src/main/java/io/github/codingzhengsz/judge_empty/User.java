package io.github.codingzhengsz.judge_empty;

public class User {

  private String name;
  private String gender;
  private School school;

  public static class School {
    private String scName;
    private String address;

    public String getScName() {
      return scName;
    }

    public void setScName(String scName) {
      this.scName = scName;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public School getSchool() {
    return school;
  }

  public void setSchool(School school) {
    this.school = school;
  }
}
