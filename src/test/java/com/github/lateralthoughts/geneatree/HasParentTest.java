package com.github.lateralthoughts.geneatree;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.google.gag.annotation.remark.WTF;
import org.joda.time.DateTime;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.github.lateralthoughts.geneatree.domain.Gender.FEMININE;
import static com.github.lateralthoughts.geneatree.domain.Gender.MASCULINE;

public class HasParentTest {

	@WTF("Darth, son of Luke, really?!")
	@Test(expectedExceptions = IllegalStateException.class)
	public void should_fail_if_child_is_older_than_parent() {
		Person luke = new Person(MASCULINE, "Luke", "Skywalker", new DateTime().withDate(1951, 9, 25));
		Person darth = new Person(MASCULINE, "Anakin", "Skywalker", new DateTime().withDate(1935, 7, 1));

		new Parenthood(darth, luke);
	}

	@Test(dataProvider = "null_cases", expectedExceptions = IllegalStateException.class)
	public void should_fail_if_one_person_is_null(Person child, Person father) {

		new Parenthood(child, father);
	}

	@DataProvider
	Object[][] null_cases() {
		return new Object[][] {
				{ new Person(MASCULINE, "LeFils", "Du SoldatInconnu", new DateTime().withDate(1920, 11, 11)), null },
				{ null, new Person(FEMININE, "Frigide", "Barjot", new DateTime().withDate(1952, 9, 25)) }, };
	}
}
