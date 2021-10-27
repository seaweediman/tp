package seedu.address.logic.interview;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.interview.CommandTestUtil.DESC_INTERVIEW_ADMIN_ASSISTANT;
import static seedu.address.logic.interview.CommandTestUtil.DESC_INTERVIEW_MANAGER;
import static seedu.address.logic.interview.CommandTestUtil.VALID_CANDIDATE_INDEX_1;
import static seedu.address.logic.interview.CommandTestUtil.VALID_CANDIDATE_INDEX_SET;
import static seedu.address.logic.interview.CommandTestUtil.VALID_EMPTY_CANDIDATE_INDEX_SET;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN_NAME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_MANAGER_NAME;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.interview.CommandTestUtil.assertEditInterviewCommandSuccess;
import static seedu.address.logic.interview.CommandTestUtil.showInterviewAtIndex;
import static seedu.address.logic.interview.EditInterviewCommand.MESSAGE_CANDIDATE_DID_NOT_APPLY;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_ADMIN_ASSISTANT;
import static seedu.address.model.interview.Interview.MESSAGE_POSITION_CONSTRAINTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERVIEW;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;
import static seedu.address.testutil.TypicalPositions.BOOKKEEPER;
import static seedu.address.testutil.TypicalPositions.CLOSED_POSITION_CLERK;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.logic.general.ClearCommand;
import seedu.address.logic.interview.EditInterviewCommand.EditInterviewDescriptor;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.testutil.EditInterviewDescriptorBuilder;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPositions;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditInterviewCommand.
 */
public class EditInterviewCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() { //todo
        Interview editedInterview = new InterviewBuilder().build();
        EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder(editedInterview, VALID_EMPTY_CANDIDATE_INDEX_SET)
                        .withCandidateIndexes(VALID_CANDIDATE_INDEX_1).build();
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW, descriptor);

        String expectedMessage =
                String.format(EditInterviewCommand.MESSAGE_EDIT_INTERVIEW_SUCCESS, editedInterview.getDisplayString());

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setInterview(model.getFilteredInterviewList().get(0), editedInterview);

        assertEditInterviewCommandSuccess(editInterviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() { //todo
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW,
                new EditInterviewCommand.EditInterviewDescriptor());

        Interview editedInterview = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());

        String expectedMessage =
                String.format(EditInterviewCommand.MESSAGE_EDIT_INTERVIEW_SUCCESS, editedInterview.getDisplayString());

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());

        assertEditInterviewCommandSuccess(editInterviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() { //todo
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);

        Interview interviewInFilteredList = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());

        Interview editedInterview =
                new InterviewBuilder(interviewInFilteredList).withPosition(TypicalPositions.ADMIN_ASSISTANT).build();

        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW,
                new EditInterviewDescriptorBuilder().withPosition(VALID_TITLE_ADMIN_ASSISTANT).build());

        String expectedMessage =
                String.format(EditInterviewCommand.MESSAGE_EDIT_INTERVIEW_SUCCESS, editedInterview.getDisplayString());

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setInterview(model.getFilteredInterviewList().get(0), editedInterview);

        assertEditInterviewCommandSuccess(editInterviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateInterviewUnfilteredList_failure() {
        Interview firstInterview = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());
        EditInterviewCommand.EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder(firstInterview, VALID_CANDIDATE_INDEX_SET).build();
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_SECOND_INTERVIEW, descriptor);

        assertCommandFailure(editInterviewCommand, model, EditInterviewCommand.MESSAGE_DUPLICATE_INTERVIEW);
    }

    @Test
    public void execute_duplicateInterviewFilteredList_failure() {
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);

        // edit interview in filtered list into a duplicate in HR Manager
        Interview interviewInList = model.getHrManager().getInterviewList().get(INDEX_SECOND_INTERVIEW.getZeroBased());
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW,
                new EditInterviewDescriptorBuilder(interviewInList, VALID_CANDIDATE_INDEX_SET).build());

        assertCommandFailure(editInterviewCommand, model, EditInterviewCommand.MESSAGE_DUPLICATE_INTERVIEW);
    }

    @Test
    public void execute_invalidInterviewIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewList().size() + 1);
        EditInterviewCommand.EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder().withPosition(VALID_POSITION_ADMIN_NAME).build();
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of position list
     */
    @Test
    public void execute_invalidInterviewIndexFilteredList_failure() {
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);
        Index outOfBoundIndex = INDEX_SECOND_INTERVIEW;
        // ensures that outOfBoundIndex is still in bounds of position list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrManager().getInterviewList().size());

        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(outOfBoundIndex,
                new EditInterviewDescriptorBuilder().withPosition(VALID_POSITION_MANAGER_NAME).build());

        assertCommandFailure(editInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditInterviewCommand standardCommand =
                new EditInterviewCommand(INDEX_FIRST_INTERVIEW, DESC_INTERVIEW_ADMIN_ASSISTANT);

        // same values -> returns true
        EditInterviewCommand.EditInterviewDescriptor copyDescriptor =
                new EditInterviewCommand.EditInterviewDescriptor(DESC_INTERVIEW_ADMIN_ASSISTANT);
        EditInterviewCommand commandWithSameValues = new EditInterviewCommand(INDEX_FIRST_INTERVIEW, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand
                .equals(new EditInterviewCommand(INDEX_SECOND_INTERVIEW, DESC_INTERVIEW_ADMIN_ASSISTANT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditInterviewCommand(INDEX_FIRST_INTERVIEW, DESC_INTERVIEW_MANAGER)));
    }

    @Test
    public void execute_candidateDidNotApply_throwsCommandException() {
        Interview editedInterview = new InterviewBuilder().build();
        EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder(editedInterview, VALID_EMPTY_CANDIDATE_INDEX_SET)
                        .withCandidateIndexes(VALID_CANDIDATE_INDEX_1).build();
        descriptor.setPosition(BOOKKEEPER);
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW, descriptor);

        String expectedMessage = String.format(MESSAGE_CANDIDATE_DID_NOT_APPLY, BENSON.getName(), BOOKKEEPER);


        assertThrows(CommandException.class, expectedMessage, () -> editInterviewCommand.execute(model));
    }

    @Test
    public void execute_positionIsClosed_throwsCommandException() {
        ModelStubWithJohn modelStub = new ModelStubWithJohn();
        Interview editedInterview = new InterviewBuilder().build();
        EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder(editedInterview, VALID_EMPTY_CANDIDATE_INDEX_SET)
                        .withCandidateIndexes("0").build();
        descriptor.setPosition(CLOSED_POSITION_CLERK);
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW, descriptor);

        assertThrows(CommandException.class,
                MESSAGE_POSITION_CONSTRAINTS, () -> editInterviewCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ModelStubWithJohn modelStub = new ModelStubWithJohn();
        Interview editedInterview = new InterviewBuilder().build();
        EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder(editedInterview, VALID_EMPTY_CANDIDATE_INDEX_SET)
                        .withCandidateIndexes("2").build();
        descriptor.setPosition(CLOSED_POSITION_CLERK);
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW, descriptor);

        assertThrows(CommandException.class,
                MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () -> editInterviewCommand.execute(modelStub));
    }

    @Test
    public void execute_editJohn_success() {
        ModelStubWithJohn modelStub = new ModelStubWithJohn();
        Interview editedInterview = new InterviewBuilder().build();
        EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder(editedInterview, VALID_EMPTY_CANDIDATE_INDEX_SET)
                        .withCandidateIndexes("0").build();
        //edit to bookkeeper on 21/12/2021
        descriptor.setPosition(BOOKKEEPER);
        descriptor.setDate(LocalDate.of(2021, 12, 21));
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW, descriptor);

        //check if John has been edited
        Person expectedPerson = new PersonBuilder(JOHN).withStatus("scheduled").build();
        Interview expectedInterview = new InterviewBuilder().withPosition(BOOKKEEPER).withCandidates(Set.of(JOHN))
                .withDate(LocalDate.of(2021, 12, 21)).build();
        expectedPerson.setInterviews(Set.of(expectedInterview));
        assertEditCommandSuccess(modelStub, expectedPerson, 0, editInterviewCommand);
    }

    @Test
    public void execute_editJohnWithNoCandidateIndexSpecified_success() {
        ModelStubWithJohn modelStub = new ModelStubWithJohn();
        Interview editedInterview = new InterviewBuilder().withCandidates(new HashSet<>()).build();
        EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder(editedInterview, VALID_EMPTY_CANDIDATE_INDEX_SET).build();
        //edit to bookkeeper on 22/12/2021
        descriptor.setPosition(BOOKKEEPER);
        descriptor.setDate(LocalDate.of(2021, 12, 22));
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(INDEX_FIRST_INTERVIEW, descriptor);

        //check if John has been edited
        Person expectedPerson = new PersonBuilder(JOHN).withStatus("scheduled").build();
        Interview expectedInterview = new InterviewBuilder().withPosition(BOOKKEEPER).withCandidates(Set.of(JOHN))
                .withDate(LocalDate.of(2021, 12, 22)).build();
        expectedPerson.setInterviews(Set.of(expectedInterview));
        assertEditCommandSuccess(modelStub, expectedPerson, 0, editInterviewCommand);
    }

    private void assertEditCommandSuccess(ModelStub modelStub, Person expected,
                                          int index, EditInterviewCommand command) {
        try {
            command.execute(modelStub);
            assertTrue(expected.equals(modelStub.getPerson(Index.fromZeroBased(index))));
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * A Model stub that contains Typical Person John Doe and other required information.
     */
    private class ModelStubWithJohn extends ModelStub {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Position> positions = FXCollections.observableArrayList();
        private final ObservableList<Interview> interviews = FXCollections.observableArrayList();

        ModelStubWithJohn() {
            persons.add(JOHN);
            persons.add(ALICE);
            positions.add(CLOSED_POSITION_CLERK);
            positions.add(BOOKKEEPER);
            interviews.add(new InterviewBuilder().withCandidates(Set.of(JOHN)).withPosition(BOOKKEEPER).build());
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            return interviews;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return persons;
        }

        @Override
        public Person getPerson(Index index) {
            return persons.get(index.getZeroBased());
        }

        @Override
        public boolean isPositionClosed(Position toCheck) {
            return toCheck.getStatus() == Position.PositionStatus.CLOSED;
        }

        @Override
        public boolean hasInterview(Interview toAdd) {
            return interviews.contains(toAdd);
        }

        @Override
        public void setInterview(Interview target, Interview editedInterview) {
            persons.get(0).deleteInterview(target);
            persons.get(0).addInterview(editedInterview);
        }

        @Override
        public void updateFilteredInterviewList(Predicate<Interview> predicate) {

        }
    }

}
