package com.zhinkoilya1993.todolist.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

	@Column(name = "name")
	private String name;
}
