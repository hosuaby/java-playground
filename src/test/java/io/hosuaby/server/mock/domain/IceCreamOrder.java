package io.hosuaby.server.mock.domain;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Give me some ice-cream! :p
 *
 * @author Alexei KLENIN
 */
public record IceCreamOrder(
        int id, // order id
        Map<Flavor, Integer> balls, // how much balls of flavor
        Set<Mixin> mixins, // and some mixins ...
        Instant orderTimestamp // and give it to me right now !
) implements IOrder {
  public IceCreamOrder() {
    this(Instant.now());
  }

  public IceCreamOrder(final int id) {
    this(id, new HashMap<>(), new LinkedHashSet<>(), Instant.now());
  }

  IceCreamOrder(final Instant orderTimestamp) {
    this(
            ThreadLocalRandom.current().nextInt(),
            new HashMap<>(),
            new LinkedHashSet<>(),
            orderTimestamp);
  }

  @Override
  public IceCreamOrder addBall(final Flavor ballFlavor) {
    final var ballCount = balls.containsKey(ballFlavor) ? balls.get(ballFlavor) + 1 : 1;
    var balls = new HashMap<>(this.balls);
    balls.put(ballFlavor, ballCount);

    return new IceCreamOrder(this.id, balls, this.mixins, this.orderTimestamp);
  }

  @Override
  public IceCreamOrder addMixin(final Mixin mixin) {
    var mixins = new LinkedHashSet<>(this.mixins);
    mixins.add(mixin);

    return new IceCreamOrder(this.id, this.balls, mixins, this.orderTimestamp);
  }

  @Override
  public IceCreamOrder withOrderTimestamp(final Instant orderTimestamp) {
    return new IceCreamOrder(this.id, this.balls, this.mixins, orderTimestamp);
  }
}
