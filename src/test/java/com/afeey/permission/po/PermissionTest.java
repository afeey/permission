package com.afeey.permission.po;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class PermissionTest {

	private Validator validator;

	@Test
	public void idIsNull() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		Permission permission = new Permission();
		permission.setId("");
		permission.setParentId("0");
		permission.setName("xxx");

		Set<ConstraintViolation<Permission>> cvSet = validator
				.validate(permission);

		assertEquals(1, cvSet.size());
		// fail(cvSet.iterator().next().getPropertyPath().toString()+" "+cvSet.iterator().next().getMessage());
	}

}
