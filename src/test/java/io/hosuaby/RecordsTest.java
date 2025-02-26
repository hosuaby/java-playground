package io.hosuaby;

import io.hosuaby.server.mock.domain.Flavor;
import io.hosuaby.server.mock.domain.IceCreamOrder;
import io.hosuaby.server.mock.domain.Mixin;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordsTest {

    @Test
    void testConstructor() {
        var order1 = new IceCreamOrder();
        assertThat(order1)
                .isNotNull()
                .hasFieldOrPropertyWithValue("balls", Collections.emptyMap())
                .hasFieldOrPropertyWithValue("mixins", Collections.emptySet());

        var order2 = order1.addBall(Flavor.BANANA);
        assertThat(order2)
                .isNotNull()
                .isNotEqualTo(order1)
                .hasFieldOrPropertyWithValue("balls", Collections.singletonMap(Flavor.BANANA, 1))
                .hasFieldOrPropertyWithValue("mixins", Collections.emptySet());
    }

    @Test
    void testGetters() {
        var order = new IceCreamOrder()
                .addBall(Flavor.BANANA)
                .addBall(Flavor.PISTACHIO)
                .addBall(Flavor.MELON)
                .addMixin(Mixin.COOKIES)
                .addMixin(Mixin.MNMS);

        assertThat(order)
                .isNotNull();
        assertThat(order.balls())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .isEqualTo(Map.of(
                        Flavor.BANANA, 1,
                        Flavor.PISTACHIO, 1,
                        Flavor.MELON, 1));
        assertThat(order.mixins())
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .isEqualTo(Set.of(Mixin.COOKIES, Mixin.MNMS));
    }

    @Test
    void testHashCodeAndEquals() {
        var timestamp = Instant.now();

        var order1 = new IceCreamOrder(1)
                .withOrderTimestamp(timestamp)
                .addBall(Flavor.BANANA)
                .addBall(Flavor.PISTACHIO)
                .addBall(Flavor.MELON)
                .addMixin(Mixin.COOKIES)
                .addMixin(Mixin.MNMS);

        var order2 = new IceCreamOrder(1)
                .withOrderTimestamp(timestamp)
                .addBall(Flavor.BANANA)
                .addBall(Flavor.PISTACHIO)
                .addBall(Flavor.MELON)
                .addMixin(Mixin.COOKIES)
                .addMixin(Mixin.MNMS);

        var hash1 = order1.hashCode();
        var hash2 = order2.hashCode();

        assertThat(hash1)
                .isEqualTo(hash2);
        assertThat(order1)
                .isEqualTo(order2);
    }

    @Test
    void testToString() {
        var order = new IceCreamOrder()
                .addBall(Flavor.BANANA)
                .addBall(Flavor.PISTACHIO)
                .addBall(Flavor.MELON)
                .addMixin(Mixin.COOKIES)
                .addMixin(Mixin.MNMS);

        var str = order.toString();
        assertThat(str)
                .isNotNull()
                .isNotEmpty();
    }
}
