package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Name;
import seedu.address.model.position.Position;

/**
 * Adds an interview to the HR Manager.
 */
public class AddInterviewCommand extends Command {
    public static final String COMMAND_WORD = "add_i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the HR Manager. "
            + "Parameters: "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_CANDIDATE_NAME + "CANDIDATE "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_DURATION + "DURATION...\n"
            + "[" + PREFIX_INTERVIEW_STATUS + "STATUS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_POSITION + "Accountant "
            + PREFIX_CANDIDATE_NAME + "John Doe "
            + PREFIX_DATE + "15/10/2021 "
            + PREFIX_TIME + "1400 "
            + PREFIX_DURATION + "120 "
            + PREFIX_INTERVIEW_STATUS + "pending";

    public static final String MESSAGE_SUCCESS = "New interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the HR Manager";
    public static final String MESSAGE_NO_PERSON_FOUND = "Person not found in HR Manager";
    public static final String MESSAGE_NO_POSITION_FOUND = "Position not found in HR Manager";

    private final Interview toAdd;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public AddInterviewCommand(Interview interview) {
        requireNonNull(interview);
        toAdd = interview;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInterview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        for (Name personName : toAdd.getCandidates()) {
            if (!model.hasPersonWithName(personName)) {
                throw new CommandException(MESSAGE_NO_PERSON_FOUND);
            }
        }

        Position position = toAdd.getPosition();
        if (!model.hasPosition(position)) {
            throw new CommandException(MESSAGE_NO_POSITION_FOUND);
        }

        model.addInterview(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewCommand // instanceof handles nulls
                && toAdd.equals(((AddInterviewCommand) other).toAdd));
    }
}
