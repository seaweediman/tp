package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.position.FindPositionCommand.COMMAND_WORD;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.position.FindPositionCommand;
import seedu.address.model.position.FindPositionCommandPredicate;

public class FindPositionCommandParserTest {

    private FindPositionCommandParser parser = new FindPositionCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPositionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPositionCommand expectedFindPositionCommand =
                new FindPositionCommand(new FindPositionCommandPredicate(Arrays.asList("Assistant", "HR")));
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_TITLE + "Assistant HR",
                expectedFindPositionCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_TITLE + " \n Assistant \n \t HR  \t",
                expectedFindPositionCommand);
    }

}
