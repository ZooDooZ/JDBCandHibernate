package org.example.exercise3Hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "Project_Employee",
            joinColumns = {
    @JoinColumn(name = "project_id")},
            inverseJoinColumns = {
    @JoinColumn(name = "employee_id")}
    )
    private List<Employee> employeeList;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
