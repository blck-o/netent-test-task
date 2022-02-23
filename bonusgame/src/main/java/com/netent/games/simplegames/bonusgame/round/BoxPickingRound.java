package com.netent.games.simplegames.bonusgame.round;

import com.netent.games.simplegames.framework.game.round.Round;
import com.netent.games.simplegames.framework.player.Player;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class BoxPickingRound implements Round {

    private final Box[] boxes;
    private final Player player = Player.player();

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
                String step = scanner.next();
                var status = processingStep(step);

                if (ProcessingStatus.END == status) {
                    System.out.println("end game");
                    break;
                }
            } while (true);

            System.out.println("submit result");
            submitResult();
            System.out.println(player);
        }
    }

    private ProcessingStatus processingStep(String step) {
        var integerOptional = tryParseInt(step);

        if (integerOptional.isEmpty()) {
            return ProcessingStatus.RETRY;
        }

        int boxIndex = integerOptional.get();

        if (boxIndex > boxes.length) {
            return ProcessingStatus.RETRY;
        }

        var box = boxes[boxIndex];

        if (box.isOpened()) {
            return ProcessingStatus.RETRY;
        } else if (box.getValue() == null) {
            return ProcessingStatus.END;
        }

        box.setOpened(true);

        if (isEndOfGame()) {
            return ProcessingStatus.END;
        }

        return ProcessingStatus.PROCESSED;
    }

    private boolean isEndOfGame() {
        return Arrays.stream(boxes)
            .allMatch(Box::isOpened);
    }

    private Optional<Integer> tryParseInt(String input) {
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException exception) {
            return Optional.empty();
        }
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


    private enum ProcessingStatus {
        END,
        PROCESSED,
        RETRY
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
