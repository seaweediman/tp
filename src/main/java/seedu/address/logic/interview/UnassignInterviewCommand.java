package seedu.address.logic.interview;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;


public class UnassignInterviewCommand extends Command {
    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Candidates removed from interview %1$s: %2$s";
    public static final String MESSAGE_CANDIDATE_DID_NOT_APPLY = "Candidate %1$s (%2$s) is not scheduled for " +
            "this Interview: %3$s";

    private Index interviewIndex;

    private Set<Index> candidateIndexes;
    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public UnassignInterviewCommand(Index interview, Set<Index> candidates) {
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

        if (interviewIndex.getZeroBased() >= model.getFilteredInterviewList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interview = lastShownInterviewList.get(interviewIndex.getZeroBased());
        int count = 1;

        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (Index candidateIndex : candidateIndexes) {
            if (candidateIndex.getZeroBased() >= lastShownCandidateList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person candidate = lastShownCandidateList.get(candidateIndex.getZeroBased());
            if (!candidate.hasInterview(interview)) {
                throw new CommandException(String.format(MESSAGE_CANDIDATE_DID_NOT_APPLY, candidateIndex.getOneBased(),
                        candidate.getName(), interview.getDisplayString()));
            }
            interview.removeCandidate(candidate);
            candidate.deleteInterview(interview);
            sb.append(count + ". " + candidate.getName() + "\n");
            count++;
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, interview.getDisplayString(), sb),
                false, false, true, false, false, false, false,
                false);
    }
}


