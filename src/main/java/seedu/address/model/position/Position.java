package seedu.address.model.position;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents a Position in the HR Manager.
 * Guarantees: immutable; title is valid and not null.
 */
public class Position {

    public static final String MESSAGE_CONSTRAINTS = "Position names should be alphanumeric";

    public final Title title;

    private final Set<Person> candidates = new HashSet<>();

    /**
     * Constructs a {@code Position}.
     *
     * @param title A valid position title.
     */
    public Position(Title title, Set<Person> candidates) {
        requireNonNull(title);
        this.title = title;
        this.candidates.addAll(candidates);
    }

    /**
     * Constructs a {@code Position}.
     *
     * @param title A valid position title.
     */
    public Position(Title title) {
        requireNonNull(title);
        this.title = title;
    }

    /**
     * Returns true if both positions have the same name.
     * This defines a weaker notion of equality between two positions.
     */
    public boolean isSamePosition(Position otherPosition) {
        if (otherPosition == this) {
            return true;
        }

        return otherPosition != null
                && otherPosition.getTitle().equals(getTitle());
    }

    public Title getTitle() {
        return this.title;
    }

    public Set<Person> getCandidates() {
        return Collections.unmodifiableSet(candidates);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && title.equals(((Position) other).title))
                && candidates.equals(((Position) other).candidates); // state check
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + title.toString() + ']';
    }
}
