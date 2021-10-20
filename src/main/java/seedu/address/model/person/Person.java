package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the HR Manager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;
    private final Status status;
    private Set<Position> positions = new HashSet<>();
    private Set<Interview> interviews = new HashSet<>();

    /**
     * Every field must be present and not null. If status is null, defaults to applied
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags, Status status,
                  Set<Position> positions) {
        requireAllNonNull(name, phone, email, address, tags, status, positions);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.remark = remark;
        this.status = status;
        this.positions.addAll(positions);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable position set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Position> getPositions() {
        return Collections.unmodifiableSet(positions);
    }

    public Set<Interview> getInterviews() {
        return Collections.unmodifiableSet(interviews);
    }

    public boolean appliedForPosition(Position p) {
        return positions.stream().anyMatch(p::isSamePosition);
    }

    public boolean hasInterview(Interview i) {
        return interviews.stream().anyMatch(i::isSameInterview);
    }

    public void addPosition(Position p) {
        positions.add(p);
    }

    public void deletePosition(Position p) {
        positions.remove(p);
    }

    public void addInterview(Interview i) {
        interviews.add(i);
    }

    public void deleteInterview(Interview i) {
        interviews.remove(i);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getPositions().equals(getPositions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        // hashing with all fields does not work properly when loading from data
        //return Objects.hash(name, phone, email, address, tags, remark, status, positions);
        return Objects.hash(name, phone, email, address, tags, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append(" Remark: ")
                .append(getRemark())
                .append(" Status: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Position> positions = getPositions();
        if (!positions.isEmpty()) {
            builder.append("; Positions: ");
            positions.forEach(builder::append);
        }

        Set<Interview> interviews = getInterviews();
        if (!interviews.isEmpty()) {
            builder.append("; Interviews: ");
            interviews.forEach(interview -> builder.append(interview.getDisplayString()));
        }

        return builder.toString();
    }

    public void setInterviews(Set<Interview> personInterviews) {
        interviews.addAll(personInterviews);
    }
}
