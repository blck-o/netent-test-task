package com.netent.games.simplegames.framework.game.round;

public interface Round {

    String getDescription();

    default void play() {
    }

}
