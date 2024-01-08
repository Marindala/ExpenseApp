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
import main.interfaces.ExpenseAmountValidator;
import main.interfaces.ExpenseAmountValidatorImpl;
import main.interfaces.ControlPanel;
import main.interfaces.ControlPanelImpl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class AppExpense {
    static Scanner sc = new Scanner(System.in);
    static List<AccountManagerDto> accountManagerDto;
    static List<CategoryDto> categoryDto;
    static List<OperationTypeDto> operationTypeDto;
    static List<TransactionsDto> transactionsDto;
    static TransactionsDao transactionsDao;
    static CategoryDao categoryDao;
    static AccountManagerDao accountManagerDao;




    public static void main(String[] args) {
        try (Connection connection = JdbcConfiguration.getConnection()) {
            Statement statement = connection.createStatement();

            transactionsDao = new TransactionsDaoImpl(connection);
            transactionsDto = transactionsDao.getAll();

            categoryDao = new CategoryDaoImpl(connection);
            accountManagerDao = new AccountManagerImpl(connection);

            CategoryDao categoryDao = new CategoryDaoImpl(connection);
            OperationTypeDao operationTypeDao = new OperationTypeImpl(connection);

            accountManagerDto = accountManagerDao.getAll();
            categoryDto = categoryDao.getAll();
            operationTypeDto = operationTypeDao.getAll();

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
                        calculateExpenses(transactionsDto, categoryDto);
                        break;
                    case 4:
                        listTransactions(transactionsDto);
                        break;
                    case 5:
                        listCategory(categoryDto);
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

            listAccountManagerDtoPlain(accountManagerDto);
            System.out.print("Enter the account: ");
            int account = sc.nextInt();
            sc.nextLine();

            listCategoryPlain(categoryDto);
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
            transactionsDto = (TransactionsDto) transactionsDao.getAll();
            calculateExpenses((List<TransactionsDto>) transactionsDto, categoryDto);
        } catch (DAOException e) {
            System.out.println("Could not register the transaction: " + e.getMessage());
        }

    }

    public static void newCategory() throws InvalidExpenseException, DAOException {
        try {
            CategoryDto categoryDto = new CategoryDto();
            ExpenseAmountValidator expenseAmountValidator = new ExpenseAmountValidatorImpl();

            System.out.print("Enter the category name: ");
            String name = sc.nextLine();

            listOperationTypePlain(operationTypeDto);
            System.out.print("Enter the operation type: ");
            int operation = sc.nextInt();
            sc.nextLine();

            categoryDto.setName(name);
            categoryDto.setOperationId(operation);
            categoryDao.insert(categoryDto);
            categoryDto = (CategoryDto) categoryDao.getAll();
        } catch (Exception e) {
            System.out.println("Could not register the category: " + e.getMessage());
        }
    }

    public static void calculateExpenses(List<TransactionsDto> transactionsDto, List<CategoryDto> categoryDto) {
        ControlPanel cpanel = new ControlPanelImpl();
        System.out.println("Total Expenses: " + cpanel.calculateTotalExpenses(transactionsDto, categoryDto));
    }


    public static void listTransactions(List<TransactionsDto> transactionsDtos) throws DAOException {
        if (transactionsDtos.size() > 0) {
            for (TransactionsDto t : transactionsDtos) {
                System.out.println("Account: " + t.getAccountManagerId() + " - " +
                        accountManagerDao.getById(t.getAccountManagerId()).getDescription());
                System.out.println("Category: " + t.getCategoryId() + " - " +
                        categoryDao.getById(t.getCategoryId()).getName());
                System.out.println("Date: " + t.getDate());
                System.out.println("Amount: " + t.getAmount());
                System.out.println("-----------------");
            }
        } else {
            System.out.println("No transactions available");
        }
    }

    public static void listCategory(List<CategoryDto> categoriesDtos) throws DAOException {
        System.out.println("Category: ");
        for (CategoryDto p : categoriesDtos) {
            System.out.println("Name: " + p.getName());
            System.out.println("Operation Type: " + p.getOperationId());
            System.out.println("-----------------");
        }
        System.out.println("");
    }

    public static void listCategoryPlain(List<CategoryDto> categoryDto) {
        System.out.print("Category: ");
        for (CategoryDto p : categoryDto) {
            System.out.print(p.getId() + " - " + p.getName() + " / ");
        }
        System.out.println("");
    }

    public static void listAccountManagerDtoPlain(List<AccountManagerDto> accountManagerDtos) {
        System.out.print("Accounts: ");
        for (AccountManagerDto c : accountManagerDtos) {
            System.out.print(c.getId() + " - " + c.getDescription() + " / ");
        }
        System.out.println("");
    }

    public static void listOperationTypePlain(List<OperationTypeDto> operationTypeDtos) {
        System.out.print("Operations: ");
        for (OperationTypeDto t : operationTypeDtos) {
            System.out.print(t.getId() + " - " + t.getName() + " / ");
        }
        System.out.println("");
    }


}
