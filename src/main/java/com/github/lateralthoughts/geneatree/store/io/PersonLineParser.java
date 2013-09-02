package com.github.lateralthoughts.geneatree.store.io;

import com.github.lateralthoughts.geneatree.domain.Gender;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Function;
import org.joda.time.DateTime;

import java.util.Collection;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

final class PersonLineParser implements Function<String, Person> {

	private PersonLineParser() {}

	public static PersonLineParser AS_PERSONS() {
		return new PersonLineParser();
	}

	@Override
	public Person apply(String input) {
		if (input == null) {
			return null;
		}
		Collection<String> elements = newArrayList(input.split(" "));
		checkArgument(elements.size() >= 4);
		Iterator<String> iterator = elements.iterator();

		return new Person(gender(iterator.next()), iterator.next(), iterator.next(), dateTime(iterator.next()));
	}

	private Gender gender(String value) {
		switch (value) {
			case "M":
				return Gender.MASCULINE;
			case "F":
				return Gender.FEMININE;
			default:
				throw new IllegalArgumentException(format("Unsupported gender: %s", value));
		}
	}

	private DateTime dateTime(String value) {
		Collection<String> dateElements = newArrayList(value.split("/"));
		checkArgument(dateElements.size() == 3);
		Iterator<String> iterator = dateElements.iterator();
		return new DateTime().withDate(parseInt(iterator.next(), 10), parseInt(iterator.next(), 10),
				parseInt(iterator.next(), 10)).withTimeAtStartOfDay();
	}

}
