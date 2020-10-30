package io.github.codingzhengsz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Configuration
public class InitPrintChain {

  private final List<PrintChainPattern> printChainPatterns;

  public InitPrintChain(List<PrintChainPattern> printChainPatterns) {
    this.printChainPatterns = printChainPatterns;
  }

  @PostConstruct
  private void initPrintChainPattern() {
    printChainPatterns.sort(AnnotationAwareOrderComparator.INSTANCE);

    int size = printChainPatterns.size();
    for (int i = 0; i < size; i++) {
      if (i == size - 1) {
        printChainPatterns.get(i).setNext(null);
      } else {
        printChainPatterns.get(i).setNext(printChainPatterns.get(i + 1));
      }
    }
  }

  public void print(int index) {
    printChainPatterns.get(index - 1).print();
  }
}
