package main.dao.dto;


public class OperationTypeDto {
    private int id;
    private String name;
    private String operation;

    public OperationTypeDto() {
    }

    public OperationTypeDto(int id, String name, String operation) {
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

    public static void setName(String name) {
        this.name = name;
    }

    public String getOperation() {
        return operation;
    }

    public static void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Operation Type {" +
                "Name='" + name + '\'' +
                ", Operation='" + operation + '\'' +
                '}';
    }
}

