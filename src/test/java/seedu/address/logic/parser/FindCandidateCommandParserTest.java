package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.candidate.FindCandidateCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.candidate.FindCandidateCommand;
import seedu.address.model.person.FindCandidateCommandPredicate;
import seedu.address.model.person.Person;

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
                new FindCandidateCommand(new FindCandidateCommandPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob",
                expectedFindCandidateCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
                expectedFindCandidateCommand);


        //find by phone
        expectedFindCandidateCommand =
                new FindCandidateCommand(new FindCandidateCommandPredicate(new ArrayList<>(),
                        Arrays.asList(ALICE.getPhone().value), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>()));

        assertParseSuccess(parser, " " + PREFIX_PHONE + ALICE.getPhone().value,
                expectedFindCandidateCommand);

        //find by email
        expectedFindCandidateCommand =
                new FindCandidateCommand(new FindCandidateCommandPredicate(new ArrayList<>(),
                        new ArrayList<>(), Arrays.asList(BENSON.getEmail().value), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>()));

        assertParseSuccess(parser, " " + PREFIX_EMAIL + BENSON.getEmail().value,
                expectedFindCandidateCommand);

        //find by address
        ArrayList<String> address = new ArrayList<>();
        //because actual implementation of parsing method splits it, need to add individual words
        address.add("wall");
        address.add("street");
        expectedFindCandidateCommand =
                new FindCandidateCommand(new FindCandidateCommandPredicate(new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), address, new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>()));

        assertParseSuccess(parser, " "  + PREFIX_ADDRESS + "wall street",
                expectedFindCandidateCommand);

        //find by tag
        ArrayList<String> tags = new ArrayList<>();
        //because actual implementation of parsing method splits it, need to add individual words
        tags.add("owesMoney");
        tags.add("friends");
        expectedFindCandidateCommand =
                new FindCandidateCommand(new FindCandidateCommandPredicate(new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), tags,
                        new ArrayList<>(), new ArrayList<>()));

        assertParseSuccess(parser, " "  + PREFIX_TAG + BENSON.getTags().toString()
                .replaceAll("\\[", "").replaceAll(",", "")
                        .replaceAll("\\]", ""),
                expectedFindCandidateCommand);


        //find by status
        expectedFindCandidateCommand =
                new FindCandidateCommand(new FindCandidateCommandPredicate(new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        Arrays.asList(ELLE.getStatus().toString()), new ArrayList<>()));

        assertParseSuccess(parser, " "  + PREFIX_STATUS + ELLE.getStatus(),
                expectedFindCandidateCommand);

        //find by position
        ArrayList<String> positions = new ArrayList<>();
        //because actual implementation of parsing method splits it, need to add individual words
        positions.add("HR");
        positions.add("Manager");
        expectedFindCandidateCommand =
                new FindCandidateCommand(new FindCandidateCommandPredicate(new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), positions));

        assertParseSuccess(parser, " "  + PREFIX_POSITION + BENSON.getPositionsString(),
                expectedFindCandidateCommand);

    }

}
