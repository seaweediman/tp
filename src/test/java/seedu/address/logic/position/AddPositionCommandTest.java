package seedu.address.logic.position;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.testutil.PositionBuilder;


public class AddPositionCommandTest {

    @Test
    public void constructor_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPositionCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        AddPositionCommandTest.ModelStubAcceptingPositionAdded modelStub = new AddPositionCommandTest
                .ModelStubAcceptingPositionAdded();
        Position validPosition = new PositionBuilder().build();

        CommandResult commandResult = new AddPositionCommand(validPosition).execute(modelStub);

        assertEquals(String.format(AddPositionCommand.MESSAGE_SUCCESS, validPosition),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPosition), modelStub.positionsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Position validPosition = new PositionBuilder().build();
        AddPositionCommand addPositionCommand = new AddPositionCommand(validPosition);
        AddPositionCommandTest.ModelStub modelStub = new AddPositionCommandTest.ModelStubWithPosition(validPosition);

        assertThrows(CommandException.class, AddPositionCommand.MESSAGE_DUPLICATE_PERSON, () ->
                addPositionCommand.execute(modelStub));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getHrManagerCandidatesFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getHrManagerPositionsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHrManagerCandidatesFilePath(Path hrManagerCandidatesFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHrManagerPositionsFilePath(Path hrManagerPositionsFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHrManager(ReadOnlyHrManager newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHrManager getHrManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPosition(Position position) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePosition(Position target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPosition(Position position) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPosition(Position target, Position editedPosition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Position> getFilteredPositionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPositionList(Predicate<Position> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        public void deletePositionFromPerson(Position p) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInterview(Interview target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInterview(Interview target, Interview editedInterview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInterviewList(Predicate<Interview> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPosition extends ModelStub {
        private final Position position;

        ModelStubWithPosition(Position position) {
            requireNonNull(position);
            this.position = position;
        }

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return this.position.isSamePosition(position);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPositionAdded extends ModelStub {
        final ArrayList<Position> positionsAdded = new ArrayList<>();

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return positionsAdded.stream().anyMatch(position::isSamePosition);
        }

        @Override
        public void addPosition(Position position) {
            requireNonNull(position);
            positionsAdded.add(position);
        }

        @Override
        public ReadOnlyHrManager getHrManager() {
            return new HrManager();
        }
    }
}
