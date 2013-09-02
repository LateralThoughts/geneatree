package com.github.lateralthoughts.geneatree.custom;

import com.github.lateralthoughts.geneatree.domain.Person;
import org.assertj.core.api.Assertions;

public class GeneAssertions extends Assertions {

	public static PersonAssert assertThat(Person actual) {
		return new PersonAssert(actual);
	}
}
