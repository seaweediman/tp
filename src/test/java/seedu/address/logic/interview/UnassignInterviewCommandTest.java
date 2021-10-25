package seedu.address.logic.interview;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

public class UnassignInterviewCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);
        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, indexes);
        Interview interviewToUnassign = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("1. " + model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getName() + "\n");

        String expectedMessage = String.format(UnassignInterviewCommand.MESSAGE_SUCCESS,
                interviewToUnassign.getDisplayString(), sb);

        ALICE.addInterview(interviewToUnassign);

        ModelManager expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false,
                true, false, false, false, false,
                false);

        assertCommandSuccess(unassignInterviewCommand, model, expectedCommandResult, expectedModel);
        ALICE.resetStatus();
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewList().size() + 1);
        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(outOfBoundIndex, indexes);

        assertCommandFailure(unassignInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);
        indexes.add(INDEX_SECOND_PERSON);

        UnassignInterviewCommand deleteFirstCommand = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, indexes);
        UnassignInterviewCommand deleteSecondCommand = new UnassignInterviewCommand(INDEX_SECOND_INTERVIEW, indexes);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        UnassignInterviewCommand unassignFirstCommandCopy = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, indexes);
        assertTrue(deleteFirstCommand.equals(unassignFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different position -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
