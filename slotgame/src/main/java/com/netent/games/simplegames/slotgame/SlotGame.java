package com.netent.games.simplegames.slotgame;

import com.netent.games.simplegames.framework.game.AbstractInteractiveGame;
import com.netent.games.simplegames.framework.game.InteractiveGame;
import com.netent.games.simplegames.framework.game.event.GameEvent;
import com.netent.games.simplegames.framework.game.round.Round;
import com.netent.games.simplegames.framework.player.Player;
import com.netent.games.simplegames.framework.util.GameUtil;
import com.netent.games.simplegames.slotgame.event.PlayerEvent;
import com.netent.games.simplegames.slotgame.round.FreeRound;
import com.netent.games.simplegames.slotgame.round.NormalRound;
import java.util.Map;
import lombok.Getter;

public class SlotGame extends AbstractInteractiveGame implements InteractiveGame {

    @Getter
    private final String description = """
        SlotGame have the following rules:
            • The player has an infinite amount of coins.
            • The player bets 10 coins to play a normal game round
            • In any round (free or normal), the player has a 30 % chance of winning back 20 coins.
            • In any round (free or normal), the player also has a 10% chance of triggering a
                free round where the player does not have to pay for bet. The free round works in
                the same way as a normal round except it costs 0 coins. The free round should
                follow immediately after the normal round.
            • The player can both win coins and free round at the same time.""";
    @Getter
    private final Round defaultRound = new NormalRound();
    @Getter
    private final Map<Integer, Round> specialRounds = Map.of(10, new FreeRound());
    @Getter
    private final Map<Integer, GameEvent<Player>> playerEvents = Map.of(30,
        PlayerEvent.WINNING_BACK.getEvent());

    @Override
    public void play() {
        var currentRound = getCurrentRound();
        currentRound.play();

        var event = GameUtil.defineChance(playerEvents, PlayerEvent.NONE.getEvent());
        event.accept(player);

        if (PlayerEvent.WINNING_BACK.getEvent() == event) {
            System.out.println("WIN!!!");
        } else {
            System.out.println("nothing");
        }

        var nextRound = GameUtil.defineChance(specialRounds, defaultRound);

        if (currentRound instanceof NormalRound && nextRound instanceof FreeRound) {
            play();
        }

        setCurrentRound(nextRound);
    }

    @Override
    protected void handleInput(int input) {
        play();
    }

    @Override
    public void cli() {
        interactiveMenu();
    }

}
