package com.netent.games.simplegames.slotgame.round;

import com.netent.games.simplegames.framework.game.round.Round;
import com.netent.games.simplegames.framework.player.Player;
import lombok.Getter;

public class NormalRound implements Round {

    private static final Long ROUND_COST = 10L;
    private final Player player = Player.player();
    @Getter
    private final String description = "The player bets 10 coins to play a normal game round" ;

    @Override
    public void play() {
        player.minus(ROUND_COST);
    }
}
