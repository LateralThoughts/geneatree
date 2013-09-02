package com.github.lateralthoughts.geneatree.store.query;

import com.github.lateralthoughts.geneatree.domain.Person;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.github.lateralthoughts.geneatree.DomainObjects.date;
import static com.github.lateralthoughts.geneatree.DomainObjects.person;
import static com.github.lateralthoughts.geneatree.domain.Gender.FEMININE;
import static com.github.lateralthoughts.geneatree.domain.Gender.TOKIO_HOTEL;

public class AgeComparatorTest {

	@Test(dataProvider = "comparison_cases")
	public void should_compare_people_by_age(Person firstPerson, Person secondPerson, int result) {

		// assertThat new AgeComparator
	}

	@DataProvider
	Object[][] comparison_cases() {
		return new Object[][] {
				{ person(FEMININE, "Agnetha", "Fältskog", date(1950, 4, 5)),
						person(FEMININE, "Anni-Frid", "Lyngstad", date(1945, 11, 15)), -1 },
				{ person(TOKIO_HOTEL, "Bill", "Kaulitz", date(1989, 9, 1)),
						person(TOKIO_HOTEL, "Tom", "Kaulitz", date(1989, 9, 1)), 0 },
				{ person(FEMININE, "Anni-Frid", "Lyngstad", date(1945, 11, 15)),
						person(FEMININE, "Agnetha", "Fältskog", date(1950, 4, 5)), 1 }, };
	}
}
