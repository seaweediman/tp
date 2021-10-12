package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Position> PREDICATE_SHOW_ALL_POSITIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' HR Manager Candidate file path.
     */
    Path getHrManagerCandidatesFilePath();

    /**
     * Returns the user prefs' HR Manager Position file path.
     */
    Path getHrManagerPositionsFilePath();

    /**
     * Sets the user prefs' HR Manager candidate file path.
     */
    void setHrManagerCandidatesFilePath(Path hrManagerCandidatesFilePath);

    /**
     * Sets the user prefs' HR Manager position file path.
     */
    void setHrManagerPositionsFilePath(Path hrManagerPositionsFilePath);

    /**
     * Replaces HR Manager data with the data in {@code addressBook}.
     */
    void setHrManager(ReadOnlyHrManager addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyHrManager getHrManager();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the HR Manager.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the HR Manager.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the HR Manager.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the HR Manager.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the HR Manager.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a position with the same identity as {@code position} exists in the HR Manager.
     */
    boolean hasPosition(Position position);

    /**
     * Deletes the given position.
     * The position must exist in the HR Manager.
     */
    void deletePosition(Position target);

    /**
     * Adds the given position.
     * {@code position} must not already exist in the HR Manager.
     */
    void addPosition(Position position);

    /**
     * Replaces the given position {@code target} with {@code editedPosition}.
     * {@code target} must exist in the HR Manager.
     * The position identity of {@code editedPosition} must not be the same as another existing position in the HR
     * Manager.
     */
    void setPosition(Position target, Position editedPosition);

    /**
     * Returns an unmodifiable view of the filtered position list
     */
    ObservableList<Position> getFilteredPositionList();

    /**
     * Updates the filter of the filtered position list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPositionList(Predicate<Position> predicate);
}
