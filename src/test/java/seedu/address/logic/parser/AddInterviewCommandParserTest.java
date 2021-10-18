package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_DURATION_TIME;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.interview.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.interview.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.interview.CommandTestUtil.VALID_CANDIDATES_SET;
import static seedu.address.logic.interview.CommandTestUtil.VALID_CANDIDATE_DESC_ALICE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_CANDIDATE_DESC_BOB;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE_OTHER_DATE_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_TIME_OTHER_DURATION_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_LOCAL_DATE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_MANAGER_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_START_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_COMPLETED_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_PENDING;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_PENDING_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME_DESC;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME_OTHER_TIME_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.interview.AddInterviewCommand;
import seedu.address.model.interview.Interview;
import seedu.address.testutil.InterviewBuilder;


class AddInterviewCommandParserTest {
    private AddInterviewCommandParser parser = new AddInterviewCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Interview expectedInterview = new InterviewBuilder().withPosition(VALID_POSITION_ADMIN)
                .withCandidates(VALID_CANDIDATES_SET).withDate(VALID_LOCAL_DATE).withStartTime(VALID_START_TIME)
                .withDuration(VALID_DURATION).withStatus(VALID_STATUS_PENDING).build();

        // whitespace only preamble and multiple persons - all accepted
        assertParseSuccess(parser, VALID_POSITION_ADMIN_DESC
                        + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                + VALID_TIME_DESC + VALID_DURATION_DESC + VALID_STATUS_PENDING_DESC,
                new AddInterviewCommand(expectedInterview));

        // multiple positions - last position accepted
        assertParseSuccess(parser, VALID_POSITION_MANAGER_DESC + VALID_POSITION_ADMIN_DESC
                + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB
                + VALID_DATE_DESC + VALID_TIME_DESC + VALID_DURATION_DESC, new AddInterviewCommand(expectedInterview));

        // multiple dates - last date accepted
        assertParseSuccess(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + VALID_DATE_OTHER_DATE_DESC + VALID_DATE_DESC
                + VALID_TIME_DESC + VALID_DURATION_DESC, new AddInterviewCommand(expectedInterview));

        // multiple times - last time accepted
        assertParseSuccess(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC + VALID_TIME_OTHER_TIME_DESC + VALID_TIME_DESC
                + VALID_DURATION_DESC, new AddInterviewCommand(expectedInterview));

        // multiple durations - last duration accepted
        assertParseSuccess(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                + VALID_TIME_DESC + VALID_DURATION_TIME_OTHER_DURATION_DESC + VALID_DURATION_DESC,
                new AddInterviewCommand(expectedInterview));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                + VALID_TIME_DESC + VALID_DURATION_TIME_OTHER_DURATION_DESC + VALID_DURATION_DESC
                + VALID_STATUS_COMPLETED_DESC + VALID_STATUS_PENDING_DESC,
                new AddInterviewCommand(expectedInterview));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        //no interview status
        Interview expectedInterview = new InterviewBuilder().withPosition(VALID_POSITION_ADMIN)
                .withCandidates(VALID_CANDIDATES_SET).withDate(VALID_LOCAL_DATE).withStartTime(VALID_START_TIME)
                .withDuration(VALID_DURATION).build();
        assertParseSuccess(parser,
                VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB
                        + VALID_DATE_DESC + VALID_TIME_DESC + VALID_DURATION_DESC,
                new AddInterviewCommand(expectedInterview));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE);

        // missing position prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN.getTitle().fullTitle
                        + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                        + VALID_TIME_DESC + VALID_DURATION_DESC + VALID_STATUS_COMPLETED_DESC,
                expectedMessage);

        // missing candidate names prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + " " + VALID_NAME_ALICE + " " + VALID_NAME_BOB
                        + VALID_DATE_DESC + VALID_TIME_DESC + VALID_DURATION_DESC,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB
                        + " " + VALID_DATE + VALID_TIME_DESC + VALID_DURATION_DESC,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB
                        + VALID_DATE_DESC + " " + VALID_TIME + VALID_DURATION_DESC,
                expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE + VALID_CANDIDATE_DESC_BOB
                        + VALID_DATE_DESC + VALID_TIME_DESC + " " + VALID_DURATION_TIME,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_POSITION_ADMIN.getTitle() + " " + VALID_NAME_ALICE + " "
                        + VALID_NAME_BOB + " " + VALID_DATE + " " + VALID_TIME + " "
                        + VALID_DURATION_TIME,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid date
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + INVALID_DATE_DESC
                + VALID_TIME_DESC + VALID_DURATION_DESC, Interview.MESSAGE_DATE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                + INVALID_TIME_DESC + VALID_DURATION_DESC, Interview.MESSAGE_TIME_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                + VALID_TIME_DESC + INVALID_DURATION_TIME, Interview.MESSAGE_DURATION_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                        + VALID_CANDIDATE_DESC_BOB + VALID_DATE_DESC
                        + VALID_TIME_DESC + VALID_DURATION_DESC + INVALID_STATUS_DESC,
                Interview.InterviewStatus.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                + VALID_CANDIDATE_DESC_BOB + INVALID_DATE_DESC
                + VALID_TIME_DESC + VALID_DURATION_DESC + INVALID_STATUS_DESC, Interview.MESSAGE_DATE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_POSITION_ADMIN_DESC + VALID_CANDIDATE_DESC_ALICE
                        + VALID_CANDIDATE_DESC_BOB
                        + VALID_DATE_DESC + VALID_TIME_DESC + VALID_DURATION_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE));
    }

}
