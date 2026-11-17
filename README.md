# Methods and Recursion lab: a text-analysis utility

A note-taking app wants one report line per note: how many words it has, a shortened headline, and whether its ends mirror each other.
You build that utility, `TextScan.java`, in seven linked tasks. You practise writing methods, overloading, static members, two-pointer loops and recursion, and each later task reuses methods you wrote earlier.

The full learner instructions are in [`.evolv/instructions.md`](.evolv/instructions.md), which the lab platform shows as the lab's instructions.

## Layout

This repository follows the Exa coding-lab scaffold for Java.

| Path | What it is |
|---|---|
| `src/main/java/textscan/TextScan.java` | A file learners edit |
| `src/test/java/textscan/TextScanTest.java` | Visible tests |
| `.evolv/instructions.md` | Learner instructions |
| `.evolv/grade.yml` | Grading manifest, used by Run Tests and by the grader |
| `pom.xml` | Maven build: Java 17 and JUnit 5.11.3, matching the scaffold |
| `Dockerfile` | Image for local Run Tests; warms the Maven cache |
| `expected/test-summary.txt` | The result of a finished lab |

There are no held-out tests in this lab: every test is visible.

## Run the tests

On the platform, select **Run Tests**. From a terminal with JDK 17 or later and Apache Maven 3.9:

```
mvn -q test
```

All 7 tests fail until the `TODO`s are done. That is expected.
