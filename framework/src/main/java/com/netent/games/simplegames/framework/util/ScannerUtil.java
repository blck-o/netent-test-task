package com.netent.games.simplegames.framework.util;

import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ScannerUtil {

    public Optional<Integer> tryParseInt(String input) {
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException exception) {
            return Optional.empty();
        }
    }

}
