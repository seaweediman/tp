package seedu.address.logic.candidate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.interview.AddInterviewCommand.MESSAGE_NO_POSITION_FOUND;
import static seedu.address.logic.interview.AddInterviewCommand.MESSAGE_POSITION_CLOSED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.testutil.PersonBuilder;

public class AddCandidateCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCandidateCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCandidateCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCandidateCommand.MESSAGE_SUCCESS, validPerson),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCandidateCommand addCandidateCommand = new AddCandidateCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCandidateCommand.MESSAGE_DUPLICATE_PERSON, () ->
                addCandidateCommand.execute(modelStub));
    }

    @Test
    public void execute_positionDoesNotExist_throwsCommandException() {
        Person validPerson = new PersonBuilder(ALICE).withPositions("Admin").build();
        ModelStubWithSomePositionsAndBob modelStub = new ModelStubWithSomePositionsAndBob();
        AddCandidateCommand addCandidateCommand = new AddCandidateCommand(validPerson);
        String expectedMessage = String.format(MESSAGE_NO_POSITION_FOUND, "Admin");

        assertThrows(CommandException.class, expectedMessage, () ->
                addCandidateCommand.execute(modelStub));
    }

    @Test
    public void execute_positionIsClosed_throwsCommandException() {
        Person validPerson = new PersonBuilder(ALICE).withPositions(ADMIN_ASSISTANT.getTitle().fullTitle).build();
        ModelStubWithSomePositionsAndBob modelStub = new ModelStubWithSomePositionsAndBob();
        AddCandidateCommand addCandidateCommand = new AddCandidateCommand(validPerson);

        String expectedMessage = String.format(MESSAGE_POSITION_CLOSED, ADMIN_ASSISTANT.getTitle().fullTitle);

        assertThrows(CommandException.class, expectedMessage, () ->
                addCandidateCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCandidateCommand addAliceCommand = new AddCandidateCommand(alice);
        AddCandidateCommand addBobCommand = new AddCandidateCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCandidateCommand addAliceCommandCopy = new AddCandidateCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Path getHrManagerInterviewsFilePath() {
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
        public void setHrManagerInterviewsFilePath(Path hrManagerInterviewsFilePath) {
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

        public boolean isPositionClosed(Position p) {
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
        public Person getPerson(Index index) {
            throw new AssertionError("This method should not be called.");
        }
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

        public void deleteInterviewFromPerson(Interview i) {
            throw new AssertionError("This method should not be called.");
        }

        public void deletePersonFromInterview(Person p) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that has position ADMIN_ASSISTANT and a person BOB.
     */
    private class ModelStubWithSomePositionsAndBob extends ModelStub {
        private final Set<Position> positions = new HashSet<>();
        private final Set<Person> persons = new HashSet<>();

        ModelStubWithSomePositionsAndBob() {
            persons.add(BOB);
            positions.add(ADMIN_ASSISTANT);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.persons.contains(person);
        }

        @Override
        public boolean hasPosition(Position position) {
            for (Position p : positions) {
                if (p.isSamePosition(position)) {
                    return true;
                }
            }
            return false;
        }

        //For some reason p.getStatus() is always open even when building with closed
        @Override
        public boolean isPositionClosed(Position p) {
            return true;
        }

        @Override
        public void addPerson(Person person) {
            persons.add(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyHrManager getHrManager() {
            return new HrManager();
        }

        @Override
        public boolean hasPosition(Position position) {
            return true;
        }

        @Override
        public boolean isPositionClosed(Position p) {
            return false;
        }
    }
}
