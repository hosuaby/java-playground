package io.hosuaby.server.mock.domain;

import java.time.Instant;

public interface IOrder {
    IOrder addBall(Flavor ballFlavor);
    IOrder addMixin(Mixin mixin);
    IOrder withOrderTimestamp(Instant orderTimestamp);
}
