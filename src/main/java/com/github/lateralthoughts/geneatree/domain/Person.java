package com.github.lateralthoughts.geneatree.domain;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import org.joda.time.DateTime;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

public final class Person {

	private final Gender gender;
	private final String firstName;
	private final String lastName;
	private final DateTime birthDate;
	private final Optional<DateTime> deathDate;

	public Person(Gender gender, String firstName, String lastName, DateTime birthDate, Optional<DateTime> deathDate) {

		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.deathDate = deathDate;

		checkState(gender != null);
		checkState(firstName != null);
		checkState(lastName != null);
		checkState(birthDate != null && birthDate.isBeforeNow());
		if (deathDate.isPresent()) {
			DateTime date = deathDate.get();
			checkState(date.isAfter(birthDate) && date.isBeforeNow());
		}
	}

	public Person(Gender gender, String firstName, String lastName, DateTime birthDate) {

		this(gender, firstName, lastName, birthDate, Optional.<DateTime> absent());
	}

	public Gender getGender() {
		return gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public DateTime getBirthDate() {
		return birthDate;
	}

	public boolean isDead() {
		return deathDate.isPresent();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Person person = (Person) o;

		return equal(gender, person.gender) && equal(firstName, person.firstName) && equal(lastName, person.lastName)
				&& equal(birthDate, person.birthDate);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(gender, firstName, lastName, birthDate);
	}

	@Override
	public String toString() {
		return format("%s %s", firstName, lastName);
	}
}
