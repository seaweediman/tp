package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Represents an Interview in the HR Manager, with default status 'pending'.
 * Guarantees: immutable; position is valid and not null.
 */
public class Interview {

    // public static final String MESSAGE_CONSTRAINTS = "";

    private final Position position;

    private InterviewStatus status;

    private final Set<Person> candidates;

    private final LocalTime startTime;

    private final Duration duration;

    public enum InterviewStatus {
        PENDING,
        COMPLETED;

        public static final String MESSAGE_CONSTRAINTS = "Interview Status can ony take the values:\n"
                + "pending\n"
                + "completed\n";

        /*
         * The first character of the status must not be a whitespace,
         * otherwise " " (a blank string) becomes a valid input.
         */
        public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

        /**
         * Return true if a give string is a valid interview status.
         * Only "pending" and "completed" are valid strings for interview status.
         *
         * @param test A string to test if is a valid interview status.
         * @return True if test string is a valid interview status, false otherwise.
         */
        public static boolean isValidInterviewStatus(String test) {
            return (test != null && (test.equals("pending") || test.equals("completed")))
                    && test.matches(VALIDATION_REGEX);
        }
    }

    /**
     * Constructs a {@code Interview}.
     *
     * @param position   A position for the interview.
     * @param candidates A list of candidates attending the interview.
     * @param startTime  The start time of the interview.
     * @param duration   The duration of the interview.
     */
    public Interview(Position position, Set<Person> candidates, LocalTime startTime, Duration duration) {
        requireAllNonNull(position, candidates, startTime, duration);
        this.position = position;
        this.candidates = candidates;
        this.startTime = startTime;
        this.duration = duration;
    }

    /**
     * Constructs a {@code Interview}.
     *
     * @param position   A position for the interview.
     * @param candidates A list of candidates attending the interview.
     * @param startTime  The start time of the interview.
     * @param duration   The duration of the interview.
     * @param status     The interview status.
     */
    public Interview(Position position, Set<Person> candidates, LocalTime startTime, Duration duration,
                     InterviewStatus status) {
        this(position, candidates, startTime, duration);
        requireNonNull(status);
        this.status = status;
    }

    /**
     * Returns true if both interviews have the same position, candidates, start time, and duration.
     * This defines a weaker notion of equality between two interviews.
     */
    public boolean isSameInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.getPositionTitle().equals(getPositionTitle())
                && otherInterview.getCandidates().equals(getCandidates())
                && otherInterview.getStartTime().equals(getStartTime())
                && otherInterview.getDuration().equals(getDuration());
    }

    public Set<Person> getCandidates() {
        assert this.candidates != null : "Interview candidate set is non-null.";
        return this.candidates;
    }

    public Duration getDuration() {
        assert this.duration != null : "Interview duration is non-null.";
        return this.duration;
    }

    public LocalTime getStartTime() {
        assert this.startTime != null : "Interview start time is non-null.";
        return this.startTime;
    }

    public Title getPositionTitle() {
        assert this.position != null : "Interview position is non-null.";
        return this.position.getTitle();
    }

    public InterviewStatus getStatus() {
        assert this.status != null : "Interview status is non-null.";
        return this.status;
    }

    public String getStatusInString() {
        assert this.status != null : "Interview status is non-null.";
        return this.status.toString();
    }

    public void setStatus(InterviewStatus status) {
        requireNonNull(status);
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interview // instanceof handles nulls
                && position.equals(((Interview) other).position) // position check
                && candidates.equals(((Interview) other).candidates) // candidates check
                && startTime.equals(((Interview) other).startTime) // startTime check
                && duration.equals(((Interview) other).duration)
                && status.equals(((Interview) other).getStatus())); // status check
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, candidates, startTime, duration, status);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return "[" + getPositionTitle().toString() + "\n\t"
                + getCandidates().toString() + "\n\t"
                + getStartTime().toString() + " - "
                + getDuration().toString() + "\n"
                + getStatusInString() + "]";
    }


}
