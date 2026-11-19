# Code Review Checklist

## 1. Correctness
- [ ] Does the code do what it claims to do? (Verify logic and edge cases)
- [ ] Are nulls or empty collections handled safely?

## 2. Tests
- [ ] Are there tests for the new code?
- [ ] Do the tests cover edge cases (empty, single, multiple items)?
- [ ] Are the tests meaningful, or are they just asserting the implementation?

## 3. Readability
- [ ] Are variable and method names clear and descriptive?
- [ ] Is the code easy to follow without needing extra comments?
- [ ] Are comments present where the logic is complex?

## 4. Design
- [ ] Does the code fit into the existing architecture?
- [ ] Is there any code duplication that can be refactored?
- [ ] Are SOLID principles followed?

## 5. Risk (Security & Performance)
- [ ] Are there any security concerns (e.g., SQL injection, exposed data)?
- [ ] Is the code optimized for performance (e.g., no unnecessary loops)?
- [ ] Does it break any existing backward compatibility?

## Lessons Learned (Retrospective)

**Which checklist item caught the most valuable issue?**
The "Correctness" checklist item (specifically looking for off-by-one errors and edge cases) was the most valuable. It helped catch the division-by-zero bug when a library has only 1 member, and the logical error of dividing by `totalMembers` instead of `totalMembers - 1`. 

**One checklist item I'd add for next time:**
"Documentation and Docstrings: Ensure every public method has a Javadoc explaining its parameters and return type." This would have caught the missing docstring issue immediately.