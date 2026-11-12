# Methods and Recursion lab: a text-analysis utility

A note-taking app wants one report line per note: how many words it has, a shortened headline, and whether its ends mirror each other.
You build that utility, `TextScan.java`, in seven linked tasks. You practise writing methods, overloading, static members, two-pointer loops and recursion, and each later task reuses methods you wrote earlier.

## Quick start

The lab needs JDK 25 and Apache Maven 3.9 or later in the terminal. Troubleshooting shows how to check.

1. Open the lab project you were given in VS Code, then open its integrated terminal: **Terminal > New Terminal**.
2. Go into the project folder. Every command in this lab runs there:

   ```
   cd starter
   ```

3. Before the first test run, download the lab's libraries once. This needs the internet, and prints nothing when it works:

   ```
   mvn -q dependency:go-offline
   ```

4. Run the tests before you change anything:

   ```
   mvn -q -o test
   ```

   **All 7 tests fail at first. That is expected: the TODOs are not done yet.**
   The output ends with `Tests run: 7, Failures: 7`, and every failure line starts with its task number, such as `Task 1:`.
5. Work through the checklist below in order. After each task, run `mvn -q -o test` again and read the remaining `Task N:` lines.
6. **You are finished when `mvn -q -o test` prints nothing at all.** Then do Task 8, which has no test.

```
OPEN project -> cd starter -> setup (once) -> RUN tests: 7 fail (expected)
  -> READ the "Task N:" lines -> EDIT the next TODO -> RUN tests again
  -> repeat for Tasks 1-7 -> tests print nothing -> Task 8 (a note, no test) -> STOP
```

In words: open the project, run the tests, read which tasks fail, complete one task's TODO, run the tests again, and when they print nothing, write the Task 8 note.

## Checklist

Tasks 1-7 are in `starter/src/main/java/textscan/TextScan.java`. Each one is a method with a `TODO` comment.

- [ ] Task 1: `cleaned` (a helper method with one loop)
- [ ] Task 2: `countWords` (reuse your own helper)
- [ ] Task 3: both `headline` methods (overloading)
- [ ] Task 4: `reverse` (two indexes)
- [ ] Task 5: `matchingEnds` (two indexes)
- [ ] Task 6: `countChar` (recursion)
- [ ] Task 7: `report` and the `scansPerformed` counter (composing methods, static members)
- [ ] Task 8: ask, verify, record (no code, no test)

A task is done when no failure line starts with its number.
Edit only `TextScan.java`. The tests in `tests/textscan/`, the build file `starter/pom.xml` and `expected/test-summary.txt` stay as they are.

## Task steps

Each method's `TODO` comment says exactly what it must return.
The tests call every method with several inputs, including edge cases, and check only the results. They cannot see how you computed them.

### Task 1: cleaned

1. Find the method `cleaned(String raw)` and its `TODO`.
2. Build the result with one loop over `raw`. Skip spaces at the ends, and let one space through between words, however many the input has.
   Every later task calls this helper; that is the point of the task. You write the loop once, here, and never again.
3. While it is wrong, you see lines such as `Task 1: cleaned("  hello   world ") ==> expected: <hello world> but was: <>`.

### Task 2: countWords

1. Find `countWords(String text)`.
2. First call your own `cleaned(text)`.
3. While it is wrong, you see lines such as `Task 2: countWords("  one   two ")`.

<details><summary>Hint</summary>

- After calling `cleaned(text)`, counting words is just counting spaces plus one.
- Decide what the method must return for a text with no words at all, and make sure every path through your method returns a value.

</details>

### Task 3: headline and its overload

1. Find the two `headline` methods. They have the same name and different parameter lists: that is overloading.
2. Write the two-argument version first. Clean the text, and return it unchanged when it fits in `max`. Otherwise keep the first `max - 3` characters and add `"..."`.
3. Then write the one-argument version as a single line: `return headline(text, 24);`. It delegates; it must not repeat the shortening logic.
4. While they are wrong, you see lines such as `Task 3: headline("  the quick   brown fox  ", 12), exactly 12 characters`.

### Task 4: reverse

1. Find `reverse(String text)`.
2. Copy the characters with `toCharArray()`. Then reverse them with two indexes that move toward each other: one starts at the first character, the other at the last.
   Swap each pair through a temporary `char`, move both indexes inward, and stop when they meet or cross.
   Build the result with `new String(letters)`. The original String itself can never change.
3. While it is wrong, you see lines such as `Task 4: reverse("scan") ==> expected: <nacs> but was: <?>`.

### Task 5: matchingEnds

1. Find `matchingEnds(String text)`.
2. Use two indexes, one starting at the first character and one at the last.
   Compare the pair, count it when the characters match, then move both toward the middle.
   Choose the loop condition so the indexes stop before they meet or cross.
3. While it is wrong, you see lines such as `Task 5: matchingEnds("noon")`.
4. Optional: watch it run. Set a breakpoint on your comparison line, start the debugger, and step through `matchingEnds("level")`.
   Watch the two indexes close in on each other, and see why the middle character never pairs with anything.
   If your editor cannot start the test in its debugger, debug `main` instead, after changing its `matchingEnds("noon")` call to `matchingEnds("level")`.

<details><summary>Hint</summary>

Test your idea against a one-character text.

</details>

### Task 6: countChar

1. Find `countChar(String text, char target)`.
2. Write it recursively, with no loop. The base case returns 0 for an empty text. Otherwise check whether the first character matches, and add the result of the recursive call on `text.substring(1)`.
3. While it is wrong, you see lines such as `Task 6: countChar("banana", 'a')`.

<details><summary>Hint</summary>

If you see `StackOverflowError`, your recursion never reaches the base case. Check which text you recurse on, and make sure the empty-text check comes first.

</details>

### Task 7: report

1. Find `report(String text)`, and the static field `scansPerformed` at the top of the class.
2. Compose the report line exactly as the comment shows. Call your own `countWords`, `headline`, `cleaned` and `matchingEnds`; do not re-implement any of them.
3. Also increase `scansPerformed` by one on every call. The field is static, so every call updates the one shared counter that `scanCount()` reads.
4. While it is wrong, you see lines such as `Task 7: report("  the quick   brown fox  ")`.

### Task 8: ask, verify, record (no test)

No test checks this task. Your note is the result, and it is how this course expects you to work with AI assistants from now on.

1. Pick one real question from this lab, ask an AI assistant to explain it, and do not ask it to write the method for you.
   For example: why does a loop miss the last word, why does a recursion overflow, or why must two pointers stop before crossing?
2. Check the explanation yourself: run the tests, or step through the method in the debugger, and see whether the claim holds.
3. Record one or two lines in your notes stating what the assistant claimed and what you verified.
   For example: "assistant said my loop condition skipped the last pair; confirmed by stepping through matchingEnds(\"abca\")".

### About the test results

A passing run shows that each method returns the right results for the inputs the tests try.
It does not check which Java constructs your code uses, so compare your code with each task's steps yourself.

## Run the program

The utility prints one line per method. From `starter`:

```
mvn -q -o compile
java -cp target/classes textscan.TextScan
```

`TextScan.java` is a single source file with no dependencies, so Java can also run it straight from the source, with no compile step:

```
java src/main/java/textscan/TextScan.java
```

That shortcut only works because the file is alone and needs nothing else. The tests live in other files and use JUnit, so testing the project still needs Maven.
`expected/test-summary.txt` shows the finished result, including the nine lines the program prints.

## Troubleshooting

- **`there is no POM in this directory`.** The terminal is not in `starter`. From the lab folder, run `cd starter` and try again.
- **`COMPILATION ERROR`.** Your code does not compile yet. Maven names the file, then `[line,column]`, then the problem. Fix that line and run the tests again.
- **`mvn: command not found`, or `'mvn' is not recognized`.** Maven's `bin` folder is not on your `PATH`. Add it, then open a new terminal.
- **`mvn -v` shows a Java version other than 25, or you see `release version 25 not supported`.** Maven uses the JDK named by `JAVA_HOME` when it is set, otherwise the `java` on your `PATH`. Set `JAVA_HOME` to your JDK 25 folder, or unset it, then open a new terminal. Check with `java -version` and `mvn -v`.
- **`Cannot access central in offline mode`, or a missing artifact.** The setup step has not finished. Run `mvn -q dependency:go-offline` once while online.

## Cleanup (optional)

Maven writes only the `starter/target` folder, and Git already ignores it.
To delete it, run `rm -rf target` from `starter`. In Windows PowerShell, run `Remove-Item -Recurse -Force target`.
