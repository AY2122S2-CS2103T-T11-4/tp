package peoplesoft.logic.commands.job;

import static java.util.Objects.requireNonNull;
import static peoplesoft.logic.parser.CliSyntax.PREFIX_DURATION;
import static peoplesoft.logic.parser.CliSyntax.PREFIX_NAME;

import peoplesoft.logic.commands.Command;
import peoplesoft.logic.commands.CommandResult;
import peoplesoft.logic.commands.exceptions.CommandException;
import peoplesoft.logic.parser.exceptions.ParseException;
import peoplesoft.model.Model;
import peoplesoft.model.job.Job;

/**
 * Adds a {@code Job} to {@code AddressBook}.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "job";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a job to the database. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DURATION + "DURATION ";

    public static final String MESSAGE_SUCCESS = "New job added: %s";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the database";

    private final Job toAdd;

    /**
     * Creates a {@code AddCommand} to add a {@code Job}.
     *
     * @param toAdd Job.
     * @throws ParseException Thrown if there is an error with parsing.
     */
    public AddCommand(Job toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasJob(toAdd.getJobId())) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.addJob(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
