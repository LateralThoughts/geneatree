package com.github.lateralthoughts.geneatree.store.io;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;

import java.util.Collection;
import java.util.List;

import static com.github.lateralthoughts.geneatree.store.query.WithName.WITH_NAME;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

final class ParenthoodLineParser implements Function<String, Parenthood> {

	private final Collection<Person> persons;

	private ParenthoodLineParser(Collection<Person> persons) {
		this.persons = persons;
	}

	public static ParenthoodLineParser AS_PARENT_LINKS(Collection<Person> persons) {
		return new ParenthoodLineParser(persons);
	}

	@Override
	public Parenthood apply(String input) {
		if (input == null) {
			return null;
		}

		List<String> elements = newArrayList(input.split("->"));
		checkArgument(elements.size() == 2);

		return new Parenthood(person(elements.get(1)), person(elements.get(0)));
	}

	private Person person(String identity) {
		List<String> name = newArrayList(identity.split(" "));
		checkArgument(name.size() == 2);

		Optional<Person> person = FluentIterable.from(persons).firstMatch(WITH_NAME(name.get(0), name.get(1)));

		checkArgument(person.isPresent(), format("%s not found", identity));
		return person.get();
	}
}
