package com.netent.games.simplegames.bonusgame;

import com.netent.games.simplegames.bonusgame.round.BoxPickingRound;
import com.netent.games.simplegames.framework.game.AbstractGame;
import com.netent.games.simplegames.framework.game.Game;
import com.netent.games.simplegames.framework.game.round.Round;
import lombok.Getter;

public class BoxPickingGame extends AbstractGame implements Game {

    @Getter
    private final Round defaultRound = new BoxPickingRound();

    @Override
    public void play() {
        getCurrentRound().play();
    }

}
