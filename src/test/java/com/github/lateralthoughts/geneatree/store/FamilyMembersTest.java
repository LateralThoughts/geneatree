package com.github.lateralthoughts.geneatree.store;

import com.github.lateralthoughts.geneatree.domain.Person;
import com.github.lateralthoughts.geneatree.store.io.FamilyTreeParser;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FamilyMembersTest {

	private FamilyMembers familyMembers;

	@BeforeMethod
	public void prepare() throws Exception {
		Class<FamilyMembersTest> myClass = FamilyMembersTest.class;
		familyMembers = new FamilyTreeParser(Paths.get(myClass.getResource("people.ssv").toURI()), Paths.get(myClass
				.getResource("parenthood.links").toURI())).parse();
	}

	@Test
	public void should_return_father_older_than_son() {
		Person florent = familyMembers.withName("Florent", "Biville").singleResult();

		Person daniel = familyMembers.fatherOf(florent).singleResult();

		// TODO: (api jodatime) assert birth dates...
	}

	@Test
	public void should_return_youngest_of_brothers_and_sisters() {
		Person aurelie = familyMembers.withName("Aurélie", "Biville").singleResult();

		FamilyMembers brotherhood = familyMembers.brothersAndSistersOf(aurelie);

		final Person youngest = brotherhood.youngest().singleResult();

		Assertions.assertThat(brotherhood.result()).is(new Condition<Iterable<Person>>() {
			@Override
			public boolean matches(Iterable<Person> brotherhood) {
				// TODO: filter with BornBefore ;)
				return true;
			}
		});
	}
}
