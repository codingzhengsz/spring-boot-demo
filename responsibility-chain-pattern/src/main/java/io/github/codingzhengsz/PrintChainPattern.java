package io.github.codingzhengsz;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public abstract class PrintChainPattern {

  private PrintChainPattern next;

  public final void print() {
    String message = getString();

    System.out.println(message);
    if (getNext() != null) {
      getNext().print();
    }
  }

  public abstract String getString();

  public PrintChainPattern getNext() {
    return next;
  }

  public void setNext(PrintChainPattern next) {
    this.next = next;
  }
}
