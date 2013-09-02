package com.github.lateralthoughts.geneatree.custom;

import com.github.lateralthoughts.geneatree.domain.Person;
import org.assertj.core.api.AbstractAssert;

public class PersonAssert extends AbstractAssert<PersonAssert, Person> {

	protected PersonAssert(Person actual) {
		super(actual, PersonAssert.class);
	}

	public static PersonAssert assertThat(Person actual) {
		return new PersonAssert(actual);
	}

	public PersonAssert isDead() {
		// check person under test isn't null ;)
		isNotNull();

		if (!actual.isDead()) {
			failWithMessage("Expected person <%s> <%s> to be dead, but wasn't.", actual.getFirstName(), actual.getLastName());
		}

		return this;
	}

}
