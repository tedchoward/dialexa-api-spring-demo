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

--changeset ted:3
CREATE TABLE IF NOT EXISTS roles (
  role_id UUID NOT NULL DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL,
  CONSTRAINT roles_name_unique UNIQUE (name),
  CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);
CREATE INDEX role_names_index ON roles(name);
--rollback DROP TABLE IF EXISTS roles;

--changeset ted:4
CREATE TABLE IF NOT EXISTS user_roles (
  user_id UUID NOT NULL,
  role_id UUID NOT NULL,
  CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT user_roles_user_id_foreign FOREIGN KEY (user_id) REFERENCES users(user_id),
  CONSTRAINT user_roles_role_id_foreign FOREIGN KEY (role_id) REFERENCES roles(role_id)
);
--rollback DROP TABLE IF EXISTS user_roles;

--changeset ted:5
CREATE TABLE IF NOT EXISTS sessions (
  session_id UUID NOT NULL DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL,
  expires_at TIMESTAMPTZ NULL,
  created_at TIMESTAMPTZ NULL,
  updated_at TIMESTAMPTZ NULL,
  CONSTRAINT sessions_pkey PRIMARY KEY (session_id),
  CONSTRAINT sessions_user_id_foreign_key FOREIGN KEY (user_id) REFERENCES users(user_ID)
);
--rollback DROP TABLE IF EXISTS sessions;