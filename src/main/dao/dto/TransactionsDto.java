package main.dao.dto;



public class TransactionsDto {
    private int accountManagerId;
    private int categoryId;
    private String date;
    private Double amount;

    public TransactionsDto() {
    }
    public TransactionsDto(int accountManagerId, int categoryId, String date, Double amount) {
        this.accountManagerId = accountManagerId;
        this.categoryId = categoryId;
        this.date = date;
        this.amount = amount;
    }

    public int getAccountManagerId() {
        return accountManagerId;
    }

    public void setAccountManagerId(int accountManagerId) {
        this.accountManagerId = accountManagerId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    //public Double getNetAmount() {
        //return amount;
   // }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction {" +
                "accountManagerId='" + accountManagerId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", date='" + date + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}

