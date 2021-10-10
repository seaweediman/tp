package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.position.Position;
import seedu.address.model.position.UniquePositionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class HrManager implements ReadOnlyHrManager {

    private final UniquePersonList persons;

    private final UniquePositionList positions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        persons = new UniquePersonList();
        positions = new UniquePositionList();
    }

    public HrManager() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public HrManager(ReadOnlyHrManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the position list with {@code positions}.
     * {@code positions} must not contain duplicate positions.
     */
    public void setPositions(List<Position> positions) {
        this.positions.setPositions(positions);
    }

    /**
     * Resets the existing data of this {@code HrManager} with {@code newData}.
     */
    public void resetData(ReadOnlyHrManager newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPositions(newData.getPositionList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the HR Manager.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the HR Manager.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// position-level operations

    /**
     * Returns true if a position with the same identity as {@code position} exists in the HR Manager.
     */
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return positions.contains(position);
    }

    /**
     * Adds a position to the HR Manager.
     * The position must not already exist in the HR Manager.
     */
    public void addPosition(Position p) {
        positions.add(p);
    }

    /**
     * Replaces the given position {@code target} in the list with {@code editedPosition}.
     * {@code target} must exist in the HR Manager.
     * The position identity of {@code editedPosition} must not be the same as another existing position in the HR
     * Manager.
     */
    public void setPosition(Position target, Position editedPosition) {
        requireNonNull(editedPosition);

        positions.setPosition(target, editedPosition);
    }

    /**
     * Removes {@code key} from this {@code HrManager}.
     * {@code key} must exist in the HR Manager.
     */
    public void removePosition(Position key) {
        positions.remove(key);
    }

    public Position getPosition(String position) {
        return positions.get(position);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // The following changes can only be made after storage is also integrated with positions,
        // due to existing test cases.
        // return persons.asUnmodifiableObservableList().size() + " persons\n"
        //      + positions.asUnmodifiableObservableList().size() + " positions";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Position> getPositionList() {
        return positions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HrManager // instanceof handles nulls
                && persons.equals(((HrManager) other).persons));
        // The following changes can only be made after storage is also integrated with positions,
        // due to existing test cases.
                // && positions.equals(((HrManager) other).positions)
    }

    @Override
    public int hashCode() {
        // The following changes can only be made after storage is also integrated with positions,
        // due to existing test cases.
        // return persons.hashCode() ^ positions.hashCode();
        return persons.hashCode();
    }
}
