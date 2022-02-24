package com.netent.games.simplegames.framework.game;

import com.netent.games.simplegames.framework.util.ScannerUtil;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class AbstractInteractiveGame extends AbstractGame {

    void showGameInfo() {
        System.out.println("Game:\n\t" + getDescription());
        var rounds = new ArrayList<>(getSpecialRounds().values());
        rounds.add(getDefaultRound());

        System.out.println("Rounds:");
        rounds.forEach(round -> System.out.println(
            "\t" + round.getClass().getSimpleName() + ":\n\t\t" + round.getDescription()));

    }

    protected void showCurrentRound() {
        System.out.println("Current round: " + getCurrentRound().getClass().getSimpleName());
    }

    private void showMenu() {
        var menuRows = """
            Select:
            1) Play
            2) Player
            0) Exit""";
        System.out.println(menuRows);
    }

    private void showPlayerInfo() {
        System.out.println(player);
    }

    protected abstract void handleInput(int input);

    protected void interactiveMenu() {
        showGameInfo();

        try (var scanner = new Scanner(System.in)) {
            while (true) {
                showMenu();
                showCurrentRound();
                var input = scanner.next();
                var userInputOpt = ScannerUtil.tryParseInt(input);
                if (userInputOpt.isEmpty()) {
                    continue;
                }
                int userInput = userInputOpt.get();
                if (userInput == 0) {
                    break;
                } else if (userInput == 2) {
                    showPlayerInfo();
                    continue;
                }
                handleInput(userInput);
            }
        } catch (NoSuchElementException exception) {
            System.err.println("exception is occurred: " + exception);
        }
    }

}
