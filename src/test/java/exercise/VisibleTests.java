package exercise;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Visible tests for the Methods and Recursion lab: one test per task. Each test
 * calls one method with a few inputs and checks only the results.
 * Restored over the learner's checkout at grade time.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class VisibleTests {

    @Test
    void task1Cleaned() {
        assertAll(
                () -> assertEquals("hello world", TextScan.cleaned("  hello   world "),
                        "Task 1: cleaned(\"  hello   world \")"),
                () -> assertEquals("one", TextScan.cleaned("one"), "Task 1: cleaned(\"one\")"),
                () -> assertEquals("a b c", TextScan.cleaned("a b c"), "Task 1: cleaned(\"a b c\")"));
    }

    @Test
    void task2CountWords() {
        assertAll(
                () -> assertEquals(2, TextScan.countWords("  one   two "),
                        "Task 2: countWords(\"  one   two \")"),
                () -> assertEquals(1, TextScan.countWords("word"), "Task 2: countWords(\"word\")"),
                () -> assertEquals(4, TextScan.countWords("the quick brown fox"),
                        "Task 2: countWords(\"the quick brown fox\")"));
    }

    @Test
    void task3Headline() {
        assertAll(
                () -> assertEquals("the quick brown fox", TextScan.headline("  the quick   brown fox  "),
                        "Task 3: headline(\"  the quick   brown fox  \") with the house width 24"),
                () -> assertEquals("the quick...", TextScan.headline("  the quick   brown fox  ", 12),
                        "Task 3: headline(\"  the quick   brown fox  \", 12), exactly 12 characters"),
                () -> assertEquals("notes from today and tomo...",
                        TextScan.headline("notes from today and tomorrow and beyond", 28),
                        "Task 3: headline(long note, 28)"));
    }

    @Test
    void task4Reverse() {
        assertAll(
                () -> assertEquals("nacs", TextScan.reverse("scan"), "Task 4: reverse(\"scan\")"),
                () -> assertEquals("ab", TextScan.reverse("ba"), "Task 4: reverse(\"ba\")"),
                () -> assertEquals("oof rab", TextScan.reverse("bar foo"), "Task 4: reverse(\"bar foo\")"));
    }

    @Test
    void task5MatchingEnds() {
        assertAll(
                () -> assertEquals(2, TextScan.matchingEnds("noon"), "Task 5: matchingEnds(\"noon\")"),
                () -> assertEquals(0, TextScan.matchingEnds("java"), "Task 5: matchingEnds(\"java\")"),
                () -> assertEquals(1, TextScan.matchingEnds("abca"), "Task 5: matchingEnds(\"abca\")"));
    }

    @Test
    void task6CountChar() {
        assertAll(
                () -> assertEquals(3, TextScan.countChar("banana", 'a'), "Task 6: countChar(\"banana\", 'a')"),
                () -> assertEquals(0, TextScan.countChar("hello", 'z'), "Task 6: countChar(\"hello\", 'z')"),
                () -> assertEquals(4, TextScan.countChar("aaaa", 'a'), "Task 6: countChar(\"aaaa\", 'a')"));
    }

    @Test
    void task7Report() {
        int before = TextScan.scanCount();
        assertTrue(before >= 0,
                "Task 7: scanCount() must never be negative; it reports how many notes were scanned");
        assertAll(
                () -> assertEquals("words=4 | headline=the quick brown fox | ends=1",
                        TextScan.report("  the quick   brown fox  "),
                        "Task 7: report(\"  the quick   brown fox  \")"),
                () -> assertEquals("words=1 | headline=noon | ends=2", TextScan.report("noon"),
                        "Task 7: report(\"noon\")"));
        assertEquals(before + 2, TextScan.scanCount(),
                "Task 7: two report(...) calls must raise scanCount() by exactly 2");
    }
}
