package com.github.lateralthoughts.geneatree.store.io;

import com.github.lateralthoughts.geneatree.domain.Parenthood;
import com.github.lateralthoughts.geneatree.domain.Person;
import com.github.lateralthoughts.geneatree.store.FamilyMembers;
import com.google.common.io.CharStreams;

import java.io.*;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;

import static com.github.lateralthoughts.geneatree.store.io.ParenthoodLineParser.AS_PARENT_LINKS;
import static com.github.lateralthoughts.geneatree.store.io.PersonLineParser.AS_PERSONS;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.notNull;
import static com.google.common.base.Splitter.on;
import static com.google.common.collect.FluentIterable.from;

public final class FamilyTreeParser {

	private final Path peopleFile;
	private final Path parenthoodFile;

	public FamilyTreeParser(Path peopleFile, Path parenthoodFile) {
		this.peopleFile = peopleFile;
		this.parenthoodFile = parenthoodFile;

		checkIsReadableFile(peopleFile);
		checkIsReadableFile(parenthoodFile);
	}

	public FamilyMembers parse() throws IOException {
		Collection<Person> persons = from(linesFrom(reader(peopleFile))).transform(AS_PERSONS()).filter(notNull()).toSet();

		Set<Parenthood> parenthoodLinks = from(linesFrom(reader(parenthoodFile))).transform(AS_PARENT_LINKS(persons))
				.filter(notNull()).toSet();

		return new FamilyMembers(parenthoodLinks);
	}

	private InputStreamReader reader(Path path) throws FileNotFoundException {
		return new InputStreamReader(new FileInputStream(path.toFile()));
	}

	private Iterable<String> linesFrom(Readable reader) throws IOException {
		return on('\n').trimResults().omitEmptyStrings().split(CharStreams.toString(reader));
	}

	private void checkIsReadableFile(Path path) throws IllegalStateException {
		checkState(path != null);
		File file = path.toAbsolutePath().toFile();
		checkState(file.isFile());
		checkState(file.canRead());
	}
}
