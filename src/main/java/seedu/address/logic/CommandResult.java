package seedu.address.logic;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.candidate.FindCandidateCommand;
import seedu.address.logic.candidate.ListCandidateCommand;
import seedu.address.logic.interview.ListInterviewCommand;
import seedu.address.logic.position.FindPositionCommand;
import seedu.address.logic.position.ListPositionCommand;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean isShowHelp;

    /**
     * The application should exit.
     */
    private final boolean isExit;

    /**
     * The application should display candidate list.
     */
    private final boolean isListC;

    /**
     * The application should display position list.
     */
    private final boolean isListP;

    /**
     * The application should display interview list.
     */
    private final boolean isListI;

    /**
     * The application should display find candidate list.
     */
    private final boolean isFindC;

    /**
     * The application should display position list.
     */
    private final boolean isFindP;

    /**
     * The application should display interview list.
     */
    private final boolean isFindI;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser,
                         boolean isShowHelp, boolean isExit, boolean isListC, boolean isListP, boolean isListI,
                        boolean isFindC, boolean isFindP, boolean isFindI) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
        this.isListC = isListC;
        this.isListP = isListP;
        this.isListI = isListI;
        this.isFindC = isFindC;
        this.isFindP = isFindP;
        this.isFindI = isFindI;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false,
                feedbackToUser.equals(ListCandidateCommand.MESSAGE_SUCCESS),
                feedbackToUser.equals(ListPositionCommand.MESSAGE_SUCCESS),
                feedbackToUser.equals(ListInterviewCommand.MESSAGE_SUCCESS),
                feedbackToUser.equals(FindCandidateCommand.MESSAGE_SUCCESS),
                feedbackToUser.equals(FindPositionCommand.MESSAGE_SUCCESS),
                false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isListC() {
        return isListC;
    }

    public boolean isListP() {
        return isListP;
    }

    public boolean isListI() {
        return isListI;
    }

    public boolean isFindC() {
        return isFindC;
    }

    public boolean isFindP() {
        return isFindP;
    }

    public boolean isFindI() {
        return isFindI;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isShowHelp == otherCommandResult.isShowHelp()
                && isExit == otherCommandResult.isExit()
                && isListC == otherCommandResult.isListC()
                && isListP == otherCommandResult.isListP()
                && isListI == otherCommandResult.isListI()
                && isFindC == otherCommandResult.isFindC()
                && isFindP == otherCommandResult.isFindP()
                && isFindI == otherCommandResult.isFindI();
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit, isListC, isListP, isListI, isFindC, isFindP, isFindI);
    }

}
