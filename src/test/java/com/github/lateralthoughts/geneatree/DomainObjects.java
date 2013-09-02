package com.github.lateralthoughts.geneatree;

import com.github.lateralthoughts.geneatree.domain.Gender;
import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Optional;
import org.joda.time.DateTime;

public class DomainObjects {

	public static Person person(Gender gender, String firstName, String lastName, DateTime birthdate, DateTime deathDate) {

		return new Person(gender, firstName, lastName, birthdate, Optional.of(deathDate));
	}

	public static Person person(Gender gender, String firstName, String lastName, DateTime birthdate) {

		return new Person(gender, firstName, lastName, birthdate);
	}

	public static Parenthood parenthood(Person child, Person parent) {
		return new Parenthood(child, parent);
	}

	public static DateTime date(int year, int month, int day) {
		return new DateTime().withDate(year, month, day);
	}
}
