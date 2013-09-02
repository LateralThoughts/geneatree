package com.github.lateralthoughts.geneatree;

import com.github.lateralthoughts.geneatree.domain.Gender;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.common.base.Optional;
import org.joda.time.DateTime;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.github.lateralthoughts.geneatree.DomainObjects.date;
import static com.github.lateralthoughts.geneatree.domain.Gender.FEMININE;
import static com.github.lateralthoughts.geneatree.domain.Gender.MASCULINE;
import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

public class PersonTest {

	@Test(dataProvider = "exception_cases", expectedExceptions = IllegalStateException.class)
	public void should_fail_when_creating_person_with_invalid_parameter(Gender gender, String firstName, String lastName,
			DateTime birthDate, Optional<DateTime> deathDate) {
		new Person(gender, firstName, lastName, birthDate, deathDate);
	}

	@Test
	public void should_be_dead_if_death_date_present() {
		Person ada = new Person(FEMININE, "Ada", "Lovelace", date(1815, 12, 10), of(date(1852, 11, 27)));

		// TODO: use GeneAssertions
	}

	@DataProvider
	Object[][] exception_cases() {
		return new Object[][] { { null, "Florent", "Biville", date(1986, 3, 4), absent() },
				{ MASCULINE, null, "Biville", date(1986, 3, 4), absent() },
				{ MASCULINE, "Florent", null, date(1986, 3, 4), absent() },
				{ MASCULINE, "Florent", "Biville", null, absent() },
				{ MASCULINE, "Florent", "Biville", new DateTime().plusYears(1), absent() },
				{ MASCULINE, "Florent", "Biville", date(1986, 3, 4), of(date(1983, 3, 4)) },
				{ MASCULINE, "Florent", "Biville", date(1986, 3, 4), of(new DateTime().plusYears(1)) }, };
	}
}
