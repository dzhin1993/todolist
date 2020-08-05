package com.zhinkoilya1993.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo extends AbstractNamedEntity {

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private boolean completed = false;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}