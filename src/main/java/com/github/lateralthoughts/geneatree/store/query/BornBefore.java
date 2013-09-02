package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Predicate;
import org.joda.time.DateTime;

import static com.google.common.base.Preconditions.checkNotNull;

public final class BornBefore implements Predicate<Person> {

	private final DateTime date;

	private BornBefore(DateTime date) {
		this.date = date;
	}

	public static BornBefore BORN_BEFORE(Person person) {
		checkNotNull(person);
		return new BornBefore(person.getBirthDate());
	}

	public static BornBefore BORN_BEFORE(DateTime date) {
		checkNotNull(date);
		return new BornBefore(date);
	}

	@Override
	public boolean apply(Person input) {
		if (input == null) {
			return false;
		}
		return input.getBirthDate().isBefore(date);
	}
}
