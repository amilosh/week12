CREATE SCHEMA document_storage;

CREATE TABLE document_storage.document (
  id BIGSERIAL PRIMARY KEY ,
  title CHARACTER VARYING(256) NOT NULL ,
  type CHARACTER VARYING(128) NOT NULL ,
  upload_date TIMESTAMP default NULL ,
  url CHARACTER VARYING(256) NOT NULL
);