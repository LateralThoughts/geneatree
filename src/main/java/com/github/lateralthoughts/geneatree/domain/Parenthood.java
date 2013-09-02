package com.github.lateralthoughts.geneatree.domain;

import com.google.common.base.Objects;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

public final class Parenthood {

	private final Person child;
	private final Person parent;

	public Parenthood(Person child, Person parent) {
		this.child = child;
		this.parent = parent;

		checkState(child != null);
		checkState(parent != null);
		checkState(child.getBirthDate().isAfter(parent.getBirthDate()));
	}

	public Person getChild() {
		return child;
	}

	public Person getParent() {
		return parent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Parenthood that = (Parenthood) o;

		return equal(child, that.child) && equal(parent, that.parent);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(child, parent);
	}

	@Override
	public String toString() {
		return format("(%s)-[:HAS_PARENT]->(%s)", child, parent);
	}
}
