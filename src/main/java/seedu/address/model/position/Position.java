package seedu.address.model.position;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Position in the HR Manager, with default status 'open'.
 * Guarantees: immutable; title is valid and not null.
 */
public class Position {

    public static final String MESSAGE_CONSTRAINTS = "Position names should be alphanumeric";

    public final Title title;

    private PositionStatus status;

    public enum PositionStatus {
        OPEN,
        CLOSED;

        public static final String MESSAGE_CONSTRAINTS = "Position Status can only take the values:\n"
                + "open\n"
                + "closed\n";

        /*
         * The first character of the status must not be a whitespace,
         * otherwise " " (a blank string) becomes a valid input.
         */
        public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

        /**
         * Returns true if a given string is a valid status.
         * Only "open" and "closed" are valid strings for status.
         */
        public static boolean isValidPositionStatus(String test) {
            return (((test).equals("open") || (test).equals("closed"))
                    && test.matches(VALIDATION_REGEX));
        }
    }

    /**
     * Constructs a {@code Position}.
     *
     * @param title A valid position title.
     */
    public Position(Title title) {
        requireNonNull(title);
        this.title = title;
        this.status = PositionStatus.OPEN;
    }

    /**
     * Constructs a {@code Position}
     *
     * @param title A valid position title.
     * @param status A valid position status.
     */
    public Position(Title title, PositionStatus status) {
        requireNonNull(title);
        this.title = title;
        this.status = status;
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

    public PositionStatus getStatus() {
        return this.status;
    }

    public String getStatusInString() {
        return this.status.toString();
    }

    public void setStatus(PositionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && title.equals(((Position) other).title)
                && status.equals(((Position) other).getStatus())); // status check
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
