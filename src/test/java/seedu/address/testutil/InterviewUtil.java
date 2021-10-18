package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.interview.AddInterviewCommand;
import seedu.address.model.interview.Interview;

public class InterviewUtil {

    /**
     * Returns an add interview command string for adding the {@code interview}.
     */
    public static String getAddInterviewCommand(Interview interview) {
        return AddInterviewCommand.COMMAND_WORD + " " + getInterviewDetails(interview);
    }

    /**
     * Returns the part of command string for the given {@code interview}'s details.
     */
    public static String getInterviewDetails(Interview interview) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_POSITION + interview.getPosition().getTitle().fullTitle + " ");
        interview.getCandidates().stream().forEach(s -> sb.append(PREFIX_CANDIDATE_NAME + s.fullName + " "));
        //hard coded this for easy testing currently, will change in the next update
        sb.append(PREFIX_DATE + "30/10/2021" + " ");
        sb.append(PREFIX_TIME + interview.getStartTime().toString().replace(":", "") + " ");
        //hard coded this for easy testing currently, will change in the next update
        sb.append(PREFIX_DURATION + "60" + " ");
        sb.append(PREFIX_INTERVIEW_STATUS + interview.getStatus().toString() + " ");
        return sb.toString();
    }
}
