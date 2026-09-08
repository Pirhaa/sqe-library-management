

# fine_tier Function - Days Overdue to Fine Tier

**Business Rule:** 
- 0 days = "None"
- 1-7 days = "Low"
- 8-14 days = "Medium"
- 15-30 days = "High"
- 31+ days = "Severe"
- Negative days = ValueError

| Class ID | Description | Valid/Invalid | Representative Value |
|----------|-------------|---------------|---------------------|
| C10 | days < 0 | Invalid | -3 |
| C11 | days = 0 | Valid | 0 |
| C12 | 1-7 days | Valid | 4 |
| C13 | 8-14 days | Valid | 10 |
| C14 | 15-30 days | Valid | 20 |
| C15 | 31+ days | Valid | 45 |
