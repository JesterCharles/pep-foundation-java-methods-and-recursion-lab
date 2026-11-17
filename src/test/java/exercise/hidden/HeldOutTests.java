package exercise.hidden;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import exercise.TextScan;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/** Held-out tests: graded on submit, never shown to the learner. Edge cases for Tasks 2-6. */
@TestMethodOrder(MethodOrderer.MethodName.class)
class HeldOutTests {

    @Test
    void task2CountWordsEdgeCases() {
        assertAll(
                () -> assertEquals(0, TextScan.countWords("   "), "Task 2: countWords(\"   \")"),
                () -> assertEquals(0, TextScan.countWords(""), "Task 2: countWords(\"\")"));
    }

    @Test
    void task3HeadlineEdgeCases() {
        assertAll(
                () -> assertEquals("abc", TextScan.headline("abc", 3),
                        "Task 3: headline(\"abc\", 3), exactly at the limit stays unchanged"),
                () -> assertEquals("a...", TextScan.headline("abcde", 4),
                        "Task 3: headline(\"abcde\", 4)"),
                () -> assertEquals("", TextScan.headline("   ", 10), "Task 3: headline(\"   \", 10)"));
    }

    @Test
    void task4ReverseEdgeCases() {
        assertAll(
                () -> assertEquals("x", TextScan.reverse("x"), "Task 4: reverse(\"x\")"),
                () -> assertEquals("", TextScan.reverse(""), "Task 4: reverse(\"\")"));
    }

    @Test
    void task5MatchingEndsEdgeCases() {
        assertAll(
                () -> assertEquals(2, TextScan.matchingEnds("level"),
                        "Task 5: matchingEnds(\"level\"), the middle character pairs with nobody"),
                () -> assertEquals(0, TextScan.matchingEnds("x"),
                        "Task 5: matchingEnds(\"x\"), one character forms no pair"),
                () -> assertEquals(0, TextScan.matchingEnds(""), "Task 5: matchingEnds(\"\")"));
    }

    @Test
    void task6CountCharEdgeCases() {
        assertAll(
                () -> assertEquals(0, TextScan.countChar("", 'x'), "Task 6: countChar(\"\", 'x')"),
                () -> assertEquals(1, TextScan.countChar("z", 'z'), "Task 6: countChar(\"z\", 'z')"));
    }
}
