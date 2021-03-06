package com.zhinkoilya1993.todolist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

	@NotBlank
	private String name;
}
