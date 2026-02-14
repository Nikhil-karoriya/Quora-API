# Quora API

RESTful backend for a Q&A platform similar to Quora. This application handles user management, questions, answers, comments, and social interactions like following and liking.

Built with **Java** and **Spring Boot**.

---

## Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [API Documentation](#-api-documentation)
- [Project Structure](#-project-structure)

---

## Features

- **User Management**: Register, update profiles, and view user details.
- **Q&A Engine**: Post questions, search by tag/text, and provide answers.
- **Nested Comments**: Comment on answers and reply to other comments.
- **Social Interactions**: Follow other users and "Like" content (Questions, Answers, Comments).
- **Topics**: Create and manage topics/tags for questions.

---

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.5.10
- **Build Tool:** Gradle
- **Database:** MySQL
- **Utilities:** Project Lombok

---

## API Documentation

**Version:** 1.0.0  
**Base URL:** `/api/v1`

---

## 1. User Management
**Base Path:** `/users`

### 1.1 Register User
Register a new user in the system.

- **Endpoint:** `POST /users`
- **Request Body:**
  ```json
  {
    "username": "johndoe",
    "email": "john@example.com",
    "bio": "Software Engineer and Tech Enthusiast"
  }

- **Response (201 Created):**

  ```json
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "username": "johndoe",
    "email": "john@example.com",
    "bio": "Software Engineer and Tech Enthusiast",
    "questions": [],
    "answers": [],
    "comments": [],
    "followers": [],
    "follows": []
  }

### 1.2 Get All Users
Retrieve a list of all registered users.

- **Endpoint:** `GET /users`

- **Response (200 OK):** `List<UserResponse>`

### 1.3 Get User Info
Retrieve public profile information for a specific user.

- **Endpoint:** `GET /users/{userId}`

- **Path Parameters:** `userId: UUID of the user`

- **Response (200 OK):** `UserResponse`

### 1.4 Update User
Update profile details for an existing user.

- **Endpoint:** `PUT /users/{userId}`

- **Request Body:**

  ```JSON
  {
    "username": "john_updated",
    "email": "john_new@example.com",
    "bio": "Updated bio text"
  }
- **Response (200 OK):** `UserResponse`

## 2. Question Management
**Base Path:** `/questions`

### 2.1 Add Question
Post a new question to the platform.

- **Endpoint:** `POST /questions`

- **Request Body:**

  ```JSON
  {
    "title": "How does Spring Boot handle dependency injection?",
    "body": "I am trying to understand the internal working of IoC container...",
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "topics": ["Java", "Spring Boot", "Programming"]
  }
- **Response (201 Created):**

  ```JSON
  {
    "id": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
    "title": "How does Spring Boot handle dependency injection?",
    "body": "I am trying to understand the internal working of IoC container...",
    "username": "johndoe",
    "topics": ["Java", "Spring Boot"],
    "answers": [],
    "likes": []
  }
### 2.2 Search Questions
Search for questions by text content or specific topic tags.

- **Endpoint:** `GET /questions/search`

- **Query Parameters:**

`text (optional): Keyword search for title/body`

`tag (optional): Filter by topic`

- **Response (200 OK):** `List<QuestionResponse>`

## 3. Answer Management
**Base Path:** `/answers`

### 3.1 Get All Answers
Retrieve all answers available in the database.

- **Endpoint:** `GET /answers`

- **Response (200 OK):** `List<AnswerResponse>`

### 3.2 Add Answer
Submit an answer to a specific question.

- **Endpoint:** `POST /answers/{questionId}`

- **Path Parameters:**

`questionId: UUID of the question being answered`

- **Request Body:**

  ```JSON
  {
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "text": "Spring Boot uses the ApplicationContext to manage beans..."
  }
- **Response (201 Created):**

  ```JSON
  {
    "answerId": "987fcdeb-5432-10fe-ba98-765432101234",
    "text": "Spring Boot uses the ApplicationContext to manage beans...",
    "user": "johndoe",
    "question": "How does Spring Boot handle dependency injection?",
    "comments": [],
    "likes": []
  }
### 3.3 Edit Answer
Modify the text of an existing answer.

- **Endpoint:** `PUT /answers/{answerId}`

- **Request Body:** `(String) "Updated answer text content..."`

- **Response (200 OK):** `AnswerResponse`

## 4. Comment Management
- **Base Path:** `/comments`

### 4.1 Add Comment on Answer
Add a comment directly to an answer.

- **Endpoint:** `POST /comments/{answerId}/answer`

- **Request Body:**

  ```JSON
  {
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "answerId": "987fcdeb-5432-10fe-ba98-765432101234",
    "text": "Great explanation!",
    "parentCommentId": null
  }
- **Response (201 Created):** `CommentResponse`

### 4.2 Reply to Comment
Add a nested reply to an existing comment.

- **Endpoint:** `POST /comments/{commentId}/comment`

- **Request Body:**

  ```JSON
  {
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "answerId": "987fcdeb-5432-10fe-ba98-765432101234",
    "text": "I agree with this point.",
    "parentCommentId": "existing-comment-uuid"
  }
- **Response (201 Created):** `CommentResponse`

### 4.3 Get Comment
Retrieve a single comment by ID.

- **Endpoint:** `GET /comments/{commentId}`

- **Response (200 OK):**

  ```JSON
  {
    "commentId": "uuid-string",
    "user": "username",
    "answer": "answer-text-preview",
    "text": "Great explanation!",
    "parentComment": "parent-username-or-null",
    "childComments": []
  }
## 5. Topic Management
**Base Path:** `/topics`

### 5.1 Create Topic
Create a new topic tag for categorizing questions.

- **Endpoint:** `POST /topics`

- **Request Body:** `(String) "Microservices"`

- **Response (201 Created):** `(String) "Microservices"`

### 5.2 Get All Topics
Retrieve a list of all available topics.

- **Endpoint:** `GET /topics`

- **Response (200 OK):** `["Java", "Microservices", "Spring Boot"]`

## 6. Social Interactions
### 6.1 Follow User
Create a follower relationship between two users.

- **Endpoint:** `POST /follow/{userId}/follows/{targetUserId}`

- **Path Parameters:**

`userId: UUID of the follower`

`targetUserId: UUID of the user being followed`

`Response: 201 Created (Empty Body)`

### 6.2 Like Entity
Add a like to a Question, Answer, or Comment.

- **Endpoint:** `POST /likes/{type}/{id}`

- **Path Parameters:**

`type: The type of entity (e.g., question, answer, comment)`

`id: The UUID of the specific entity`

- **Request Body:**

  ```JSON
  {
    "userId": "123e4567-e89b-12d3-a456-426614174000"
  }
- **Response:** `201 Created (Empty Body)`

---

## Project Structure

    com.example.QuoraAPI
    ├── controllers   # REST Controllers (Endpoints)
    ├── dto           # Data Transfer Objects (Request/Response models)
    ├── services      # Business Logic
    ├── repositories  # Database Access (JPA Interfaces)
    └── models        # JPA Entities (Database Tables)
