package com.netent.games.menu;

import com.netent.games.simplegames.bonusgame.BonusGame;
import com.netent.games.simplegames.slotgame.SlotGame;
import java.util.Scanner;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class MenuMain {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        for (int i = 0; i < GAME.values().length; i++) {
            System.out.println(i + ": " + GAME.values()[i].getTitle());
        }
        int game = scanner.nextInt();

        switch (GAME.values()[game]) {
            case SLOTGAME -> new SlotGame().cli();
            case BONUSGAME -> new BonusGame().cli();
        }
    }

    @Getter
    @RequiredArgsConstructor
    private enum GAME {
        SLOTGAME("Slot game"), BONUSGAME("Bonus game");

        private final String title;
    }

}
