package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Predicate;
import org.joda.time.DateTime;

import static com.google.common.base.Preconditions.checkNotNull;

public final class BornAfter implements Predicate<Person> {

	private final DateTime date;

	private BornAfter(DateTime date) {
		this.date = date;
	}

	public static BornAfter BORN_AFTER(Person person) {
		checkNotNull(person);
		return new BornAfter(person.getBirthDate());
	}

	public static BornAfter BORN_AFTER(DateTime date) {
		checkNotNull(date);
		return new BornAfter(date);
	}

	@Override
	public boolean apply(Person input) {
		if (input == null) {
			return false;
		}
		return input.getBirthDate().isAfter(date);
	}
}
