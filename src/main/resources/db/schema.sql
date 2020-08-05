DROP TABLE todo IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE users (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL
);

CREATE TABLE todo (
  id        INTEGER IDENTITY PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  date_time TIMESTAMP    NOT NULL,
  completed BOOLEAN      NOT NULL,
  user_id   INTEGER      NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX todo_date_time ON todo (date_time);
