package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.interview.Interview;

/**
 * A UI component that displays information of a {@code Interview}.
 */
public class InterviewCard extends UiPart<Region> {

    private static final String FXML = "InterviewListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Interview interview;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    /**
     * status uses the FXML tags format
     */
    @FXML
    private FlowPane tags;
    @FXML
    private Label position;
    @FXML
    private Label startTime;
    @FXML
    private Label date;
    @FXML
    private Label duration;
    @FXML
    private FlowPane candidates;

    /**
     * Creates a {@code InterviewCard} with the given {@code Interview} and index to display.
     */
    public InterviewCard(Interview interview, int displayedIndex) {
        super(FXML);
        this.interview = interview;
        id.setText(displayedIndex + ". ");
        position.setText(interview.getPositionTitle().fullTitle);
        startTime.setText("Starting at: " + interview.getStartTime().toString());
        date.setText("Date: " + interview.getDate().toString());
        duration.setText("Duration: " + interview.getDuration().toMinutes() + " min");
        Interview.InterviewStatus status = interview.getStatus();
        tags.getChildren().add(new Label(status.name()));

        interview.getCandidates().stream()
                .sorted(Comparator.comparing(candidate -> candidate.getName().fullName))
                .forEach(candidate -> candidates.getChildren().add(new Label(candidate.getName().fullName + " | ")));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewCard)) {
            return false;
        }

        // state check
        InterviewCard card = (InterviewCard) other;
        return id.getText().equals(card.id.getText())
                && position.equals(card.position)
                && startTime.equals(card.startTime)
                && date.equals(card.date)
                && duration.equals(card.duration)
                && tags.equals(card.tags);
    }
}
