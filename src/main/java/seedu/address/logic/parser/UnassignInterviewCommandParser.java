package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.candidate.AddCandidateCommand;
import seedu.address.logic.interview.UnassignInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_INDEX;

public class UnassignInterviewCommandParser implements Parser<UnassignInterviewCommand>{


    public UnassignInterviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CANDIDATE_INDEX, PREFIX_INTERVIEW_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_CANDIDATE_INDEX, PREFIX_INTERVIEW_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnassignInterviewCommand.MESSAGE_USAGE));
        }

        Index interviewIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INTERVIEW_INDEX).get());
        Set<Index> candidateIndexes;
        String candidateIndexInput = argMultimap.getValue(PREFIX_CANDIDATE_INDEX).get();
        if (candidateIndexInput.equals("*")) {
            return new UnassignInterviewCommand(interviewIndex, true);
        } else {
            candidateIndexes = ParserUtil.parseCandidateIndex(candidateIndexInput);
            return new UnassignInterviewCommand(interviewIndex, candidateIndexes);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
