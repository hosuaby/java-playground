package io.hosuaby;

import io.hosuaby.server.mock.domain.Flavor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SwitchExpressionsTest {

    @Test
    void testSwitchExpressions() {
        int calories = 0;
        int fruits = 0;

        for (var flavor : Flavor.values()) {
            switch (flavor) {
                case STRAWBERRY -> {
                    calories += 30;
                    fruits++;
                }
                case CHOCOLATE, VANILLA -> {
                    calories += 50;
                }
                case BANANA -> {
                    calories += 40;
                    fruits++;
                }
                case PISTACHIO -> {
                    calories += 20;
                }
                case MELON -> {
                    calories += 10;
                    fruits++;
                }
            }
        }

        assertThat(calories)
                .isEqualTo(200);
        assertThat(fruits)
                .isEqualTo(3);
    }
}
