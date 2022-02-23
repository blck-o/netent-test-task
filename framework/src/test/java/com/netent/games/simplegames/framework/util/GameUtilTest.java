package com.netent.games.simplegames.framework.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GameUtilTest {

    @Mock
    private Random random = mock(Random.class, withSettings().withoutAnnotations());

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        var randomField = GameUtil.class.getDeclaredField("random");
        randomField.setAccessible(true);
        randomField.set(null, random);
    }

    @Test
    void defineChance_WhenOptionsProvided_ThenReturnOptionByChance() {
        when(random.nextDouble()).thenReturn(0.1D);

        Map<Integer, String> options = Map.of(
            10, "test1",
            20, "test3",
            30, "test2"
        );

        var result = GameUtil.defineChance(options, "test4");

        assertEquals("test1", result);
    }

    @Test
    void defineChance_WhenNoOptionForChance_ThenReturnDefaultResult() {
        when(random.nextDouble()).thenReturn(0.9D);

        Map<Integer, String> options = Map.of(
            10, "test1",
            20, "test3",
            30, "test2"
        );

        var result = GameUtil.defineChance(options, "test4");

        assertEquals("test4", result);
    }

    @Test
    void defineChance_WhenOptionsIsEmpty_ThenReturnDefaultResult() {
        Map<Integer, String> options = Collections.emptyMap();

        var result = GameUtil.defineChance(options, "test");

        assertEquals("test", result);
    }

}
