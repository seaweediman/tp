package seedu.address.testutil;

import seedu.address.model.interview.Interview;

/**
 * A utility class containing a list of {@code Interview} objects to be used in tests.
 */
public class TypicalInterviews {
    public static final Interview ASSISTANT_INTERVIEW = new InterviewBuilder()
            .withPosition(TypicalPositions.ADMIN_ASSISTANT)
            .build();

    public static final Interview BOOKKEEPER_INTERVIEW = new InterviewBuilder()
            .withPosition(TypicalPositions.BOOKKEEPER)
            .build();
    public static final Interview HR_MANAGER_INTERVIEW = new InterviewBuilder()
            .withPosition(TypicalPositions.HR_MANAGER)
            .build();
}
