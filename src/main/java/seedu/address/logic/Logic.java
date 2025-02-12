package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the HR Manager.
     *
     * @see seedu.address.model.Model#getHrManager()
     */
    ReadOnlyHrManager getHrManager();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of positions */
    ObservableList<Position> getFilteredPositionList();

    /**
     * Returns the user prefs' address book candidate file path.
     */
    Path getHrManagerCandidatesFilePath();

    /**
     * Returns the user prefs' address book position file path.
     */
    Path getHrManagerPositionsFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
