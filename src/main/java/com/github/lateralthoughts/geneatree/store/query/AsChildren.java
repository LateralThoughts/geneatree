package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.github.lateralthoughts.geneatree.store.FamilyMembers;
import com.google.common.base.Function;

import static com.google.common.collect.Lists.newArrayList;

public class AsChildren implements Function<Parenthood, Person> {

	private AsChildren() {}

	public static AsChildren AS_CHILD() {
		return new AsChildren();
	}

	public static Function<Person, Iterable<Person>> AS_CHILDREN(final FamilyMembers members) {
		return new Function<Person, Iterable<Person>>() {
			@Override
			public Iterable<Person> apply(Person input) {
				if (input == null) {
					return newArrayList();
				}
				return members.childrenOf(input).result();
			}
		};
	}

	@Override
	public Person apply(Parenthood input) {
		if (input == null) {
			return null;
		}
		return input.getChild();
	}
}
