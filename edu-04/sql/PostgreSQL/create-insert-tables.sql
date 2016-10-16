CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(150)NOT NULL,
  enabled boolean NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (username)
);
CREATE UNIQUE INDEX ix_username ON users (username);
ALTER TABLE users OWNER TO osd;

CREATE TABLE authorities (
  id SERIAL,
  username varchar(50) not null,
  authority varchar(50) not null,
  CONSTRAINT authorities_pkey PRIMARY KEY (id),
  CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
ALTER TABLE authorities OWNER TO osd;
      
      
INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$10$N46hAq9fEQAf57cYcxe9SeCHY8a4rSClw8HVcvMaWlcnenzlAC8J2', true);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$9DqYSxCB0s4dzSvE4KGctumncBohVl921y/vLhf477tY9oWDLcoma', true);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');