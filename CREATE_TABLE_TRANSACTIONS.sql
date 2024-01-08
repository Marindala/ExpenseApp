CREATE TABLE Transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    accountManagerId INT,
    categoryId INT,
    date VARCHAR(20),
    amount DOUBLE,

    FOREIGN KEY (accountManagerId) REFERENCES AccountManager(id),
    FOREIGN KEY (categoryId) REFERENCES Category(id)
)
   DROP TABLE IF EXISTS Transactions