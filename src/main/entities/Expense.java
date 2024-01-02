package main.entities;

public class Expense {
    private int userId;
    private double amount;
    private int categoryId;
    private String date;

    public Expense() {
    }

    public Expense(int userId, double amount, int categoryId, String date) {
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = date;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", categoryId=" + categoryId +
                ", date='" + date + '\'' +
                '}';
    }
}
