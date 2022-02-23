package com.netent.games.simplegames.bonusgame;

import com.netent.games.simplegames.bonusgame.round.BasicRound;
import com.netent.games.simplegames.bonusgame.round.BonusRound;
import com.netent.games.simplegames.framework.game.AbstractGame;
import com.netent.games.simplegames.framework.game.Game;
import com.netent.games.simplegames.framework.game.round.Round;
import com.netent.games.simplegames.framework.util.GameUtil;
import java.util.Map;
import lombok.Getter;

public class BonusGame extends AbstractGame implements Game {

    @Getter
    private final Round defaultRound = new BasicRound();

    @Getter
    private final Map<Integer, Round> specialRounds = Map.of(
        10, new BonusRound()
    );

    public static void main(String[] args) {
        var game = new BonusGame();
        for (int i = 0; i < 10; i++) {
            game.play();
        }
    }

    @Override
    public void play() {
        var currentRound = getCurrentRound();
        currentRound.play();

        var nextRound = currentRound instanceof BasicRound
            ? GameUtil.defineChance(specialRounds, defaultRound)
            : defaultRound;

        setCurrentRound(nextRound);
    }

}
