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
- `Library.validateIsbn(String)` — ISBN-10 validation: strips hyphens/spaces,
  requires length 10, first 9 digits, last char digit or 'X' (value 10),
  checksum sum((i+1) * digit) % 11 == 0.
- `Library.fineTier(int)` — returns None/Low/Medium/High/Severe; throws
  IllegalArgumentException for negative input.

### Test classes (21 tests, all passing)
- fineTier: None (0), Low (1,7), Medium (8,14), High (15,30), Severe (31), negative throws
- validateIsbn valid: plain, hyphenated, spaced, X, lowercase x
- validateIsbn invalid: checksum, length (short/long/empty), chars, X in middle, null

### JUnit terminal output
<img width="1191" height="292" alt="image" src="https://github.com/user-attachments/assets/ac64ca02-3c82-4138-841a-3f68da2cbc92" />

