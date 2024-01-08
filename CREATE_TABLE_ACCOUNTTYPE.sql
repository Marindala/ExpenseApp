DROP TABLE IF EXISTS AccountType;
CREATE TABLE AccountType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO AccountType (id, name) VALUES
(1, 'Cash'),
(2, 'Accounts'),
(3, 'Savings'),
(4, 'Investments') ;