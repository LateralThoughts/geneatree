package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Gender;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkState;

public final class MatchesGender implements Predicate<Person> {

	private final Gender gender;

	private MatchesGender(Gender gender) {
		this.gender = gender;
		checkState(gender != null);
	}

	public static MatchesGender MATCHES_GENDER(Gender gender) {
		return new MatchesGender(gender);
	}

	@Override
	public boolean apply(Person input) {
		if (input == null) {
			return false;
		}
		return input.getGender() == gender;
	}
}
