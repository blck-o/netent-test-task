package com.netent.games.simplegames.framework.player;

import com.netent.games.simplegames.framework.exception.InvalidOperationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString(of = {"totalBet", "totalWin"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Player {

    // TODO thinking about player service
    private static final Player INSTANCE = new Player();
    // i'm using Double because of POSITIVE_INFINITY value, in other case it's better to use Long or something like this
    private Double wallet = Double.POSITIVE_INFINITY;
    private Long totalBet = 0L;
    private Long totalWin = 0L;

    public static Player player() {
        return INSTANCE;
    }

    public void plus(Long value) {
        wallet += value;
        totalWin += value;
    }

    public boolean minus(Long value) {
        if (value > wallet) {
            throw new InvalidOperationException("value cannot be greater than player wallet");
        }
        wallet -= value;
        totalBet += value;
        return true;
    }

}
