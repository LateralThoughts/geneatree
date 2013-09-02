package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkState;

public class ChildrenOf implements Predicate<Parenthood> {

	private final Person parent;

	public ChildrenOf(Person parent) {
		this.parent = parent;
		checkState(parent != null);
	}

	public static ChildrenOf CHILDREN_OF(Person parent) {
		return new ChildrenOf(parent);
	}

	@Override
	public boolean apply(Parenthood input) {
		if (input == null) {
			return false;
		}
		return input.getParent().equals(parent);
	}
}
