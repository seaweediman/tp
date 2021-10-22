package seedu.address.logic.position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.position.FindPositionCommandPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindPositionCommandTest {
    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void equals() {
        FindPositionCommandPredicate firstPredicate =
                new FindPositionCommandPredicate(Collections.singletonList("first"));
        FindPositionCommandPredicate secondPredicate =
                new FindPositionCommandPredicate(Collections.singletonList("second"));

        FindPositionCommand findFirstCommand = new FindPositionCommand(firstPredicate);
        FindPositionCommand findSecondCommand = new FindPositionCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPositionCommand findFirstCommandCopy = new FindPositionCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multiplePositionsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindPositionCommandPredicate predicate = preparePredicate("Assistant");
        FindPositionCommand command = new FindPositionCommand(predicate);
        expectedModel.updateFilteredPositionList(predicate);
        assertEquals(Arrays.asList(ADMIN_ASSISTANT), expectedModel.getFilteredPositionList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FindPositionCommandPredicate preparePredicate(String userInput) {
        List<String> output = new ArrayList<String>(Arrays.asList(userInput.split("\\s+")));
        output.removeAll(Arrays.asList("", null));
        return new FindPositionCommandPredicate(output);
    }
}
