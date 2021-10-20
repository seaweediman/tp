package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.candidate.FindCandidateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindCommandPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCandidateCommandParser implements Parser<FindCandidateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCandidateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_STATUS, PREFIX_POSITION);


        FindCommandPredicate findCommandPredicate = new FindCommandPredicate();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            findCommandPredicate.setNameKeywords(ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            findCommandPredicate.setPhoneKeywords(ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            findCommandPredicate.setEmailKeywords(ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            findCommandPredicate.setStatusKeywords(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_STATUS).orElse("")));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            findCommandPredicate.setAddressKeywords(ParserUtil.parseKeywords(
                    argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            findCommandPredicate.setTagKeywords(ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            findCommandPredicate.setPositionKeywords(ParserUtil.parseKeywords(
                    argMultimap.getValue(PREFIX_POSITION).get()));
        }

        if (!findCommandPredicate.isAnyField()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCandidateCommand.MESSAGE_USAGE));
        }

        return new FindCandidateCommand(findCommandPredicate);
    }

}
