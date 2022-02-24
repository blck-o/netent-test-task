package com.netent.games.simplegames.slotgame.round;

import com.netent.games.simplegames.framework.game.round.Round;
import lombok.Getter;

public class FreeRound implements Round {

    @Getter
    private final String description =
        "The free round works in the same way as a normal round except it costs 0 coins " ;

}
