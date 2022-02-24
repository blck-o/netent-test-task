package com.netent.games.simplegames.bonusgame;

import com.netent.games.simplegames.bonusgame.round.BasicRound;
import com.netent.games.simplegames.bonusgame.round.BonusRound;
import com.netent.games.simplegames.framework.game.AbstractInteractiveGame;
import com.netent.games.simplegames.framework.game.InteractiveGame;
import com.netent.games.simplegames.framework.game.round.Round;
import com.netent.games.simplegames.framework.util.GameUtil;
import java.util.Map;
import lombok.Getter;

public class BonusGame extends AbstractInteractiveGame implements InteractiveGame {

    @Getter
    private final String description = """
        BonusGame have the following rules:
            • The player has an infinite amount of coins.
            • The player bets 10 coins to play a basic game round.
            • In a basic game round, the player has a 10% chance of triggering a bonus round.
            • If the player wins a bonus round they will play a separate “box picking game”. In
                this game the player is given five boxes. One of the boxes will end the game, while
                the rest contain 5 coins each. The values should be assigned to the boxes
                randomly when the user enters the bonus round from the basic round. Each box
                can only be opened once and the game ends when the end-game box is picked.
            • The player should not be able to win coins by any other means.""";
    @Getter
    private final Round defaultRound = new BasicRound();

    @Getter
    private final Map<Integer, Round> specialRounds = Map.of(
        100, new BonusRound()
    );

    @Override
    public void play() {
        var currentRound = getCurrentRound();

        boolean basicRound = currentRound instanceof BasicRound;
        if (basicRound) {
            System.out.println("nothing");
        } else {
            System.out.println("Bonus round!!!");
        }

        currentRound.play();
        var nextRound = basicRound
            ? GameUtil.defineChance(specialRounds, defaultRound)
            : defaultRound;

        setCurrentRound(nextRound);
    }

    @Override
    public void cli() {
        interactiveMenu();
    }

    @Override
    protected void handleInput(int input) {
        play();
    }

}
