package seedu.address.logic.candidate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.candidate.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.candidate.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.candidate.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.candidate.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.candidate.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.interview.AddInterviewCommand.MESSAGE_NO_POSITION_FOUND;
import static seedu.address.logic.interview.AddInterviewCommand.MESSAGE_POSITION_CLOSED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;
import static seedu.address.testutil.TypicalPositions.CLOSED_POSITION_CLERK;
import static seedu.address.testutil.TypicalPositions.HR_MANAGER;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.candidate.EditCandidateCommand.EditPersonDescriptor;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.logic.general.ClearCommand;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCandidateCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().withRemark("She likes aardvarks.").build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCandidateCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCandidateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCandidateCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCandidateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCandidateCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());

        assertCommandSuccess(editCandidateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCandidateCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCandidateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCandidateCommand, model, EditCandidateCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getHrManager().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCandidateCommand, model, EditCandidateCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCandidateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrManager().getPersonList().size());

        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCandidateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCandidateCommand standardCommand = new EditCandidateCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCandidateCommand commandWithSameValues = new EditCandidateCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCandidateCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCandidateCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void execute_positionDoesNotExist_throwsCommandException() {
        Set<Position> tempSet = new HashSet<>();
        tempSet.add(new Position(new Title("Admin")));
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setPositions(tempSet);
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(INDEX_FIRST_PERSON,
                editPersonDescriptor);
        ModelStubWithObservable modelStub = new ModelStubWithObservable();
        String expectedMessage = String.format(MESSAGE_NO_POSITION_FOUND, "Admin");

        assertThrows(CommandException.class, expectedMessage, () ->
                editCandidateCommand.execute(modelStub));
    }

    @Test
    public void execute_positionIsClosed_throwsCommandException() {
        Set<Position> tempSet = new HashSet<>();
        tempSet.add(CLOSED_POSITION_CLERK);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setPositions(tempSet);
        EditCandidateCommand editCandidateCommand = new EditCandidateCommand(INDEX_FIRST_PERSON,
                editPersonDescriptor);
        ModelStubWithObservable modelStub = new ModelStubWithObservable();

        String expectedMessage = String.format(MESSAGE_POSITION_CLOSED, CLOSED_POSITION_CLERK.getTitle().fullTitle);

        assertThrows(CommandException.class, expectedMessage, () ->
                editCandidateCommand.execute(modelStub));
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
     * A Model stub that has position ADMIN_ASSISTANT and a person BOB.
     */
    private class ModelStubWithObservable extends ModelStub {
        private ObservableList<Person> persons = FXCollections.observableArrayList();
        private ObservableList<Position> positions = FXCollections.observableArrayList();

        ModelStubWithObservable() {
            persons.add(BOB);
            persons.add(ALICE);
            positions.add(ADMIN_ASSISTANT);
            positions.add(HR_MANAGER);
            positions.add(CLOSED_POSITION_CLERK);
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

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return persons;
        }
    }

}
