package com.netent.games.simplegames.framework.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameUtil {

    // TODO test with final
    private final Random random = new Random();

    public <T> T defineChance(Map<Integer, T> options, T defaultResult) {
        Predicate<Map.Entry<Integer, T>> peekIt = entry -> {
            var chance = random.nextDouble();
            return chance <= (entry.getKey() / 100D);
        };

        return options.entrySet().stream()
            .sorted(Entry.comparingByKey())
            .filter(peekIt)
            .findFirst()
            .map(Entry::getValue)
            .orElse(defaultResult);
    }

}
