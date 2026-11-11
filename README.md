# Methods and Recursion lab: a text-analysis utility

## Goal

Build one small text-analysis utility, `TextScan.java`, in seven linked tasks.
A note-taking app wants one report line per note: how many words it has, a shortened headline and whether its ends mirror each other.
Along the way you practise writing methods, overloading, static members, two-pointer loops and recursion, and every later task reuses the methods you wrote earlier.
You build and test it with the real JDK and Maven on your own computer.

## Files

**Edit exactly one file.** Every `TODO Task N` comment in it marks a task; the test failure lines carry the same task numbers:

- `starter/src/main/java/textscan/TextScan.java` - Tasks 1-7, one method per task; each method you finish becomes part of the one report line the utility produces. Task 8 is a workflow task with no code.

**Do not change anything else.** In particular:

- `tests/textscan/TextScanTest.java` - the public tests your methods must pass
- `starter/pom.xml` - the build is prewired
- `expected/test-summary.txt` - the finished-state reference

## Prerequisites

You need JDK 25 and Apache Maven 3.9 or later, the same setup as the Java Basics lab.
Check both in a new terminal:

```
java -version
mvn -v
```

- `java -version` must report version 25.
- `mvn -v` must report Apache Maven 3.9 or later, and its `Java version:` line must also say 25.

This lab works the same on macOS, Linux and Windows.
On Windows, use PowerShell or Command Prompt; the commands are the same.
No Docker or other container tool is needed.

## Setup (once, needs the internet)

Open a terminal in the lab's root folder - the one that holds this README, `starter/` and `tests/` - and change into `starter` with `cd starter`; every command in this lab runs there. Then run:

```
mvn -q dependency:go-offline
```

It prints nothing when it succeeds.
If you finished the Java Basics lab on this machine, it finds everything already downloaded.
After this step, everything else in the lab runs offline.

## Tasks

Open the lab's root folder in your IDE or editor; it holds one Maven project, `starter/pom.xml`, and if your editor asks, open or import that file as a Maven project, then open `starter/src/main/java/textscan/TextScan.java`. Whatever your editor shows, the test command below, run in a terminal in `starter`, is the result that counts.
Each task is one method with a `TODO` comment; its comment says exactly what the method must return.
The tests call every method with several inputs, including edge cases, and check only the results; they cannot see how you computed them.

After each task, run the one test command from the `starter` folder:

```
mvn -q -o test
```

Every failure line starts with its task number, so you can work through the tasks in order.

### Task 1: cleaned

1. Find the method `cleaned(String raw)` and its `TODO`.
2. Build the result with one loop over `raw`: skip spaces at the ends, and let one space through between words however many the input has.
   A helper that every later task calls is the point of this task; you write the loop once, here, and never again.
3. While it is wrong, you see lines such as `Task 1: cleaned("  hello   world ") ==> expected: <hello world> but was: <>`.
4. Done when no line starting with `Task 1` appears.

### Task 2: countWords

1. Find `countWords(String text)`.
2. First call your own `cleaned(text)`; then counting words is just counting spaces plus one.
   Decide what the method must return for a text with no words at all, and make sure every path through your method returns a value.
3. While it is wrong, you see lines such as `Task 2: countWords("  one   two ")`.
4. Done when no line starting with `Task 2` appears.

### Task 3: headline and its overload

1. Find the two `headline` methods. Same name, different parameter lists: that is overloading.
2. Write the two-argument version first: clean the text, return it unchanged when it fits in `max`, otherwise keep the first `max - 3` characters and add `"..."`.
3. Then write the one-argument version as a single line: `return headline(text, 24);`. It delegates; it must not repeat the shortening logic.
4. While they are wrong, you see lines such as `Task 3: headline("  the quick   brown fox  ", 12), exactly 12 characters`.
5. Done when no line starting with `Task 3` appears.

### Task 4: reverse

1. Find `reverse(String text)`.
2. Copy the characters with `toCharArray()`, then reverse with two indexes that move toward each other: one starts at the first character, one at the last; swap the pair through a temporary `char`, move both inward, and stop when they meet or cross. Build the result with `new String(letters)` - the original String itself can never change.
3. While it is wrong, you see lines such as `Task 4: reverse("scan") ==> expected: <nacs> but was: <?>`.
4. Done when no line starting with `Task 4` appears.

### Task 5: matchingEnds

1. Find `matchingEnds(String text)`.
2. Use two indexes: one starting at the first character, one at the last.
   Compare the pair, count when they match, then move both toward the middle.
   Choose the loop condition so the indexes stop before they meet or cross; test your idea against a one-character text.
3. While it is wrong, you see lines such as `Task 5: matchingEnds("noon")`.
4. Done when no line starting with `Task 5` appears.
5. **Watch it run.** Set a breakpoint on your comparison line in the IDE, debug the test, and step through `matchingEnds("level")`: watch the two indexes close in on each other and see why the middle character never pairs with anything. If your editor cannot start that test in its debugger, debug `main` instead, with `matchingEnds("level")` in place of `matchingEnds("noon")` in its call.

### Task 6: countChar

1. Find `countChar(String text, char target)`.
2. Write it recursively, with no loop: the base case returns 0 for an empty text; otherwise check whether the first character matches and add the recursive call on `text.substring(1)`.
3. If you see `StackOverflowError`, your recursion never reaches the base case; check which text you recurse on.
4. While it is wrong, you see lines such as `Task 6: countChar("banana", 'a')`.
5. Done when no line starting with `Task 6` appears.

### Task 7: report

1. Find `report(String text)` and the static field `scansPerformed` at the top of the class.
2. Compose the report line exactly as the comment shows, calling your own `countWords`, `headline`, `cleaned` and `matchingEnds`; do not re-implement any of them.
3. Also increment `scansPerformed` by one on every call. The field is static, so every call updates the one shared counter that `scanCount()` reads.
4. While it is wrong, you see lines such as `Task 7: report("  the quick   brown fox  ")`.
5. Done when no line starting with `Task 7` appears and the whole run prints nothing.

### Task 8: ask, verify, record (no test)

No test checks this task; your note is the result, and it is how this course expects you to work with assistants from here on.

1. Pick one real question from this lab - why a loop misses the last word, why a recursion overflows, why two pointers must stop before crossing - and ask an AI assistant to EXPLAIN it, not to write the method for you.
2. Verify the explanation yourself: run the tests, or step through the method in the debugger, and see whether the claim holds.
3. Record one or two lines - in your notes or the commit message - stating what the assistant claimed and what you verified, for example: "assistant said my loop condition skipped the last pair; confirmed by stepping through matchingEnds(\"abca\")".

## Test

The one test command, run from the `starter` folder, is:

```
mvn -q -o test
```

- Before you start, all seven tests fail, and Maven ends with `Tests run: 7, Failures: 7`.
- Each test lists every input that gave a wrong result. A passing run shows that each method returns the right results for those inputs; it does not check which Java constructs your code uses, so compare your code with each task's steps yourself.
- If your code does not compile, Maven prints `COMPILATION ERROR` with the file, then `[line,column]`, then the compiler's message. Fix that line and run the test again.

You can also run the utility itself, which prints one line per method:

```
mvn -q -o compile
java -cp target/classes textscan.TextScan
```

Because `TextScan.java` is a single source file with no dependencies, the single-file shortcut also works, straight from the source and with no compile step:

```
java src/main/java/textscan/TextScan.java
```

That shortcut is enough exactly here - one file, nothing external; the tested project as a whole still needs Maven, because the tests live in other files and use JUnit.

## Expected output

When every task is complete, `mvn -q -o test` prints nothing and exits with status 0.
`expected/test-summary.txt` shows the full expected result, including the nine lines the program prints.

## Offline note

Only the setup step uses the network.
The test command uses `-o` (offline), so it never downloads anything.
If it reports that an artifact is missing from the local repository, run the setup step once more while online.

## Cleanup

The lab writes only the `starter/target` folder (compiled classes and test reports). Delete it from the `starter` folder:

- macOS and Linux: `rm -rf target`
- Windows PowerShell: `Remove-Item -Recurse -Force target`

The JUnit and plugin downloads stay in your local Maven repository (`~/.m2/repository` by default) so later Java labs can reuse them.

`starter/.gitignore` already keeps Maven's `starter/target` folder out of Git. Your editor and operating system may also create local files in the lab's root folder, such as `.idea/`, `.vscode/` or `.DS_Store`; they are not part of the lab, so if `git status` lists any of them, add them to a `.gitignore` file in the lab's root folder instead of committing them.

## Troubleshooting

- **`mvn: command not found`, or `'mvn' is not recognized`.** Maven's `bin` folder is not on your `PATH`. Add it, then open a new terminal.
- **`mvn -v` shows a Java version other than 25.** Maven uses the JDK named by `JAVA_HOME` when it is set, otherwise the `java` on your `PATH`. Set `JAVA_HOME` to your JDK 25 folder, or unset it, then open a new terminal.
- **`release version 25 not supported`.** Maven is running on an older JDK. Fix it as in the previous item.
- **`Cannot access central in offline mode`, or a missing artifact.** The setup step has not completed. Run `mvn -q dependency:go-offline` once while online.
- **`StackOverflowError` in Task 6.** The recursion has no reachable base case. Return 0 for the empty text before doing anything else.
