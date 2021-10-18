package seedu.address.logic.interview;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.interview.Interview.InterviewStatus;
import seedu.address.model.person.Name;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

public class CommandTestUtil {

    public static final Position VALID_POSITION_ADMIN = new Position(new Title("Admin"));
    public static final Position VALID_POSITION_MANAGER = new Position(new Title("Manager"));
    public static final HashSet<Name> VALID_CANDIDATES_SET = new HashSet<>(List.of(ALICE.getName(), BOB.getName()));
    public static final LocalDate VALID_LOCAL_DATE = LocalDate.of(2021, 10, 18);
    public static final LocalTime VALID_START_TIME = LocalTime.NOON; //12:00
    public static final Duration VALID_DURATION = Duration.ofMinutes(180);
    public static final InterviewStatus VALID_STATUS_PENDING = InterviewStatus.PENDING;
    public static final InterviewStatus VALID_STATUS_COMPLETED = InterviewStatus.COMPLETED;
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_NAME_ALICE = ALICE.getName().fullName;
    public static final String VALID_NAME_BOB = BOB.getName().fullName;
    public static final String VALID_DATE = "18/10/2021";
    public static final String VALID_DATE_OTHER_DATE = "21/10/2021";
    public static final String VALID_TIME = "1200";
    public static final String VALID_TIME_OTHER_TIME = "1300";
    public static final String VALID_DURATION_TIME = "180";
    public static final String VALID_DURATION_TIME_OTHER_DURATION = "190";

    public static final String VALID_POSITION_ADMIN_DESC = " " + PREFIX_POSITION + VALID_POSITION_ADMIN.getTitle();
    public static final String VALID_POSITION_MANAGER_DESC = " " + PREFIX_POSITION + VALID_POSITION_MANAGER.getTitle();
    public static final String VALID_CANDIDATE_DESC_ALICE = " " + PREFIX_CANDIDATE_NAME + VALID_NAME_ALICE;
    public static final String VALID_CANDIDATE_DESC_BOB = " " + PREFIX_CANDIDATE_NAME + VALID_NAME_BOB;
    public static final String VALID_DATE_DESC = " " + PREFIX_DATE + VALID_DATE;
    public static final String VALID_DATE_OTHER_DATE_DESC = " " + PREFIX_DATE + VALID_DATE_OTHER_DATE;
    public static final String VALID_TIME_DESC = " " + PREFIX_TIME + VALID_TIME;
    public static final String VALID_TIME_OTHER_TIME_DESC = " " + PREFIX_TIME + VALID_TIME_OTHER_TIME;
    public static final String VALID_DURATION_DESC = " " + PREFIX_DURATION + VALID_DURATION_TIME;
    public static final String VALID_DURATION_TIME_OTHER_DURATION_DESC = " " + PREFIX_DURATION
            + VALID_DURATION_TIME_OTHER_DURATION;
    public static final String VALID_STATUS_PENDING_DESC = " " + PREFIX_INTERVIEW_STATUS + VALID_STATUS_PENDING;
    public static final String VALID_STATUS_COMPLETED_DESC = " " + PREFIX_INTERVIEW_STATUS
            + VALID_STATUS_COMPLETED;

    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "38/10/2021";
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "2500";
    public static final String INVALID_DURATION_TIME = " " + PREFIX_DURATION + "-180";

    public static final String INVALID_STATUS_DESC = " " + PREFIX_INTERVIEW_STATUS + "blah";
}
