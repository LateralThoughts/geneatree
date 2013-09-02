package com.github.lateralthoughts.geneatree.store;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.github.lateralthoughts.geneatree.store.query.AgeComparator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import com.google.gag.annotation.enforceable.CantTouchThis;
import com.google.gag.enumeration.Stop;
import org.joda.time.DateTime;

import java.util.Set;

import static com.github.lateralthoughts.geneatree.domain.Gender.FEMININE;
import static com.github.lateralthoughts.geneatree.domain.Gender.MASCULINE;
import static com.github.lateralthoughts.geneatree.store.query.AsChildren.AS_CHILD;
import static com.github.lateralthoughts.geneatree.store.query.AsChildren.AS_CHILDREN;
import static com.github.lateralthoughts.geneatree.store.query.AsParents.AS_PARENTS;
import static com.github.lateralthoughts.geneatree.store.query.AsPersons.AS_PERSONS;
import static com.github.lateralthoughts.geneatree.store.query.BornAfter.BORN_AFTER;
import static com.github.lateralthoughts.geneatree.store.query.BornBefore.BORN_BEFORE;
import static com.github.lateralthoughts.geneatree.store.query.ChildrenOf.CHILDREN_OF;
import static com.github.lateralthoughts.geneatree.store.query.MatchesGender.MATCHES_GENDER;
import static com.github.lateralthoughts.geneatree.store.query.ParentsOf.PARENTS_OF;
import static com.github.lateralthoughts.geneatree.store.query.WithName.WITH_NAME;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.*;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.ImmutableSet.copyOf;
import static com.google.common.collect.Sets.filter;
import static com.google.common.collect.Sets.newHashSet;

public final class FamilyMembers {

	private final Set<Parenthood> parenthoodLinks;
	private Set<Person> lastResult;

	public FamilyMembers(Set<Parenthood> parenthoodLinks) {

		this.parenthoodLinks = parenthoodLinks;
		reset();

		checkState(parenthoodLinks != null);
	}

	public FamilyMembers parentsOf(Person child) {
		lastResult = from(parenthoodLinks).filter(and(PARENTS_OF(child), notNull())).transform(AS_PARENTS())
				.filter(notNull()).toSet();

		return this;
	}

	public FamilyMembers childrenOf(Person parent) {
		lastResult = from(parenthoodLinks).filter(and(CHILDREN_OF(parent), notNull())).transform(AS_CHILD())
				.filter(notNull()).toSet();

		return this;
	}

	public FamilyMembers motherOf(Person child) {
		lastResult = from(parentsOf(child).lastResult).filter(MATCHES_GENDER(FEMININE)).toSet();

		return this;
	}

	public FamilyMembers fatherOf(Person child) {
		lastResult = from(parentsOf(child).lastResult).filter(MATCHES_GENDER(MASCULINE)).toSet();

		return this;
	}

	public FamilyMembers brothersAndSistersOf(Person sibling) {
		lastResult = from(parentsOf(sibling).lastResult).transformAndConcat(AS_CHILDREN(this))
				.filter(not(equalTo(sibling))).toSet();

		return this;
	}

	public FamilyMembers withName(String firstName, String lastName) {
		lastResult = filter(lastResult, WITH_NAME(firstName, lastName));
		return this;
	}

	public FamilyMembers bornBefore(DateTime dateTime) {
		lastResult = filter(lastResult, BORN_BEFORE(dateTime));
		return this;
	}

	public FamilyMembers bornBefore(Person person) {
		lastResult = filter(lastResult, BORN_BEFORE(person));
		return this;
	}

	public FamilyMembers bornAfter(DateTime dateTime) {
		lastResult = filter(lastResult, BORN_AFTER(dateTime));
		return this;
	}

	public FamilyMembers bornAfter(Person person) {
		lastResult = filter(lastResult, BORN_AFTER(person));
		return this;
	}

	public FamilyMembers eldest() {
		lastResult = newHashSet(Ordering.from(new AgeComparator()).max(lastResult));

		return this;
	}

	public FamilyMembers youngest() {
		lastResult = newHashSet(Ordering.from(new AgeComparator()).min(lastResult));

		return this;
	}

	@CantTouchThis(Stop.HAMMERTIME)
	public FamilyMembers youCantTouchThis() {
		return this;
	}

	public Set<Person> result() {
		ImmutableSet<Person> result = copyOf(lastResult);
		reset();
		return result;
	}

	public Person singleResult() {
		checkState(lastResult.size() == 1);
		Person result = lastResult.iterator().next();
		reset();
		return result;
	}

	public boolean contains(Person person) {
		return lastResult.contains(person);
	}

	private void reset() {
		lastResult = from(parenthoodLinks).transformAndConcat(AS_PERSONS()).filter(notNull()).toSet();
	}
}
