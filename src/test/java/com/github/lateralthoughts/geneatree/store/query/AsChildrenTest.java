package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;

import static com.github.lateralthoughts.geneatree.DomainObjects.*;
import static com.github.lateralthoughts.geneatree.domain.Gender.FEMININE;
import static com.github.lateralthoughts.geneatree.domain.Gender.MASCULINE;
import static com.github.lateralthoughts.geneatree.store.query.AsChildren.AS_CHILD;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Sets.newHashSet;

public class AsChildrenTest {

	private Collection<Parenthood> jacksonMembers;

	@BeforeMethod
	public void prepare() {
		Person josephJackson = person(MASCULINE, "Joseph", "Jackson", date(1929, 7, 26));
		jacksonMembers = newHashSet(
				parenthood(person(MASCULINE, "Michael", "Jackson", date(1958, 8, 29), date(2009, 6, 25)), josephJackson),
				parenthood(person(FEMININE, "Janet", "Jackson", date(1966, 5, 16)), josephJackson));
	}

	@Test
	public void should_filter_children() {
		Collection<Person> children = transform(jacksonMembers, AS_CHILD());

		// extracting firstName
	}
}
