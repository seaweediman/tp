//package seedu.address.logic.interview;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import org.junit.jupiter.api.Test;
//import seedu.address.commons.core.GuiSettings;
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.CommandResult;
//import seedu.address.model.*;
//import seedu.address.model.interview.Interview;
//import seedu.address.model.person.Person;
//import seedu.address.model.position.Position;
//import seedu.address.testutil.InterviewBuilder;
//import seedu.address.testutil.PersonBuilder;
//
//import java.nio.file.Path;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.function.Predicate;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.interview.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.interview.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.interview.UnassignInterviewCommand.MESSAGE_ALL_CANDIDATES_REMOVED;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERVIEW;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//import static seedu.address.testutil.TypicalPersons.*;
//
//public class UnassignInterviewCommandTest {
//
//    private Model model = new ModelStubWithPersonsWithInterview();
//
//    @Test
//    public void execute_validIndexUnfilteredList_success() {
//        Set<Index> indexes = new HashSet<>();
//        indexes.add(INDEX_FIRST_PERSON);
//        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, indexes);
//        Interview interviewToUnassign = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("\n");
//        sb.append("1. " + model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getName() + "\n");
//
//        String expectedMessage = String.format(UnassignInterviewCommand.MESSAGE_SUCCESS,
//                interviewToUnassign.getDisplayStringWithoutNames(), sb);
//
//        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false,
//                true, false, false, false, false,
//                false);
//
//        ModelStubWithPersonsWithInterview expectedModel = new ModelStubWithPersonsWithInterview();
//        expectedModel.removeJohnFromInterview();
//
//        assertCommandSuccess(unassignInterviewCommand, model, expectedCommandResult, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Set<Index> indexes = new HashSet<>();
//        indexes.add(INDEX_FIRST_PERSON);
//
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewList().size() + 1);
//        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(outOfBoundIndex, indexes);
//
//        assertCommandFailure(unassignInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void execute_validIndexUnfilteredListDeleteAll_success() {
//        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, true);
//        Interview interviewToUnassign = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());
//
//        String expectedMessage = String.format(MESSAGE_ALL_CANDIDATES_REMOVED, interviewToUnassign.getDisplayString());
//
//        ALICE.addInterview(interviewToUnassign);
//        BENSON.addInterview(interviewToUnassign);
//
//        ModelManager expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());
//
//        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
//                false, false, true, false, false, false, false,
//                false);
//
//        assertCommandSuccess(unassignInterviewCommand, model, expectedCommandResult, expectedModel);
//        ALICE.resetStatus();
//        BENSON.resetStatus();
//    }
//
//    @Test
//    public void equals() {
//        Set<Index> indexes = new HashSet<>();
//        indexes.add(INDEX_FIRST_PERSON);
//        indexes.add(INDEX_SECOND_PERSON);
//
//        UnassignInterviewCommand deleteFirstCommand = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, indexes);
//        UnassignInterviewCommand deleteSecondCommand = new UnassignInterviewCommand(INDEX_SECOND_INTERVIEW, indexes);
//
//        // same object -> returns true
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
//
//        // same values -> returns true
//        UnassignInterviewCommand unassignFirstCommandCopy = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, indexes);
//        assertTrue(deleteFirstCommand.equals(unassignFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(deleteFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(deleteFirstCommand.equals(null));
//
//        // different position -> returns false
//        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
//    }
//
//    /**
//     * A default model stub that have all of the methods failing.
//     */
//    private class ModelStub implements Model {
//        @Override
//        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyUserPrefs getUserPrefs() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public GuiSettings getGuiSettings() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setGuiSettings(GuiSettings guiSettings) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getHrManagerCandidatesFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getHrManagerPositionsFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getHrManagerInterviewsFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setHrManagerCandidatesFilePath(Path hrManagerCandidatesFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setHrManagerPositionsFilePath(Path hrManagerPositionsFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setHrManagerInterviewsFilePath(Path hrManagerInterviewsFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addPerson(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setHrManager(ReadOnlyHrManager newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyHrManager getHrManager() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasPerson(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasPosition(Position position) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deletePosition(Position target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addPosition(Position position) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setPosition(Position target, Position editedPosition) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Position> getFilteredPositionList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredPositionList(Predicate<Position> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deletePerson(Person target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setPerson(Person target, Person editedPerson) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Person> getFilteredPersonList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredPersonList(Predicate<Person> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        public void deletePositionFromPerson(Position p) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        public boolean isPositionClosed(Position p) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasInterview(Interview interview) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteInterview(Interview target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Person getPerson(Index index) {
//            throw new AssertionError("This method should not be called.");
//        }
//        public void addInterview(Interview interview) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setInterview(Interview target, Interview editedInterview) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Interview> getFilteredInterviewList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredInterviewList(Predicate<Interview> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        public void deleteInterviewFromPerson(Interview i) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        public void deletePersonFromInterview(Person p) {
//            throw new AssertionError("This method should not be called.");
//        }
//    }
//
//    private class ModelStubWithPersonsWithInterview extends ModelStub {
//        private ObservableList<Person> personsList = FXCollections.observableArrayList();
//        private ObservableList<Interview> interviewsList = FXCollections.observableArrayList();
//
//        public ModelStubWithPersonsWithInterview() {
//            Person John = new PersonBuilder().withName("John").build();
//            Person Mark = new PersonBuilder().withName("Mark").build();
//            Set<Person> candidates = new HashSet<>();
//            candidates.add(John);
//            candidates.add(Mark);
//
//            Interview interview = new InterviewBuilder().withCandidates(candidates).build();
//
//            addPerson(John);
//            addPerson(Mark);
//            John.addInterview(interview);
//            Mark.addInterview(interview);
//            addInterview(interview);
//        }
//
//        public void deleteInterviewFromPerson(Interview i) {
//            for (Person p : personsList) {
//                p.deleteInterview(i);
//            }
//        }
//
//        public void addPerson(Person p) {
//            personsList.add(p);
//        }
//
//        public void addInterview(Interview i) {
//            interviewsList.add(i);
//        }
//
//        @Override
//        public void updateFilteredPersonList(Predicate<Person> predicate) {
//            //empty method that does nothing for the stub to always accept
//        }
//
//        @Override
//        public void updateFilteredInterviewList(Predicate<Interview> predicate) {
//            //empty method that does nothing for the stub to always accept
//        }
//
//        @Override
//        public ObservableList<Person> getFilteredPersonList() {
//            return personsList;
//        }
//
//        @Override
//        public ObservableList<Interview> getFilteredInterviewList() {
//            return interviewsList;
//        }
//
//        public void removeJohnFromInterview() {
//            interviewsList.get(0).removeCandidate(personsList.get(0));
//            personsList.get(0).deleteInterview(interviewsList.get(0));
//        }
//    }
//}
