CREATE ROLE osd LOGIN
  ENCRYPTED PASSWORD 'md57e206c4ba2263b80887ebbe3e1821e4e'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

CREATE DATABASE "osd-edu"
  WITH OWNER = osd
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;
      