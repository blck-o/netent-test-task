package com.netent.games.simplegames.bonusgame.round;

import com.netent.games.simplegames.bonusgame.BoxPickingGame;
import com.netent.games.simplegames.framework.game.round.Round;
import lombok.Getter;

public class BonusRound implements Round {

    @Getter
    private final String description = "If the player wins a bonus round they will play a separate “box picking game”" ;

    @Override
    public void play() {
        var game = new BoxPickingGame();
        game.play();
    }

}
