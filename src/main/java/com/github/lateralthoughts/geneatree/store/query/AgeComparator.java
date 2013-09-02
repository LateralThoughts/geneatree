package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Person;

import java.util.Comparator;

public class AgeComparator implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {

		if (person1 == person2) {
			return 0;
		}
		if (person1 == null) {
			return -1;
		}
		if (person2 == null) {
			return 1;
		}

		return person2.getBirthDate().compareTo(person1.getBirthDate());
	}
}
