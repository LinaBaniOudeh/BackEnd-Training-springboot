DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
                        id INT AUTO_INCREMENT  PRIMARY KEY,
                        name VARCHAR(250) NOT NULL,
                        dep INT NOT NULL);