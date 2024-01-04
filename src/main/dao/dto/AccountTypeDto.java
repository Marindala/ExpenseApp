package main.dao.dto;


public class AccountTypeDto {
    private String name;

    public AccountTypeDto() {
    }

    public AccountTypeDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account Type {" +
                "name='" + name + '\'' +
                '}';
    }
}
