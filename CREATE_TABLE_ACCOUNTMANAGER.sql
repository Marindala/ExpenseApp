DROP TABLE IF EXISTS AccountManager;
CREATE TABLE AccountManager (
    id INT PRIMARY KEY AUTO_INCREMENT,
    accountTypeId INT,
    description VARCHAR(20),
    initialBalance DOUBLE
);

ALTER TABLE AccountManager
ADD CONSTRAINT FK_AccountType
FOREIGN KEY (accountTypeId) REFERENCES AccountType(id);

INSERT INTO AccountManager (id, accountTypeId, description, initialBalance) VALUES
(1, 1, 'Cash', 0.0),
(2, 2, 'Marina', 250.50),
(3, 3, 'Savings', 0.0),
(4, 4, 'Investments', 0.0);