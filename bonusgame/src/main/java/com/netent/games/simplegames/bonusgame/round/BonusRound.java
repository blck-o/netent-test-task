package com.netent.games.simplegames.bonusgame.round;

import com.netent.games.simplegames.bonusgame.BoxPickingGame;
import com.netent.games.simplegames.framework.game.round.Round;

public class BonusRound implements Round {

    @Override
    public void play() {
        var game = new BoxPickingGame();
        game.play();
    }

}
