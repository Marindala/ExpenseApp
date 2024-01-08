DROP TABLE IF EXISTS Category;
CREATE TABLE Categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    operationTypeId INT,

    FOREIGN KEY (operationTypeId) REFERENCES OperationType(id)
);

INSERT INTO Category (name, operationTypeId) VALUES
('Salary', 1),
('Other', 1),
('Food', 2),
('Entertainment', 2);