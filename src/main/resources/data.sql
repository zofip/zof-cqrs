DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS Transaction;

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

CREATE TABLE Transaction (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  type VARCHAR(250) NOT NULL,
  table_transaction VARCHAR(250) NOT NULL,
  value_transaction VARCHAR(250),
  field_table VARCHAR(250) NOT NULL,
  create_date TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO user (name, last_name, email) VALUES
  ('Sofia', 'Peña', 'sofia.pena@techandsolve.com'),
  ('Julian', 'Garcia', 'julian.garcia@gmail.com'),
  ('Andres', 'Valencia', 'andres.valencia@gmail.com');