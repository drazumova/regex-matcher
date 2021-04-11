package com.test.mather;

import static com.test.mather.Main.matches;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void matchesEqualStrings() {
        assertTrue(matches("a", "a"));
    }

    @Test
    void matchesAnyString() {
        assertTrue(matches("a", ".*"));
    }

    @Test
    void matchesNotEmptyString() {
        assertTrue(matches("a", ".+"));
    }

    @Test
    void matchesNotMatchingString() {
        assertFalse(matches("a", ".+a"));
    }

    @Test
    void matchesMatchingString() {
        assertTrue(matches("ba", ".+a"));
    }

    @Test
    void matchesMatchingReversedString() {
        assertFalse(matches("ab", ".+a"));
    }

    @Test
    void matchesNullText() {
        assertFalse(matches(null, ".*"));
    }

    @Test
    void matchesNullRegex() {
        assertFalse(matches("some text", null));
    }

    @Test
    void matchesNullBoth() {
        assertFalse(matches(null, null));
    }

    @Test
    void matchesIncorrectRegex() {
        assertFalse(matches("some text", "[a*"));
    }

    @Test
    void matchesExponentialBacktracking() {
        assertFalse(matches("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "((a|a)*|.*)"));
    }

    @Test
    void matchesPossibleReDos() {
        assertTrue(matches("aaaa", "(.*|(a|a)*)"));
    }

    @Test
    void matchesTimeLoop() {
        boolean allResults = true;
        for(String s = ""; s.length() < 50; s += "a") {
            allResults &= matches(s, "(aa|aab?)+");
        }
        assertFalse(allResults);
    }
}