package io.github.codingzhengsz.judge_empty;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class OptionalBean<T> {

  private static final OptionalBean<?> EMPTY = new OptionalBean<>();
  private final T value;

  private OptionalBean() {
    this.value = null;
  }

  /**
   * null value will throw NullPointerException
   *
   * @param value reference value
   */
  private OptionalBean(T value) {
    this.value = Objects.requireNonNull(value);
  }

  /**
   * Wrap a bean that should not be null
   *
   * @param value reference value
   * @param <T> reference type
   * @return A non-empty bean
   */
  public static <T> OptionalBean<T> of(T value) {
    return new OptionalBean<>(value);
  }

  /**
   * Wrap a bean that may be null
   *
   * @param value reference value
   * @param <T> reference type
   * @return A maybe null bean
   */
  public static <T> OptionalBean<T> ofNullable(T value) {
    return value == null ? empty() : of(value);
  }

  /**
   * Get the value
   *
   * @return value
   */
  public T get() {
    return Objects.isNull(value) ? null : value;
  }

  /**
   * Get the value that may be null
   *
   * @param fn function that get the value
   * @param <R> The reference type of the result
   * @return The wrapper of the result
   */
  public <R> OptionalBean<R> getBean(Function<? super T, ? extends R> fn) {
    return Objects.isNull(value) ? OptionalBean.empty() : OptionalBean.ofNullable(fn.apply(value));
  }

  /**
   * If the target value is null, then return an other value
   *
   * @param other A replace value
   * @return Result
   */
  public T orElse(T other) {
    return value != null ? value : other;
  }

  /**
   * If the target value is null, then return an other value through lambda expression
   *
   * @param other the lambda of the other
   * @return Result
   */
  public T ofElseGet(Supplier<? extends T> other) {
    return value != null ? value : other.get();
  }

  /**
   * If the target value is null, then throw an exception
   *
   * @param exceptionSupplier the lambda of exception supplier
   * @param <X> exception type
   * @return value
   * @throws X exception
   */
  public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
    if (value != null) {
      return value;
    } else {
      throw exceptionSupplier.get();
    }
  }

  public boolean isPresent() {
    return value != null;
  }

  /**
   * If a value is present, invoke the specified consumer with the value, otherwise do nothing.
   *
   * @param consumer block to be executed if a value is present
   */
  public void ifPresent(Consumer<? super T> consumer) {
    if (value != null) {
      consumer.accept(value);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OptionalBean<?> that = (OptionalBean<?>) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  /**
   * Null Constant
   *
   * @param <T> reference type
   * @return constant
   */
  public static <T> OptionalBean<T> empty() {
    @SuppressWarnings("unchecked")
    OptionalBean<T> none = (OptionalBean<T>) EMPTY;
    return none;
  }
}
