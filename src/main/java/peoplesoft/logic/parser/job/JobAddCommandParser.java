package peoplesoft.logic.parser.job;

import static peoplesoft.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static peoplesoft.logic.parser.CliSyntax.PREFIX_DURATION;
import static peoplesoft.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.Duration;
import java.util.stream.Stream;

import peoplesoft.commons.core.JobIdFactory;
import peoplesoft.logic.commands.job.AddCommand;
import peoplesoft.logic.parser.ArgumentMultimap;
import peoplesoft.logic.parser.ArgumentTokenizer;
import peoplesoft.logic.parser.Parser;
import peoplesoft.logic.parser.ParserUtil;
import peoplesoft.logic.parser.Prefix;
import peoplesoft.logic.parser.exceptions.ParseException;
import peoplesoft.model.job.Job;
import peoplesoft.model.util.ID;

/**
 * Parses input parameters and returns a {@code Job}.
 */
public class JobAddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddCommand}
     * and returns a {@code AddCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DURATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DURATION)
                || !argMultimap.getPreamble().isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE));
        }
        String name = ParserUtil.parseString(argMultimap.getValue(PREFIX_NAME).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get());
        ID id = JobIdFactory.nextId();

        Job toAdd = new Job(id, name, duration);

        return new AddCommand(toAdd);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
