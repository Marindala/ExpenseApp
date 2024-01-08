package main.interfaces;

import main.exceptions.InvalidExpenseException;
@FunctionalInterface
public interface ExpenseAmountValidator {
    boolean validateAmount(double amount) throws InvalidExpenseException;
}
