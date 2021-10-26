package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * Adds an interview to the HR Manager.
 */
public class AddInterviewCommand extends Command {
    public static final String COMMAND_WORD = "add_i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the HR Manager. "
            + "Parameters: "
            + PREFIX_POSITION + "POSITION "
            + "" + PREFIX_CANDIDATE_INDEX + "INDEX (must be a positive integer)... "
            + PREFIX_DATE + "DD/MM/YYYY "
            + PREFIX_TIME + "HHMM "
            + PREFIX_DURATION + "MINUTES (must be a positive integer) \n"
            + "[" + PREFIX_INTERVIEW_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_POSITION + "Accountant "
            + PREFIX_CANDIDATE_INDEX + "1 "
            + PREFIX_DATE + "15/10/2021 "
            + PREFIX_TIME + "1400 "
            + PREFIX_DURATION + "120 "
            + PREFIX_INTERVIEW_STATUS + "pending";

    public static final String MESSAGE_SUCCESS = "New interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the HR Manager";
    public static final String MESSAGE_NO_POSITION_FOUND = "Position %1$s not found in HR Manager";
    public static final String MESSAGE_POSITION_CLOSED = "Position %1$s is closed";
    public static final String MESSAGE_CANDIDATE_DID_NOT_APPLY = "Candidate %1$s did not apply for Position %2$s";

    private final Interview toAdd;
    private final Set<Index> indexes;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public AddInterviewCommand(Interview interview, Set<Index> indexSet) {
        requireNonNull(interview);
        toAdd = interview;
        indexes = indexSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Position position = toAdd.getPosition();
        if (!model.hasPosition(position)) {
            throw new CommandException(String.format(MESSAGE_NO_POSITION_FOUND, position.getTitle()));
        }

        if (model.isPositionClosed(position)) {
            throw new CommandException(String.format(MESSAGE_POSITION_CLOSED, position.getTitle()));
        }
        //loads candidates from set of index
        Set<Person> candidates = new HashSet<>();
        for (Index index : indexes) {
            if (index.getZeroBased() < model.getFilteredPersonList().size()) {
                Person person = model.getPerson(index);
                //checks if person applied for position
                if (!person.appliedForPosition(position)) {
                    throw new CommandException(String.format(MESSAGE_CANDIDATE_DID_NOT_APPLY,
                            person.getName(), position));
                }
                candidates.add(person);
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
        toAdd.setCandidates(candidates);

        if (model.hasInterview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        for (Person p : model.getFilteredPersonList()) {
            if (toAdd.hasCandidate(p)) {
                p.addInterview(toAdd);
            }
        }

        model.addInterview(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getDisplayString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewCommand // instanceof handles nulls
                && toAdd.equals(((AddInterviewCommand) other).toAdd))
                && indexes.equals(((AddInterviewCommand) other).indexes);
    }
}
