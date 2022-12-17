package org.example.exercise3Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity // oznaczenie klasy jako encja aby umożliwić dodanie jej do Bazy Danych
public class Employee {

    @Id // adnotacja @Id ozncza kolumne jako id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // adnotacja oznacza opcje auto increment
    @Column // adnotacja definuje nazwę tabeli
    private Integer id;

    @Column // adnotacja definuje nazwę tabeli
    private String name;

    @Column  // adnotacja definuje nazwę tabeli
    private String surname;

    @Column
    private LocalDate birthday;

    @Column
    private String email;

    @OneToOne
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @OneToMany (mappedBy = "employee")
    private List<Task> tasks;

    @ManyToMany(mappedBy = "employeeList")
    private List<Project> projectList;

    public Employee() {

    }

    public Employee(String name, String surname, LocalDate birthday, String email) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
    }

    public Employee(Integer id, String name, String surname, LocalDate birthday, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setProjects(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }
}
