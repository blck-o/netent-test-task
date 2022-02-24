package com.netent.games.simplegames.bonusgame.round;

import com.netent.games.simplegames.framework.game.round.Round;
import com.netent.games.simplegames.framework.player.Player;
import com.netent.games.simplegames.framework.util.ScannerUtil;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class BoxPickingRound implements Round {

    private final Box[] boxes;
    private final Player player = Player.player();
    @Getter
    private final String description = """
        In this game the player is given five boxes. One of the boxes will end the game, while
        the rest contain 5 coins each. The values should be assigned to the boxes
        randomly when the user enters the bonus round from the basic round. Each box
        can only be opened once and the game ends when the end-game box is picked
        """;

    public BoxPickingRound() {
        int boxSize = 5;
        boxes = new Box[boxSize];

        int endBox = (new Random()).nextInt(0, boxes.length);

        for (int i = 0; i < boxes.length; i++) {
            if (i == endBox) {
                boxes[i] = Box.empty();
                continue;
            }

            boxes[i] = Box.notEmpty();
        }
    }

    @Override
    public void play() {
        System.out.println(player);

        try (var scanner = new Scanner(System.in)) {
            do {
                System.out.println("enter box index:");
                var step = scanner.next();
                var inputOptional = ScannerUtil.tryParseInt(step);

                if (inputOptional.isEmpty()) {
                    continue;
                }

                var input = inputOptional.get();
                if (!processingStep(input)) {
                    break;
                }
            } while (true);

            System.out.println("submit result");
            submitResult();
            System.out.println(player);
        }
    }

    private boolean processingStep(int index) {
        if (index >= boxes.length) {
            return true;
        }

        var box = boxes[index];

        if (box.isOpened()) {
            return true;
        } else if (box.getValue() == null) {
            return false;
        }

        box.setOpened(true);

        return !isEndOfGame();
    }

    private boolean isEndOfGame() {
        return Arrays.stream(boxes).allMatch(Box::isOpened);
    }

    private void submitResult() {
        long score = Arrays.stream(boxes)
            .filter(Box::isOpened)
            .map(Box::getValue)
            .filter(Objects::nonNull)
            .mapToLong(Integer::longValue)
            .sum();

        player.plus(score);
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Box {

        private static final Integer DEFAULT_VALUE = 5;

        private final Integer value;

        @Setter
        private boolean opened = false;

        public static Box notEmpty() {
            return new Box(DEFAULT_VALUE);
        }

        public static Box empty() {
            return new Box(null);
        }

    }

}
