  ** ( Buisness rule for number of book loan) **
A member may have between 0 and 5 books on loan simultaneously.

  **Equivalence Classes:**

| Class ID | Description | Valid/Invalid | Representative Value |
|----------|-------------|---------------|---------------------|
| C1 | 0 to 5 books | Valid | 3 |
| C2 | 6 or more books | Invalid | 7 |
| C3 | negative number| Invalid | -2|
___________________________________________________________________


 ** ( Buisness rule for a book's ISBN field) **
  ISBN field must be exactly 13 numeric digits, no letters or symbols. 

  
    
 **Equivalence Classes:**
 
| Class ID | Description | Valid/Invalid | Representative Value |
|----------|-------------|---------------|---------------------|
| C1 | Exactly 13 numeric digits | Valid | 0123456789468 |
| C2 | Empty string| Invalid |  |
| C3 |less than 13 numeric digits| Invalid | 2468|
| C4 | contains symbols or letters| Invalid | 012345@678-9$|
___________________________________________________________________

## Task 4 — ISBN Validation & fineTier (JUnit)

### Implementation
- `Library.validateIsbn(String)` — Lab 5 rule: exactly 13 numeric digits,
  no letters or symbols. Strips hyphens/spaces, then checks length == 13
  and every character is a digit. No checksum.
- `Library.fineTier(int)` — returns None (0) / Low (1–7) / Medium (8–14) /
  High (15–30) / Severe (31+); throws IllegalArgumentException for negative input.

### Test classes (20 tests, all passing)
- fineTier — None (0), Low (1,7), Medium (8,14), High (15,30), Severe (31), negative throws
- validateIsbn valid — plain 13-digit, hyphenated, spaced
- validateIsbn invalid — empty, null, length 11/12/14/15, letters, symbols

### JUnit terminal output
<img width="1216" height="285" alt="image" src="https://github.com/user-attachments/assets/8f159150-e41a-496c-9695-e0533dbe3da0" />


