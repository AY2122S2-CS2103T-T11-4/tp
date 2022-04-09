package peoplesoft.ui.regions;

import java.util.List;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import peoplesoft.commons.core.LogsCenter;
import peoplesoft.model.person.Person;
import peoplesoft.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private List<ReadOnlyDoubleProperty> colWidths;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, List<ReadOnlyDoubleProperty> colWidths) {
        super(FXML);
        this.colWidths = colWidths;
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                // add a new divider before also!
                // <StackPane fx:id="divider" layoutX="10.0" layoutY="21.0"
                // prefHeight="2.0" style="-fx-background-color: #33344B;" />
                setGraphic(new PersonCard(person, getIndex() + 1, colWidths).getRoot());
            }
        }
    }

}
