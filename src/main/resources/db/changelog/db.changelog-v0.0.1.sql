--liquibase formatted sql

--changeset ted:1
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
--rollback DROP EXTENSION IF EXISTS "pgcrypto";

--changeset ted:2
CREATE TABLE IF NOT EXISTS users (
  user_id UUID NOT NULL DEFAULT gen_random_uuid(),
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash TEXT NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  logged_in_at TIMESTAMP,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  archived_at TIMESTAMP,
  CONSTRAINT users_email_unique UNIQUE (email),
  CONSTRAINT users_pkey PRIMARY KEY (user_id)
);
CREATE INDEX users_email_index ON users(email);
--rollback DROP TABLE IF EXISTS users;