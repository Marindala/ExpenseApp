package main.interfaces;

import main.dao.dto.CategoryDto;
import main.dao.dto.AccountManagerDto;
import main.dao.dto.TransactionsDto;

import java.util.List;

public class ControlPanelImpl implements ControlPanel {
    @Override
    public double calculateTotalExpenses(List<TransactionsDto> transactionsDto, List<CategoryDto> categoryDto) {
        return transactionsDto.stream()
                .mapToDouble(el -> el.getAmount() * getOperationValue(el.getAccountManagerId(), categoryDto))
                .sum();
    }

    public int getOperationValue(int accountManagerId, List<CategoryDto> categoryDto) {
        int type = categoryDto.stream()
                .filter(el -> el.getId() == accountManagerId)
                .mapToInt(el -> el.getOperationId())
                .sum();
        return (type == 1) ? 1 : -1;
    }
}
