DROP TABLE IF EXISTS OperationType;
CREATE TABLE IF NOT EXISTS OperationType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    operation VARCHAR(1) NOT NULL
);

INSERT INTO OperationType (id, name, operation) VALUES
(1, 'Income', '+'),
(2, 'Expense', '-') ;