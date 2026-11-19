# TDD Log — Lab 8 (BookRanking)

## Cycles Overview

| Cycle | Behaviour | RED | GREEN | REFACTOR |
|-------|-----------|-----|-------|----------|
| 1 | Empty catalog returns empty list | `880be44` | `6370fe9` | - |
| 2 | Single book returns list with that book | `1a7e601` | `3dfc4d9` | - |
| 3 | Descending order by borrow count | `18db584` | `18db584` | `18db584` |
| 4 | Ties sorted alphabetically by title | `f95694f` | `f95694f` | `f95694f` |
| 5 | Book with no borrow record counts as 0 | `d7b6480` | `d7b6480` | - |

##Hashes get from/retrieved via `git log --format="%h | %s"`.
<img width="1476" height="445" alt="image" src="https://github.com/user-attachments/assets/11b0cbe4-6edc-41f1-b235-b0d409d22c9f" />

## Reflection

Writing the first test before the actual code helped me set up the method signature correctly from the start. Keeping the GREEN phase simple made it easy to review my commits step by step. The tie-break and missing record tests were very helpful because they caught real errors like the NullPointerException, which I would have missed if I only tested normal cases. Also, running the full test suite after every refactor gave me confidence that I didn't break any old code. Overall, TDD helped me write safer code and the tests act as good documentation for how the ranking works.
