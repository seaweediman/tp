package seedu.address.logic.interview;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

public class CommandTestUtil {

    public static final HashSet<Person> VALID_CANDIDATES_SET = new HashSet<Person>(List.of(ALICE, BOB));
    public static final LocalTime VALID_START_TIME = LocalTime.NOON;
    public static final Duration VALID_DURATION = Duration.ofHours(3);
    public static final Interview.InterviewStatus VALID_STATUS_PENDING = Interview.InterviewStatus.PENDING;
    public static final Interview.InterviewStatus VALID_STATUS_COMPLETED = Interview.InterviewStatus.COMPLETED;

}
