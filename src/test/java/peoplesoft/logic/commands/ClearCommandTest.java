package peoplesoft.logic.commands;

import static peoplesoft.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import peoplesoft.model.AddressBook;
import peoplesoft.model.Model;
import peoplesoft.model.ModelManager;
import peoplesoft.model.UserPrefs;
import peoplesoft.testutil.TypicalPersons;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
