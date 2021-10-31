package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.EditCandidateCommand;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

public class AssignInterviewCommand extends Command {
    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the candidates specified by their candidate index from an interview specified by its "
            + "interview index.\n"
            + "Parameters: "
            + PREFIX_INTERVIEW_INDEX + "INTERVIEW_INDEX "
            + PREFIX_CANDIDATE_INDEX + "CANDIDATE_INDEX (must be a positive integer)...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INTERVIEW_INDEX + "1 "
            + PREFIX_CANDIDATE_INDEX + "1 3 ";

    public static final String MESSAGE_SUCCESS = "Candidates added to interview %1$s: %2$s";
    public static final String MESSAGE_CANDIDATE_DID_NOT_APPLY = "Candidate %1$s (%2$s) did not apply for "
            + "this position: %3$s";

    private Index interviewIndex;

    private Set<Index> candidateIndexes;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public AssignInterviewCommand(Index interview, Set<Index> candidates) {
        requireNonNull(interview);
        requireNonNull(candidates);

        interviewIndex = interview;
        candidateIndexes = candidates;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownCandidateList = model.getFilteredPersonList();
        List<Interview> lastShownInterviewList = model.getFilteredInterviewList();
        CommandResult result;

        if (interviewIndex.getZeroBased() >= model.getFilteredInterviewList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interview = lastShownInterviewList.get(interviewIndex.getZeroBased());
        EditInterviewCommand.EditInterviewDescriptor descriptor = new EditInterviewCommand.EditInterviewDescriptor();
        descriptor.setCandidateIndexes(candidateIndexes);

        Interview assignedInterview = EditInterviewCommand.createEditedInterview(interview, descriptor).getFirst();
        Set<Person> newCandidates = interview.getCandidates();

        StringBuilder candidatesAdded = new StringBuilder();
        candidatesAdded.append("\n");

        EditCandidateCommand.EditPersonDescriptor personDescriptor = new EditCandidateCommand.EditPersonDescriptor();
        personDescriptor.setInterviews(new HashSet<>(List.of(assignedInterview)));

        int count = 1;
        for (Index candidateIndex : candidateIndexes) {
            if (candidateIndex.getZeroBased() >= lastShownCandidateList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person candidate = lastShownCandidateList.get(candidateIndex.getZeroBased());
            Position position = interview.getPosition();

            if (!candidate.appliedForPosition(position)) {
                throw new CommandException(String.format(MESSAGE_CANDIDATE_DID_NOT_APPLY,
                        candidateIndex.getOneBased(), candidate.getName(), interview.getPositionTitle()));
            }
            newCandidates.add(candidate);
            Person editedPerson = EditCandidateCommand.createEditedPerson(candidate, personDescriptor);
            model.setPerson(candidate, editedPerson);

            candidatesAdded.append(count + ". " + candidate.getName() + "\n");
            count++;
        }
        assignedInterview.setCandidates(newCandidates);
        model.setInterview(interview, assignedInterview);

        result = new CommandResult(String.format(MESSAGE_SUCCESS, interview.getDisplayStringWithoutNames(),
                candidatesAdded), false, false, false, false, false,
                false, false, false, false, false, true);

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignInterviewCommand// instanceof handles nulls
                && interviewIndex.equals(((AssignInterviewCommand) other).interviewIndex)); // state check
    }

}
