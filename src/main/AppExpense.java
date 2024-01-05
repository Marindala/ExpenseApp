package main;

import main.config.JdbcConfiguration;
import main.dao.CategoryDao;
import main.dao.AccountManagerDao;
import main.dao.OperationTypeDao;
import main.dao.TransactionsDao;
import main.dao.dto.CategoryDto;
import main.dao.dto.AccountManagerDto;
import main.dao.dto.OperationTypeDto;
import main.dao.dto.TransactionsDto;
import main.dao.impl.CategoryDaoImpl;
import main.dao.impl.AccountManagerImpl;
import main.dao.impl.OperationTypeImpl;
import main.dao.impl.TransactionsDaoImpl;
import main.exceptions.DAOException;
import main.exceptions.InvalidExpenseException;
import interfaces.ExpenseAmountValidator;
import interfaces.ExpenseAmountValidatorImpl;
import interfaces.ControlPanel;
import interfaces.ControlPanelImpl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class AppExpense {
    static Scanner sc = new Scanner(System.in);
    static List<AccountManagerDto> accountManagerDtos;
    static List<CategoryDto> categoriesDtos;
    static List<OperationTypeDto> operationTypeDtos;
    static List<TransactionsDto> transactionsDtos;
    static TransactionsDao transactionsDao;
    static CategoryDao categoriesDao;
    static AccountManagerDao accountManagerDao;
}



        public static void main(String[] args) {
            try (Connection connection = JdbcConfiguration.getConnection()) {
                Statement statement = connection.createStatement();

                transactionsDao = new TransactionsDaoImpl(connection);
                transactionsDtos = transactionsDao.getAll();

                categoryDao = new CategoryDaoImpl(connection);
                accountManagerDao = new AccountManagerImpl(connection);

                CategoryDao categoriesDao = new CategoryDaoImpl(connection);
                OperationTypeDao operationTypeDao = new OperationTypeImpl(connection);

                accountManagerDtos = accountManagerDao.getAll();
                categoriesDtos = categoriesDao.getAll();
                operationTypeDtos = operationTypeDao.getAll();

                int option;

                do {
                    option = menu();
                    switch (option) {
                        case 0:
                            break;
                        case 1:
                            newTransaction();
                            break;
                        case 2:
                            newCategory();
                            break;
                        case 3:
                            calculateExpenses(transactionsDtos, categoriesDtos);
                            break;
                        case 4:
                            listTransactions(transactionsDtos);
                            break;
                        case 5:
                            listCategories(categoriesDtos);
                            break;
                    }
                } while (option != 0);

                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static int menu() {
            int menuOption;
            System.out.println("Menu");
            System.out.println("1 - Enter New Transaction");
            System.out.println("2 - Enter New Category");
            System.out.println("3 - Calculate Expenses");
            System.out.println("4 - List Transactions");
            System.out.println("5 - List Categories");
            System.out.println("0 - Exit");
            menuOption = sc.nextInt();
            sc.nextLine();

            return menuOption;
        }

        public static void newTransaction() throws InvalidExpenseException, DAOException {
            boolean flag = false;
            double amount;

            try {
                TransactionsDto transactionsDto = new TransactionsDto();
                ExpenseAmountValidator expenseAmountValidator = new ExpenseAmountValidatorImpl();

                listAccountManagerDtoPlain(accountManagerDtos);
                System.out.print("Enter the account: ");
                int account = sc.nextInt();
                sc.nextLine();

                listCategoriesPlain(categoriesDtos);
                System.out.print("Enter the category: ");
                int category = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter the date (dd-mm-yyyy): ");
                String date = sc.nextLine();

                while (true) {
                    System.out.print("Enter the amount: ");
                    amount = sc.nextDouble();
                    if (expenseAmountValidator.validateAmount(amount)) {
                        break;
                    }
                    sc.nextLine();
                }

                transactionsDto.setAccountManagerId(account);
                transactionsDto.setCategoryId(category);
                transactionsDto.setDate(date);
                transactionsDto.setAmount(amount);
                transactionsDao.insert(transactionsDto);
                transactionsDtos = transactionsDao.getAll();
                calculateExpenses(transactionsDtos, categoriesDtos);
            } catch (DAOException e) {
                System.out.println("Could not register the transaction: " + e.getMessage());
            }

    }
}
