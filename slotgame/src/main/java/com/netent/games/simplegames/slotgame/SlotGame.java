package com.netent.games.simplegames.slotgame;

import com.netent.games.simplegames.framework.game.AbstractGame;
import com.netent.games.simplegames.framework.game.Game;
import com.netent.games.simplegames.framework.game.event.GameEvent;
import com.netent.games.simplegames.framework.game.round.Round;
import com.netent.games.simplegames.framework.player.Player;
import com.netent.games.simplegames.framework.util.GameUtil;
import com.netent.games.simplegames.slotgame.event.PlayerEvent;
import com.netent.games.simplegames.slotgame.round.FreeRound;
import com.netent.games.simplegames.slotgame.round.NormalRound;
import java.util.Map;
import lombok.Getter;

class SlotGame extends AbstractGame implements Game {

    @Getter
    private final Round defaultRound = new NormalRound();

    @Getter
    private final Map<Integer, Round> specialRounds = Map.of(
        10, new FreeRound()
    );

    @Getter
    private final Map<Integer, GameEvent<Player>> playerEvents = Map.of(
        30, PlayerEvent.WINNING_BACK.getEvent()
    );

    public static void main(String[] args) {
        var game = new SlotGame();

        Player player = Player.player();
        System.out.println(player);
        for (int i = 0; i < 1000000; i++) {
            game.play();
        }
        System.out.println(player);
        System.out.println("RTP = " + Math.round(
            player.getTotalWin().doubleValue() / player.getTotalBet().doubleValue() * 100));
    }

    @Override
    public void play() {
        var currentRound = getCurrentRound();
        currentRound.play();

        var event = GameUtil.defineChance(playerEvents, PlayerEvent.NONE.getEvent());
        event.accept(player);

        var nextRound = GameUtil.defineChance(specialRounds, defaultRound);

        if (currentRound instanceof NormalRound
            && nextRound instanceof FreeRound) {
            play();
        }

        setCurrentRound(nextRound);
    }

}
