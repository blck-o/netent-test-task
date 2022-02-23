package com.netent.games.simplegames.framework.game;

import com.netent.games.simplegames.framework.game.event.GameEvent;
import com.netent.games.simplegames.framework.game.round.Round;
import com.netent.games.simplegames.framework.player.Player;
import java.util.Collections;
import java.util.Map;
import lombok.Setter;

public abstract class AbstractGame {

    protected final Player player = Player.player();

    @Setter
    private Round currentRound;

    protected Round getCurrentRound() {
        if (currentRound == null) {
            return getDefaultRound();
        }
        return currentRound;
    }

    protected abstract Round getDefaultRound();

    protected Map<Integer, Round> getSpecialRounds() {
        return Collections.emptyMap();
    }

    protected Map<Integer, GameEvent<Player>> getPlayerEvents() {
        return Collections.emptyMap();
    }

}
