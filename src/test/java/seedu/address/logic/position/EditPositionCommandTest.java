package seedu.address.logic.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.position.CommandTestUtil.DESC_ADMIN_ASSISTANT;
import static seedu.address.logic.position.CommandTestUtil.DESC_BOOKKEEPER;
import static seedu.address.logic.position.CommandTestUtil.VALID_STATUS_OPEN;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_ADMIN_ASSISTANT;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_BOOKKEEPER;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_HR_MANAGER;
import static seedu.address.logic.position.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.position.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.position.CommandTestUtil.showPositionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POSITION;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.general.ClearCommand;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.position.Position;
import seedu.address.testutil.EditPositionDescriptorBuilder;
import seedu.address.testutil.PositionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPositionCommand.
 */
public class EditPositionCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Position editedPosition =
                new PositionBuilder().withTitle("Business Analyst").withStatus(VALID_STATUS_OPEN).build();
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder(editedPosition).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION, descriptor);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPosition(model.getFilteredPositionList().get(0), editedPosition);

        assertCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPosition = Index.fromOneBased(model.getFilteredPositionList().size());
        Position lastPosition = model.getFilteredPositionList().get(indexLastPosition.getZeroBased());

        PositionBuilder positionInList = new PositionBuilder(lastPosition);
        Position editedPosition =
                positionInList.withTitle(VALID_TITLE_HR_MANAGER).withStatus(VALID_STATUS_OPEN).build();

        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_HR_MANAGER)
                        .withPositionStatus(VALID_STATUS_OPEN).build();

        EditPositionCommand editPositionCommand = new EditPositionCommand(indexLastPosition, descriptor);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPosition(lastPosition, editedPosition);

        assertCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionCommand.EditPositionDescriptor());
        Position editedPosition = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());

        assertCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        Position positionInFilteredList = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        Position editedPosition =
                new PositionBuilder(positionInFilteredList).withTitle(VALID_TITLE_ADMIN_ASSISTANT).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_ADMIN_ASSISTANT).build());

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPosition(model.getFilteredPositionList().get(0), editedPosition);

        assertCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePositionUnfilteredList_failure() {
        Position firstPosition = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder(firstPosition).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_SECOND_POSITION, descriptor);

        assertCommandFailure(editPositionCommand, model, EditPositionCommand.MESSAGE_DUPLICATE_POSITION);
    }

    @Test
    public void execute_duplicatePositionFilteredList_failure() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        // edit position in filtered list into a duplicate in HR Manager
        Position positionInList = model.getHrManager().getPositionList().get(INDEX_SECOND_POSITION.getZeroBased());
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionDescriptorBuilder(positionInList).build());

        assertCommandFailure(editPositionCommand, model, EditPositionCommand.MESSAGE_DUPLICATE_POSITION);
    }

    @Test
    public void execute_invalidPositionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPositionList().size() + 1);
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_BOOKKEEPER).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of position list
     */
    @Test
    public void execute_invalidPositionIndexFilteredList_failure() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);
        Index outOfBoundIndex = INDEX_SECOND_POSITION;
        // ensures that outOfBoundIndex is still in bounds of position list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrManager().getPositionList().size());

        EditPositionCommand editPositionCommand = new EditPositionCommand(outOfBoundIndex,
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_BOOKKEEPER).build());

        assertCommandFailure(editPositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPositionCommand standardCommand = new EditPositionCommand(INDEX_FIRST_PERSON, DESC_ADMIN_ASSISTANT);

        // same values -> returns true
        EditPositionCommand.EditPositionDescriptor copyDescriptor =
                new EditPositionCommand.EditPositionDescriptor(DESC_ADMIN_ASSISTANT);
        EditPositionCommand commandWithSameValues = new EditPositionCommand(INDEX_FIRST_POSITION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPositionCommand(INDEX_SECOND_POSITION, DESC_ADMIN_ASSISTANT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPositionCommand(INDEX_FIRST_PERSON, DESC_BOOKKEEPER)));
    }

}
