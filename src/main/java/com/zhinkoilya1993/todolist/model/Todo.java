package com.zhinkoilya1993.todolist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Todo extends AbstractNamedEntity {

    @Column(name = "date_time")
    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private boolean completed = false;

    public Todo(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}