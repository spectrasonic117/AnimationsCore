package com.spectrasonic.animationsCore.Utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class FrameUtils {
    private static final int UNICODE_OFFSET = 0xE000;

    /**
     * Generates a list of Unicode characters for animation frames.
     *
     * @param from Starting frame index
     * @param until Ending frame index
     * @param numeric Whether to use numeric or character-based frames
     * @return List of animation frames
     */
    public static List<Character> generateFrames(int from, int until, boolean numeric) {
        return numeric
                ? generateNumericFrames(from, until)
                : generateCharacterFrames(from, until);
    }

    private static List<Character> generateCharacterFrames(int from, int until) {
        return IntStream.rangeClosed(from, until)
                .mapToObj(i -> (char) (UNICODE_OFFSET + i))
                .collect(Collectors.toList());
    }

    private static List<Character> generateNumericFrames(int from, int until) {
        return IntStream.rangeClosed(from, until)
                .mapToObj(i -> (char) Integer.parseInt(String.format("\\uE%03d", i), 16))
                .collect(Collectors.toList());
    }

    /**
     * Converts a character to its Unicode representation.
     *
     * @param character Input character
     * @return Unicode string representation
     */
    public static String getUnicodeRepresentation(Character character) {
        return String.format("\\u%04x", (int) character);
    }
}