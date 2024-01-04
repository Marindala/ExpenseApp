package main.dao.dto;


public class AccountManagerDto {
    private int id;
    private int accountTypeId;
    private String description;
    private Double initialBalance;

    public AccountManagerDto() {
    }

    public AccountManagerDto(int id, int accountTypeId, String description, Double initialBalance) {
        this.id = id;
        this.accountTypeId = accountTypeId;
        this.description = description;
        this.initialBalance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    @Override
    public String toString() {
        return "Account Manager {" +
                "accountTypeId='" + accountTypeId + '\'' +
                ", description='" + description + '\'' +
                ", initialBalance='" + initialBalance + '\'' +
                '}';
    }
}
