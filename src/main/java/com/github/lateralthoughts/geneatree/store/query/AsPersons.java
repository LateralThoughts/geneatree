package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Function;

import static com.google.common.collect.Lists.newArrayList;

public class AsPersons implements Function<Parenthood, Iterable<Person>> {

	private AsPersons() {}

	public static AsPersons AS_PERSONS() {
		return new AsPersons();
	}

	@Override
	public Iterable<Person> apply(Parenthood input) {
		if (input == null) {
			return null;
		}
		return newArrayList(input.getParent(), input.getChild());
	}
}
