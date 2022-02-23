package com.netent.games.simplegames.framework.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.netent.games.simplegames.framework.exception.InvalidOperationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @Test
    void player_WhenCalledSeveralTimes_ThenReturnSameInstance() {
        var result1 = Player.player();
        var result2 = Player.player();

        assertSame(result1, result2);
    }

    @Test
    void plus_WhenPlus_ThenIncreaseTotalWinBySameValue() {
        var testee = Player.player();
        var totalWinBefore = testee.getTotalWin();
        var value = 10L;
        testee.plus(value);
        var totalWinAfter = testee.getTotalWin();

        assertEquals(totalWinBefore + value, totalWinAfter);
    }

    @Test
    void minus_WhenMinus_ThenDecreaseTotalBetBySameValue() {
        var testee = Player.player();
        var totalBetBefore = testee.getTotalBet();
        var value = 10L;
        testee.minus(value);
        var totalBetAfter = testee.getTotalBet();

        assertEquals(totalBetBefore + value, totalBetAfter);
    }

    @Test
    void minus_WhenValueIsGreaterThanWallet_ThenThrowExceptionAndNotSubtract() {
        var value = 100L;
        var testee = Player.player();
        setPlayerWallet(10D);

        var walletBefore = testee.getWallet();

        assertThrowsExactly(InvalidOperationException.class, () -> testee.minus(value));

        var walletAfter = testee.getWallet();
        assertEquals(walletBefore, walletAfter);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 10, 100, 300})
    void minus_WhenValueIsNotGreaterThanWallet_ThenReturnTrueAndSubtract(Long value) {
        var testee = Player.player();
        setPlayerWallet(1000D);

        var walletBefore = testee.getWallet();
        var result = testee.minus(value);
        var walletAfter = testee.getWallet();

        assertTrue(result);
        assertEquals(walletBefore - value, walletAfter);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 10, 100, 300})
    void plus_WhenValueProvided_ThenPlus(Long value) {
        var testee = Player.player();
        setPlayerWallet(0D);

        var walletBefore = testee.getWallet();
        testee.plus(value);
        var walletAfter = testee.getWallet();

        assertEquals(walletBefore + value, walletAfter);
    }

    private void setPlayerWallet(Double value) {
        var player = Player.player();
        try {
            var walletField = player.getClass().getDeclaredField("wallet");
            walletField.setAccessible(true);
            walletField.set(player, value);
        } catch (Exception e) {
            // nothing
        }
    }

}
