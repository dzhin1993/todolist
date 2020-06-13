package com.zhinkoilya1993.todolist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Todo extends AbstractNamedEntity {

    @Column(name = "date_time")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "completed")
    @NotNull
    private boolean completed = false;

    public Todo(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}