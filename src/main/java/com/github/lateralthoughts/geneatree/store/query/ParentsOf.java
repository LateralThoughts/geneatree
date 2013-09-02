package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkState;

public class ParentsOf implements Predicate<Parenthood> {

	private final Person child;

	public ParentsOf(Person child) {
		this.child = child;
		checkState(child != null);
	}

	public static ParentsOf PARENTS_OF(Person child) {
		return new ParentsOf(child);
	}

	@Override
	public boolean apply(Parenthood input) {
		if (input == null) {
			return false;
		}
		return input.getChild().equals(child);
	}
}
