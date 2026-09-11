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

 
