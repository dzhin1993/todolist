DROP TABLE task IF EXISTS;
DROP TABLE user IF EXISTS;

CREATE TABLE user (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL
);

CREATE TABLE task (
  id        INTEGER IDENTITY PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  start     TIMESTAMP    NOT NULL,
  end       TIMESTAMP    NOT NULL,
  completed BOOLEAN      NOT NULL,
  user_id   INTEGER      NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);
