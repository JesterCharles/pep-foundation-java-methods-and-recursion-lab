package textscan;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Public tests for the Methods and Recursion lab. Each test calls one method
 * with several inputs, including edge cases, and checks only the results.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class TextScanTest {

    @Test
    void task1Cleaned() {
        assertAll(
                () -> assertEquals("hello world", TextScan.cleaned("  hello   world "),
                        "Task 1: cleaned(\"  hello   world \")"),
                () -> assertEquals("one", TextScan.cleaned("one"), "Task 1: cleaned(\"one\")"),
                () -> assertEquals("a b c", TextScan.cleaned("a b c"), "Task 1: cleaned(\"a b c\")"),
                () -> assertEquals("", TextScan.cleaned("   "), "Task 1: cleaned(\"   \")"),
                () -> assertEquals("", TextScan.cleaned(""), "Task 1: cleaned(\"\")"));
    }

    @Test
    void task2CountWords() {
        assertAll(
                () -> assertEquals(2, TextScan.countWords("  one   two "),
                        "Task 2: countWords(\"  one   two \")"),
                () -> assertEquals(1, TextScan.countWords("word"), "Task 2: countWords(\"word\")"),
                () -> assertEquals(4, TextScan.countWords("the quick brown fox"),
                        "Task 2: countWords(\"the quick brown fox\")"),
                () -> assertEquals(0, TextScan.countWords("   "), "Task 2: countWords(\"   \")"),
                () -> assertEquals(0, TextScan.countWords(""), "Task 2: countWords(\"\")"));
    }

    @Test
    void task3Headline() {
        assertAll(
                () -> assertEquals("the quick brown fox", TextScan.headline("  the quick   brown fox  "),
                        "Task 3: headline(\"  the quick   brown fox  \") with the house width 24"),
                () -> assertEquals("the quick...", TextScan.headline("  the quick   brown fox  ", 12),
                        "Task 3: headline(\"  the quick   brown fox  \", 12), exactly 12 characters"),
                () -> assertEquals("abc", TextScan.headline("abc", 3),
                        "Task 3: headline(\"abc\", 3), exactly at the limit stays unchanged"),
                () -> assertEquals("a...", TextScan.headline("abcde", 4),
                        "Task 3: headline(\"abcde\", 4)"),
                () -> assertEquals("", TextScan.headline("   ", 10), "Task 3: headline(\"   \", 10)"),
                () -> assertEquals("notes from today and tomo...",
                        TextScan.headline("notes from today and tomorrow and beyond", 28),
                        "Task 3: headline(long note, 28)"));
    }

    @Test
    void task4Reverse() {
        assertAll(
                () -> assertEquals("nacs", TextScan.reverse("scan"), "Task 4: reverse(\"scan\")"),
                () -> assertEquals("ab", TextScan.reverse("ba"), "Task 4: reverse(\"ba\")"),
                () -> assertEquals("x", TextScan.reverse("x"), "Task 4: reverse(\"x\")"),
                () -> assertEquals("", TextScan.reverse(""), "Task 4: reverse(\"\")"),
                () -> assertEquals("oof rab", TextScan.reverse("bar foo"), "Task 4: reverse(\"bar foo\")"));
    }

    @Test
    void task5MatchingEnds() {
        assertAll(
                () -> assertEquals(2, TextScan.matchingEnds("noon"), "Task 5: matchingEnds(\"noon\")"),
                () -> assertEquals(2, TextScan.matchingEnds("level"),
                        "Task 5: matchingEnds(\"level\"), the middle character pairs with nobody"),
                () -> assertEquals(0, TextScan.matchingEnds("java"), "Task 5: matchingEnds(\"java\")"),
                () -> assertEquals(0, TextScan.matchingEnds("x"),
                        "Task 5: matchingEnds(\"x\"), one character forms no pair"),
                () -> assertEquals(0, TextScan.matchingEnds(""), "Task 5: matchingEnds(\"\")"),
                () -> assertEquals(1, TextScan.matchingEnds("abca"), "Task 5: matchingEnds(\"abca\")"));
    }

    @Test
    void task6CountChar() {
        assertAll(
                () -> assertEquals(3, TextScan.countChar("banana", 'a'), "Task 6: countChar(\"banana\", 'a')"),
                () -> assertEquals(0, TextScan.countChar("", 'x'), "Task 6: countChar(\"\", 'x')"),
                () -> assertEquals(1, TextScan.countChar("z", 'z'), "Task 6: countChar(\"z\", 'z')"),
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
