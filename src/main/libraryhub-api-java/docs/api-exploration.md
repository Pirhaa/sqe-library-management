# API Manual Exploration (Task 2)

## 1. Valid Request: Add a Book
**Request:** POST /books
**Body:** 
{
  "title": "Clean Code",
  "isbn": "12345",
  "author": "Robert Martin",
  "totalCopies": 2
}
**Response:** 201 Created
**Observation:** Book successfully add ho gayi.

## 2. Invalid Request: Missing ISBN
**Request:** POST /books
**Body:** 
{
  "title": "Book Without ISBN",
  "author": "Unknown",
  "totalCopies": 1
}
**Response:** 400 Bad Request
**Body:** {"error":"isbn is required"}

## 3. Invalid Request: Missing Title
**Request:** POST /books
**Body:** 
{
  "isbn": "999",
  "author": "Unknown",
  "totalCopies": 1
}
**Response:** 400 Bad Request
**Body:** {"error":"title is required"}