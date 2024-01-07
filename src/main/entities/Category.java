package main.entities;



public class Category {
    private int id;
    private String name;
    private int operationTypeId;

    public Category() {
    }

    public Category(int id, String name, int operationTypeId) {
        this.id = id;
        this.name = name;
        this.operationTypeId = operationTypeId;
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

    public int getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    @Override
    public String toString() {
        return "Category {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", operationTypeId='" + operationTypeId + '\'' +
                '}';
    }
}

