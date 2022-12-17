package org.example.exercise3Hibernate;

import javax.persistence.*;

@Entity
public class Task {

    enum TaskType{
        BLOCKER, HIGH_PRIORITY, NORMAL, LOW_PRIORITY;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn (name = ("employee_id"))
    private Employee employee;

    @Column
    private String title;

    @Column
    private String description;

    @Column (name = "severity_type")
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    public Task() {
    }

    public Task(String title, String description, TaskType taskType) {
        this.title = title;
        this.description = description;
        this.taskType = taskType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                '}';
    }
}
