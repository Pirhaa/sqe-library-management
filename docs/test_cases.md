# Test Cases — Library Management System

| ID | Title | Requirement | Preconditions | Steps | Expected | Priority | Type |
|----|-------|-------------|----------------|-------|----------|----------|------|
| TC-001 | Add member with new roll number | addMember() | No member exists with roll number R1 | 1. Call addMember() with roll number R1 | Member is added, returns true | High | Positive |
| TC-002 | Add member with duplicate roll number | addMember() | Member with roll number R1 already added | 1. Call addMember() again with roll number R1 | Returns false, prints "Roll number already exists" | High | Negative |
| See Issue #14 | Add member with blank/invalid roll number | addMember() | No member exists | 1. Call addMember() with empty/invalid roll number | System should reject or handle gracefully (no crash) | Medium | Negative |
| TC-004 | Issue book when book is available | issueBook() | Book is not issued, member has not reached borrow limit | 1. Call issueBook(book, member) | Returns true, book marked as issued, prints "Book issued to [member]" | High | Positive |
| TC-005 | Issue book that is already issued | issueBook() | Book is already issued to some member | 1. Call issueBook(book, anotherMember) | Returns false, prints "Book is already issued!" | High | Negative |
| TC-006 | Return book that is currently on loan | returnBook() | Book is issued to member M1 | 1. Call returnBook(book, M1) | Returns true, book marked as not issued, prints "Book returned successfully!" | High | Positive |
| TC-007 | Return book not on loan by that member | returnBook() | Book is issued to member M1 | 1. Call returnBook(book, M2) (different member) | Returns false, prints "Book not issued to this member!" | High | Negative |
| TC-008 | Member borrowing at the allowed limit | canBorrow() / issueBook() | Member has borrowed one less than the max allowed books | 1. Call issueBook() for one more book | Returns true, book is issued (member is exactly at limit after this) | Medium | Positive |
| TC-009 | Member borrowing beyond the allowed limit | canBorrow() / issueBook() | Member has already reached max allowed books | 1. Call issueBook() for one more book | Returns false, prints "Member reached max books limit!" | High | Negative |
| TC-010 | Fine calculation for zero days overdue | calculateFine() | Book issued, daysBorrowed = 0 (well within 14-day limit) | 1. Call calculateFine(bookId) | Returns 0.0 (no fine) | Medium | Positive |
| TC-011 | Fine calculation for mid-range overdue | calculateFine() | Book issued, daysBorrowed = 20 (6 days over the 14-day limit) | 1. Call calculateFine(bookId) | Returns 12.0 (6 days × ₹2/day) | Medium | Positive |
| TC-012 | Fine calculation at overdue-tier boundary | calculateFine() | Book issued, daysBorrowed = 14 (exactly at the limit) | 1. Call calculateFine(bookId) | Returns 0.0 (boundary day is not overdue yet) | Medium | Positive |
