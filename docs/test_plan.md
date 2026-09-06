# Test Plan — Library Management System

# 1. Introduction
This document is the test plan for the Library Management System project. It covers adding members, issuing books, returning books, checking the borrow limit,
and fine calculation for late returns. The aim is to make sure the system works correctly with both correct and incorrect inputs.

# 2. Test Items
- Adding a new member
- Issuing a book to a member
- Returning a book
- Checking if a member has reached their borrow limit
- Calculating fine for late books

# 3. Features to be Tested
- A member cannot be added twice (duplicate check)
- A book that is already issued cannot be issued again
- A member who reached their borrow limit cannot borrow more
- A book that is not issued cannot be returned
- A book can only be returned by the member who borrowed it
- No fine if the book is returned within 14 days
- Fine is charged ($2 per day) if the book is returned after 14 days

# 4. Features Not to be Tested
- No UI to test, this is a simple Java program, not an app.
- Performance testing is not needed for this project.

# 5. Approach
Testing will be done manually by running the Java code and checking the output for each case. 
Both correct (valid) and incorrect (invalid) inputs will be tested, including checking the fine amount for different days borrowed.

# 6. Pass/Fail Criteria
A test passes if the output matches what we expected. The system is ready when most tests (around 95%) pass and there are no major bugs left.

# 7. Test Deliverables
- This test plan
- Test cases document
- Traceability matrix (linking requirements to test cases)
- Results of running the tests, with GitHub issues for anything that failed

# 8. Environmental Needs
- The project code from GitHub
- Java installed on the computer to run it
- GitHub account to log issues

# 9. Schedule
- Writing test plan: 1 hour
- Writing test cases: 1 hour 15 min
- Traceability matrix: 30 min
- Running tests: 35 min

# 10. Risks
   No idea, till now!
