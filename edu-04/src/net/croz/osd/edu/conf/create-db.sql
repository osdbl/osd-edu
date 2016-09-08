-- Database: "osd-edu"

-- DROP DATABASE "osd-edu";

CREATE DATABASE "osd-edu"
  WITH OWNER = osd
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;



-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  username character varying(50),
  password character varying(150),
  enabled boolean,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO osd;

 -- Table: public.authorities

-- DROP TABLE public.authorities;

CREATE TABLE public.authorities
(
  id integer NOT NULL DEFAULT nextval('authorities_id_seq'::regclass),
  username character varying(50),
  authority character varying(15),
  CONSTRAINT authorities_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.authorities
  OWNER TO osd;
