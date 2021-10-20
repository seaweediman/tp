package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.candidate.FindCandidateCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.candidate.FindCandidateCommand;
import seedu.address.model.person.FindCommandPredicate;

public class FindCandidateCommandParserTest {

    private FindCandidateCommandParser parser = new FindCandidateCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCandidateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCandidateCommand expectedFindCandidateCommand =
                new FindCandidateCommand(new FindCommandPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_NAME + "Alice Bob",
                expectedFindCandidateCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
                expectedFindCandidateCommand);
    }

}
