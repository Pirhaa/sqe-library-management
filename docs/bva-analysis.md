
# Lab 6 — Boundary Value Analysis

## 1. fineTier() Boundary Analysis

| Boundary | Value - 1 | Boundary Value | Value + 1 | Expected Results |
|---|---:|---:|---:|---|
| Domain edge 0 | -1 | 0 | 1 | Exception, None, Low |
| Low/Medium: 8 | 7 | 8 | 9 | Low, Medium, Medium |
| Medium/High: 15 | 14 | 15 | 16 | Medium, High, High |
| High/Severe: 31 | 30 | 31 | 32 | High, Severe, Severe |

## 2. Borrow Limit Boundary Analysis

Maximum allowed books = 5

| Boundary | Current Books | Expected Result |
|---|---:|---|
| Below limit | 4 | Can borrow one more |
| At limit | 5 | Cannot borrow one more |
| Above limit | 6 | Cannot borrow one more |

## 3. ISBN Length Boundary Analysis

Required ISBN length = 13 digits

| Boundary | ISBN Length | Expected Result |
|---|---:|---|
| Below valid range | 11 | Invalid |
| Below valid range | 12 | Invalid |
| Valid boundary | 13 | Valid format if all characters are digits |
| Above valid range | 14 | Invalid |
| Above valid range | 15 | Invalid |

## Conclusion

Boundary Value Analysis tests values at and around the limits of valid input ranges. It helps identify off-by-one errors and incorrect boundary conditions.
