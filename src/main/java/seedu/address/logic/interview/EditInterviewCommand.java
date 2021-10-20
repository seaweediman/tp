package seedu.address.logic.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import java.util.Collections;
import java.time.Duration;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.LocalTime;
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
        Interview editedInterview = createEditedInterview(interviewToEdit, editInterviewDescriptor);

        if (!interviewToEdit.isSameInterview(editedInterview) && model.hasInterview(editedInterview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        Set<Person> newCandidates = editedInterview.getCandidates();
        for (Person p : newCandidates) {
            if (!model.hasPerson(p)) {
                throw new CommandException("Candidate " + p.getName() + " not found in HR Manager");
            }
        }

        Position newPosition = editedInterview.getPosition();
        if (model.isPositionClosed(newPosition)) {
            throw new CommandException("Position " + newPosition.getTitle().fullTitle + " is closed");
        }

        model.setInterview(interviewToEdit, editedInterview);
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERVIEW_SUCCESS, editedInterview));
    }

    /**
     * Creates and returns a {@code Interview} with the details of {@code interviewToEdit}
     * edited with {@code editInterviewDescriptor}.
     */
    public static Interview createEditedInterview(
            Interview interviewToEdit, EditInterviewCommand.EditInterviewDescriptor editInterviewDescriptor) {
        assert interviewToEdit != null;

        Position updatedPosition = editInterviewDescriptor.getPosition().orElse(interviewToEdit.getPosition());
        Set<Person> updatedCandidates = editInterviewDescriptor.getCandidates().orElse(interviewToEdit.getCandidates());
        LocalDate updatedDate = editInterviewDescriptor.getDate().orElse(interviewToEdit.getDate());
        LocalTime updatedTime = editInterviewDescriptor.getStartTime().orElse(interviewToEdit.getStartTime());
        Duration updatedDuration = editInterviewDescriptor.getDuration().orElse(interviewToEdit.getDuration());
        InterviewStatus updatedStatus = editInterviewDescriptor.getStatus().orElse(interviewToEdit.getStatus());

        return new Interview(updatedPosition, updatedCandidates, updatedDate,
                updatedTime, updatedDuration, updatedStatus);
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
        private Set<Person> candidates;
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
            setCandidates(toCopy.candidates);
            setDate(toCopy.date);
            setStartTime(toCopy.startTime);
            setDuration(toCopy.duration);
            setStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(position, candidates, date, startTime, duration, status);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        /**
         * Sets {@code candidates} to this object's {@code candidates}.
         * A defensive copy of {@code candidates} is used internally.
         */
        public void setCandidates(Set<Person> candidates) {
            this.candidates = (candidates != null) ? new HashSet<>(candidates) : null;
        }

        /**
         * Returns an unmodifiable candidate set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code candidates} is null.
         */
        public Optional<Set<Person>> getCandidates() {
            return (candidates != null) ? Optional.of(Collections.unmodifiableSet(candidates)) : Optional.empty();
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
                    && getCandidates().equals(e.getCandidates())
                    && getDate().equals(e.getDate())
                    && getStartTime().equals(e.getStartTime())
                    && getDuration().equals(e.getDuration())
                    && getStatus().equals(e.getStatus());
        }
    }
}
