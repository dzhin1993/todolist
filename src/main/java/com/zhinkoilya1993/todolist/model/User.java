package com.zhinkoilya1993.todolist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractNamedEntity {

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;
}
