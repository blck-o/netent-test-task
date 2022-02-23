package com.netent.games.simplegames.framework.game.event;

import java.util.function.Consumer;

@FunctionalInterface
public interface GameEvent<T> extends Consumer<T> {

}
