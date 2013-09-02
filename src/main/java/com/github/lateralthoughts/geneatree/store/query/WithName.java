package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkState;

public final class WithName implements Predicate<Person> {

	private final String firstName;
	private final String lastName;

	private WithName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

		checkState(firstName != null && lastName != null);
	}

	public static WithName WITH_NAME(String firstName, String lastName) {
		return new WithName(firstName, lastName);
	}

	@Override
	public boolean apply(Person input) {
		if (input == null) {
			return false;
		}
		return input.getFirstName().equals(firstName) && input.getLastName().equals(lastName);
	}
}
