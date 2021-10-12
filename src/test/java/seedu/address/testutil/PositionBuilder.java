package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PositionBuilder {
    public static final String DEFAULT_TITLE = "HR Manager";

    private Title title;
    private Set<Person> candidates;

    /**
     * Creates a {@code PositionBuilder} with the default details.
     */
    public PositionBuilder() {
        title = new Title(DEFAULT_TITLE);
        candidates = new HashSet<>();
    }

    /**
     * Initializes the PositionBuilder with the data of {@code positionToCopy}.
     */
    public PositionBuilder(Position positionToCopy) {
        title = positionToCopy.getTitle();
        candidates = new HashSet<>(positionToCopy.getCandidatesApplied());
    }

    /**
     * Sets the {@code Name} of the {@code Position} that we are building.
     */
    public PositionBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code candidates} into a {@code Set<Person>} and set it to the {@code Position} that we are building.
     */
    public PositionBuilder withCandidates(Person ... candidates) {
        this.candidates = SampleDataUtil.getPersonSet(candidates);
        return this;
    }

    public Position build() {
        return new Position(title, candidates);
    }

}
