package main.interfaces;

import main.dao.dto.CategoryDto;
import main.dao.dto.TransactionsDto;

import java.util.List;


public interface ControlPanel {
    double calculateTotalExpenses(List<TransactionsDto> transactionsDtos, List<CategoryDto> categoriesDtos);
}
