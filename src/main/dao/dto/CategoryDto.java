package main.dao.dto;

public class CategoryDto {
    private int id;
    private String name;
    private int operationId;

    public CategoryDto() {
    }

    public CategoryDto(int id, String name, int operationId) {
        this.id = id;
        this.name = name;
        this.operationId = operationId;
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

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "Category {" +
                "name ='" + name + '\'' +
                ", operationId='" + operationId + '\'' +
                '}';
    }


}
