package seedu.address.logic.interview;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

import java.util.HashSet;
import java.util.Set;

import static seedu.address.logic.interview.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.interview.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.interview.UnassignInterviewCommand.MESSAGE_ALL_CANDIDATES_REMOVED;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
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


        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("1. " + person.getName() + "\n");

        String expectedMessage = String.format(UnassignInterviewCommand.MESSAGE_SUCCESS,
                interviewToUnassign.getDisplayStringWithoutNames(), sb);

        person.addInterview(interviewToUnassign);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false,
                true, false, false, false, false,
                false);

        ModelManager expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());

        assertCommandSuccess(unassignInterviewCommand, model, expectedCommandResult, expectedModel);
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
    public void execute_validIndexUnfilteredListTotalWipe_success() {
        UnassignInterviewCommand unassignInterviewCommand = new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, true);
        Interview interviewToUnassign = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());

        Person alice = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person benson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        String expectedMessage = String.format(MESSAGE_ALL_CANDIDATES_REMOVED,
                interviewToUnassign.getDisplayStringWithoutNames());

        alice.addInterview(interviewToUnassign);
        benson.addInterview(interviewToUnassign);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false,
                true, false, false, false, false,
                false);

        ModelManager expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());

        assertCommandSuccess(unassignInterviewCommand, model, expectedCommandResult, expectedModel);
    }
}