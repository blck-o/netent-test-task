package com.netent.games.simplegames.slotgame.event;

import com.netent.games.simplegames.framework.game.event.GameEvent;
import com.netent.games.simplegames.framework.player.Player;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlayerEvent {

    WINNING_BACK(player -> player.plus(20L)),
    NONE(Function.identity()::apply);

    private final GameEvent<Player> event;

}
