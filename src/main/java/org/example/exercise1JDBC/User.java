package org.example.exercise1JDBC;

public class User {

    private int id;
    private String userName;
    private String password;
    private String language;

    public User( String userName, String password, String language) {
        this.userName = userName;
        this.password = password;
        this.language = language;
    }

    public User(int id, String userName, String password, String language) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.language = language;
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getLanguage() {
        return language;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nId: " + getId() +
                "\nUser name: " + getUserName() +
                "\nPassword: " + getPassword() +
                "\nLanguage: " + getLanguage();
    }
}
