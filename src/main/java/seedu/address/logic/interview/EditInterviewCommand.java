package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Interview.InterviewStatus;
import seedu.address.model.interview.Tuple;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

public class EditInterviewCommand extends Command {

    public static final String COMMAND_WORD = "edit_i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the interview identified "
            + "by the index number used in the displayed interview list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_CANDIDATE_INDEX + "CANDIDATE]...\n"
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_INTERVIEW_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "15/10/2021 "
            + PREFIX_TIME + "1400 ";

    public static final String MESSAGE_EDIT_INTERVIEW_SUCCESS = "Edited Interview: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the interview list.";
    public static final String MESSAGE_CANDIDATE_DID_NOT_APPLY = "Candidate %1$s did not apply for Position %2$s";


    private final Index index;
    private final EditInterviewCommand.EditInterviewDescriptor editInterviewDescriptor;

    /**
     * @param index                of the interview in the filtered interview list to edit
     * @param editInterviewDescriptor details to edit the interview with
     */
    public EditInterviewCommand(Index index, EditInterviewCommand.EditInterviewDescriptor editInterviewDescriptor) {
        requireNonNull(index);
        requireNonNull(editInterviewDescriptor);

        this.index = index;
        this.editInterviewDescriptor = new EditInterviewCommand.EditInterviewDescriptor(editInterviewDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToEdit = lastShownList.get(index.getZeroBased());
        Tuple<Interview, Set<Index>> parsedDetails = createEditedInterview(interviewToEdit, editInterviewDescriptor);
        Interview editedInterview = parsedDetails.getFirst();

        if (!interviewToEdit.isSameInterview(editedInterview) && model.hasInterview(editedInterview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        Set<Index> newCandidateIndexes = parsedDetails.getSecond();

        boolean isCandidateSetEdited = newCandidateIndexes.size() != 0;

        // if candidates field is edited, list of updated candidate indexed will not be empty
        if (isCandidateSetEdited) {
            Set<Person> newCandidates = new HashSet<>();
            for (Index i : newCandidateIndexes) {
                if (i.getZeroBased() < model.getFilteredPersonList().size()) {
                    Person person = model.getPerson(i);
                    if (!person.appliedForPosition(editedInterview.getPosition())) {
                        throw new CommandException(String.format(MESSAGE_CANDIDATE_DID_NOT_APPLY,
                                person.getName(), editedInterview.getPosition()));
                    }
                    newCandidates.add(person);
                } else {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
            }
            editedInterview.setCandidates(newCandidates); // update edited interview with updated list of candidates
        }

        Position newPosition = editedInterview.getPosition();
        if (model.isPositionClosed(newPosition)) {
            throw new CommandException(Interview.MESSAGE_POSITION_CONSTRAINTS);
        }

        model.setInterview(interviewToEdit, editedInterview);

        // update candidate list
        if (isCandidateSetEdited) {
            // if candidate set is edited, delete old interview from all initial candidates,
            // then add edited interview to all candidates in updated candidate set
            for (Person candidate : interviewToEdit.getCandidates()) {
                candidate.deleteInterview(interviewToEdit);
            }
            for (Person candidate : editedInterview.getCandidates()) {
                candidate.addInterview(editedInterview);
            }
        } else {
            // if candidate set not edited, update interviews of candidates from initial candidate set.
            for (Person candidate : interviewToEdit.getCandidates()) {
                candidate.deleteInterview(interviewToEdit);
                candidate.addInterview(editedInterview);
            }
        }

        return new CommandResult(String.format(MESSAGE_EDIT_INTERVIEW_SUCCESS, editedInterview.getDisplayString()),
                false, false, false, false, true, false, false, false, false, false, true);
    }

    /**
     * Creates and returns a {@code Interview} with the details of {@code interviewToEdit}
     * edited with {@code editInterviewDescriptor}.
     */
    public static Tuple<Interview, Set<Index>> createEditedInterview(
            Interview interviewToEdit, EditInterviewCommand.EditInterviewDescriptor editInterviewDescriptor) {
        assert interviewToEdit != null;

        Position updatedPosition = editInterviewDescriptor.getPosition().orElse(interviewToEdit.getPosition());
        // if candidates not updated, list of updated candidate index not present
        Set<Index> updatedCandidateIndex =
                editInterviewDescriptor.getCandidateIndexes().orElse(new HashSet<>());
        Set<Person> initialCandidates = interviewToEdit.getCandidates(); // initial list of candidates
        LocalDate updatedDate = editInterviewDescriptor.getDate().orElse(interviewToEdit.getDate());
        LocalTime updatedTime = editInterviewDescriptor.getStartTime().orElse(interviewToEdit.getStartTime());
        Duration updatedDuration = editInterviewDescriptor.getDuration().orElse(interviewToEdit.getDuration());
        InterviewStatus updatedStatus = editInterviewDescriptor.getStatus().orElse(interviewToEdit.getStatus());

        Interview newInterview = new Interview(updatedPosition, initialCandidates, updatedDate,
                updatedTime, updatedDuration, updatedStatus); //new interview with all fields except candidates updated

        return new Tuple<>(newInterview, updatedCandidateIndex);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInterviewCommand)) {
            return false;
        }

        // state check
        EditInterviewCommand e = (EditInterviewCommand) other;
        return index.equals(e.index)
                && editInterviewDescriptor.equals(e.editInterviewDescriptor);
    }

    /**
     * Stores the details to edit the candidate with. Each non-empty field value will replace the
     * corresponding field value of the candidate.
     */
    public static class EditInterviewDescriptor {
        private Position position;
        private Set<Index> candidateIndexes;
        private LocalDate date;
        private LocalTime startTime;
        private Duration duration;
        private InterviewStatus status;

        public EditInterviewDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInterviewDescriptor(EditInterviewCommand.EditInterviewDescriptor toCopy) {
            setPosition(toCopy.position);
            setCandidateIndexes(toCopy.candidateIndexes);
            setDate(toCopy.date);
            setStartTime(toCopy.startTime);
            setDuration(toCopy.duration);
            setStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(position, candidateIndexes, date, startTime, duration, status);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        /**
         * Sets {@code candidateIndexes} to this object's {@code candidateIndexes}.
         * A defensive copy of {@code candidateIndexes} is used internally.
         */
        public void setCandidateIndexes(Set<Index> candidatesIndex) {
            this.candidateIndexes = candidatesIndex;
        }

        /**
         * Returns an unmodifiable candidateIndex set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code candidates} is null.
         */
        public Optional<Set<Index>> getCandidateIndexes() {
            return (candidateIndexes != null)
                    ? Optional.of(Collections.unmodifiableSet(candidateIndexes))
                    : Optional.empty();
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setStatus(InterviewStatus status) {
            this.status = status;
        }

        public Optional<InterviewStatus> getStatus() {
            return Optional.ofNullable(status);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInterviewCommand.EditInterviewDescriptor)) {
                return false;
            }

            // state check
            EditInterviewCommand.EditInterviewDescriptor e = (EditInterviewCommand.EditInterviewDescriptor) other;

            return getPosition().equals(e.getPosition())
                    && getCandidateIndexes().equals(e.getCandidateIndexes())
                    && getDate().equals(e.getDate())
                    && getStartTime().equals(e.getStartTime())
                    && getDuration().equals(e.getDuration())
                    && getStatus().equals(e.getStatus());
        }
    }
}
