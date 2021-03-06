CREATE TABLE POSTS (
  id INT IDENTITY,
  title CHAR(50),
  content CHAR(500),
  smallimg CHAR(50),
  bigimg CHAR(50),
  rating INT DEFAULT 3,
  istopnews BOOLEAN DEFAULT FALSE,
  userid INT NOT NULL,
  timestamp INT NOT NULL
);
CREATE TABLE USERS (
  id INT IDENTITY,
  firstname CHAR(50),
  lastname CHAR(50),
  email CHAR(50) NOT NULL,
  password CHAR(30) NOT NULL,
  roleid INT NOT NULL
);
CREATE TABLE POSTS_TAGS (
  id INT IDENTITY,
  postid INT NOT NULL,
  tagid INT NOT NULL
);
CREATE TABLE REF_ROLES (
  id INT IDENTITY,
  name CHAR(50) NOT NULL
);
CREATE TABLE REF_TAGS (
  id INT IDENTITY,
  name CHAR(50) NOT NULL
);

INSERT INTO REF_ROLES(name) VALUES ('admin');
INSERT INTO REF_ROLES(name) VALUES ('writer');
INSERT INTO REF_ROLES(name) VALUES ('author');
INSERT INTO REF_ROLES(name) VALUES ('corrector');

INSERT INTO REF_TAGS(name) VALUES ('music');
INSERT INTO REF_TAGS(name) VALUES ('art');
INSERT INTO REF_TAGS(name) VALUES ('sport');


