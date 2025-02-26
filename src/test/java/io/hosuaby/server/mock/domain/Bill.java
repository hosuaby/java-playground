package io.hosuaby.server.mock.domain;

import java.util.Map;
import java.util.Objects;

/**
 * Bill for consumed ice cream.
 *
 * @author Alexei KLENIN
 */
public class Bill {
  private static final Map<Integer, Float> PRICES =
      Map.of(
          1, (float) 2.00, // two euros for one ball (expensive!)
          3, (float) 2.85, // 2.85€ for 3 balls
          5, (float) 4.30, // 4.30€ for 5 balls
          7, (float) 5); // only five euros for seven balls! Wow

  private static final float MIXIN_PRICE = (float) 0.6; // price per mixin

  private Float price;

  public Bill() {}

  public Bill(final Float price) {
    this.price = price;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(final Float price) {
    this.price = price;
  }

  /**
   * Makes a bill from an order.
   *
   * @param order ice cream order
   * @return bill
   */
  public static Bill makeBill(final IceCreamOrder order) {
    final var nbBalls = order.getBalls().values().stream().mapToInt(Integer::intValue).sum();
    final var price = PRICES.get(nbBalls) + order.getMixins().size() * MIXIN_PRICE;
    return new Bill(price);
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Bill)) {
      return false;
    }

    final var another = (Bill) other;
    return Objects.equals(price, another.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(price);
  }
}
