package org.example.exercise2JDBC;

public class Client {

    private int id;
    private String name;
    private String surname;
    private int age;
    private String address;
    private String statement;
    private String description;
    private int trainerId;

    public Client(int id, String name, String surname, int age, String address,
                  String statement, String description) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
        this.statement = statement;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getStatement() {
        return statement;
    }

    public String getDescription() {
        return description;
    }

    public int getTrainerId() {
        return trainerId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", statement='" + statement + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
