package com.zhinkoilya1993.todolist.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractNamedEntity {

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @NotNull
    private LocalDate registered = LocalDate.now();

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;
}
