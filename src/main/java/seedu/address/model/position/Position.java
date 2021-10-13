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
        CLOSED
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
        if (this.status == null) {
            setStatus(PositionStatus.OPEN);
        }
        return this.status;
    }

    public void setStatus(PositionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && title.equals(((Position) other).title))
                && status.equals(((Position) other).getStatus()); // status check
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
