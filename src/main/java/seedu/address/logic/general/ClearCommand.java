package seedu.address.logic.general;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.model.HrManager;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHrManager(new HrManager());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
