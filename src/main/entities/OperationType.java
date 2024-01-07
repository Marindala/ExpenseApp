package main.entities;

public class OperationType {
    private int id;
    private String name;
    private String operation;

    public OperationType() {
    }

    public OperationType(int id, String name, String operation) {
        this.id = id;
        this.name = name;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Transaction Type {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
