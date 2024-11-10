package org.example.webapplication.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String task;
    private String date;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean completed;

    public Task() {
    }

    public Task(String task, String date) {
        this.task = task;
        this.date = date;
        this.completed = false;
    }
}
