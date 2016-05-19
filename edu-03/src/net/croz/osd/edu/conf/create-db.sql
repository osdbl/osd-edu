CREATE TABLE users (
  username VARCHAR(30),
  password VARCHAR(60),
  enabled BOOLEAN
);

CREATE TABLE authorities (
  username VARCHAR(30),
  authority  VARCHAR(50)
);

