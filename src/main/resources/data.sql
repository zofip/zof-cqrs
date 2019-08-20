DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Transaction;

CREATE TABLE User (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

CREATE TABLE Person (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE Transaction (
  id INT AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(250) NOT NULL,
  table_transaction VARCHAR(250) NOT NULL,
  value_transaction VARCHAR(250),
  table_record_id INT NOT NULL,
  field_table VARCHAR(250) NOT NULL,
  create_date TIMESTAMP NOT NULL,
);

INSERT INTO user (name, last_name, email) VALUES
  ('Zofi', 'P', 'zofi.p@gmail.com'),
  ('Julian', 'Garcia', 'julian.garcia@gmail.com'),
  ('Andres', 'Valencia', 'andres.valencia@gmail.com');