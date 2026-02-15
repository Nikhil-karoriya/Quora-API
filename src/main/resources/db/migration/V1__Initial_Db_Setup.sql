/*
  Flyway Migration Script: V1__init_schema.sql
  Database: MySQL
  Description: Initial schema setup (Idempotent)
*/

-- 1. Create User Table
CREATE TABLE IF NOT EXISTS `user` (
    `id` BINARY(16) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255),
    `bio` VARCHAR(255),
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `uk_user_username` UNIQUE (`username`),
    CONSTRAINT `uk_user_email` UNIQUE (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. Create Topic Table
CREATE TABLE IF NOT EXISTS `topic` (
    `id` BINARY(16) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `uk_topic_name` UNIQUE (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Create Follow Table
CREATE TABLE IF NOT EXISTS `follow` (
    `id` BINARY(16) NOT NULL,
    `follower_id` BINARY(16) NOT NULL,
    `followee_id` BINARY(16) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_follow_follower` FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`),
    CONSTRAINT `fk_follow_followee` FOREIGN KEY (`followee_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. Create Question Table
CREATE TABLE IF NOT EXISTS `question` (
    `id` BINARY(16) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `body` VARCHAR(255),
    `user_id` BINARY(16) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_question_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. Create Topic_Question Table
CREATE TABLE IF NOT EXISTS `topic_question` (
    `id` BINARY(16) NOT NULL,
    `topic_id` BINARY(16) NOT NULL,
    `question_id` BINARY(16) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_tq_topic` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
    CONSTRAINT `fk_tq_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. Create Answer Table
CREATE TABLE IF NOT EXISTS `answer` (
    `id` BINARY(16) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    `question_id` BINARY(16) NOT NULL,
    `user_id` BINARY(16) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_answer_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
    CONSTRAINT `fk_answer_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7. Create Comment Table
CREATE TABLE IF NOT EXISTS `comment` (
    `id` BINARY(16) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    `answer_id` BINARY(16) NOT NULL,
    `user_id` BINARY(16) NOT NULL,
    `parent_comment_id` BINARY(16),
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_comment_answer` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
    CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `fk_comment_parent` FOREIGN KEY (`parent_comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 8. Create Like_Question Table
CREATE TABLE IF NOT EXISTS `like_question` (
    `id` BINARY(16) NOT NULL,
    `question_id` BINARY(16) NOT NULL,
    `user_id` BINARY(16) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_lq_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
    CONSTRAINT `fk_lq_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 9. Create Like_Answer Table
CREATE TABLE IF NOT EXISTS `like_answer` (
    `id` BINARY(16) NOT NULL,
    `answer_id` BINARY(16) NOT NULL,
    `user_id` BINARY(16) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_la_answer` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
    CONSTRAINT `fk_la_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 10. Create Like_Comment Table
CREATE TABLE IF NOT EXISTS `like_comment` (
    `id` BINARY(16) NOT NULL,
    `comment_id` BINARY(16) NOT NULL,
    `user_id` BINARY(16) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `updated_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_lc_comment` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
    CONSTRAINT `fk_lc_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;