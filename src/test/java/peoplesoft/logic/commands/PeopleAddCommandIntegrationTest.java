package peoplesoft.logic.commands;

import static peoplesoft.logic.commands.CommandTestUtil.assertCommandFailure;
import static peoplesoft.logic.commands.CommandTestUtil.assertCommandSuccess;
import static peoplesoft.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import peoplesoft.model.Model;
import peoplesoft.model.ModelManager;
import peoplesoft.model.UserPrefs;
import peoplesoft.model.person.Person;
import peoplesoft.model.util.ID;
import peoplesoft.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code PeopleAddCommand}.
 */
public class PeopleAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().withId(new ID(95712384)).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new PeopleAddCommand(validPerson), model,
                String.format(PeopleAddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new PeopleAddCommand(personInList), model, PeopleAddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
