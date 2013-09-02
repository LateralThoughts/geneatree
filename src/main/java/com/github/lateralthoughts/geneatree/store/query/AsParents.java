package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Function;

public class AsParents implements Function<Parenthood, Person> {

	private AsParents() {}

	public static AsParents AS_PARENTS() {
		return new AsParents();
	}

	@Override
	public Person apply(Parenthood input) {
		if (input == null) {
			return null;
		}
		return input.getParent();
	}
}
