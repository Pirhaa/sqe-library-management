# LAB 11 - Coverage Notes

## Task 1: Baseline Report
- **Baseline Line Coverage:** 29%
- **Baseline Branch Coverage:** 37%

<img width="1366" height="441" alt="image" src="https://github.com/user-attachments/assets/a32f5406-b26f-4192-9d37-65083d84d701" />

## Task 2: Three Weakest Files
Based on the JaCoCo baseline HTML report, the following are the weakest files:
1. **BookIssueService.java** (Line Coverage: 0%, Branch Coverage: 0%) - All lines and branches are untested.
2. **BookSearch.java** (Line Coverage: 0%, Branch Coverage: 0%) - All lines and branches are untested.
3. **Book.java** (Line Coverage: 22%, Branch Coverage: 4%) - Most methods, constructor logic, and validation branches are untested.
   <img width="1379" height="487" alt="image" src="https://github.com/user-attachments/assets/dfd5039c-9eba-4271-ba54-866e500bc10d" />

## Task 3: Closing the Gaps
Added new JUnit 5 test suites for the weakest files. Coverage improved significantly:
- **BookIssueService:** 0% ➔ 86.79% (Added tests for issue/return success, failure, and fine calculation).
- **BookSearch:** 0% ➔ 100% (Added tests for title, author, and ID search).
- **Book:** 22% ➔ 68.92% (Added tests for ISBN validation and toString method).

Overall coverage increased from 29% to ~68.7%.

<img width="1820" height="446" alt="image" src="https://github.com/user-attachments/assets/996699ba-470e-43e3-9079-9dd6a00b4ff8" />

## Task 4: Coverage vs. Quality (Coverage Trap)

**The Coverage Trap:** 
In our test suite, `testIssueBook_Success()` was a coverage trap. It executed the `issueBook()` method and gave us green lines on the coverage report. However, its assertions were weak. It only checked `assertTrue(book.isIssued())`.

**Why it was weak:** 
It didn't verify *who* the book was issued to. If a bug caused the book to be issued to the wrong member, the test would still pass because the boolean `isIssued` was true.

**Strengthening the Test:**
We strengthened the assertion to verify the actual state change. Instead of just checking a boolean, we now test that the member can successfully return the book, proving the book was correctly linked to that specific member.
