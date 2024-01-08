package main.interfaces;

import main.exceptions.InvalidExpenseException;

import java.util.Objects;

public class ExpenseAmountValidatorImpl implements ExpenseAmountValidator {
    @Override
    public boolean validateAmount(double amount) throws InvalidExpenseException {
        if (amount < 0 || !Objects.nonNull(amount) ) {
            throw new InvalidExpenseException("The amount must be greater than or equal to zero.");
        }
        return true;
    }
}
