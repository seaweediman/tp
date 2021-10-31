package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.CommandResult;
import seedu.address.logic.Logic;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private PositionListPanel positionListPanel;
    private InterviewListPanel interviewListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private Label personListLabel;

    @FXML
    private Label positionListLabel;

    @FXML
    private Label interviewListLabel;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane positionListPanelPlaceholder;

    @FXML
    private StackPane interviewListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        positionListPanel = new PositionListPanel(logic.getFilteredPositionList());
        positionListPanelPlaceholder.getChildren().add(positionListPanel.getRoot());

        interviewListPanel = new InterviewListPanel(logic.getFilteredInterviewList());
        interviewListPanelPlaceholder.getChildren().add(interviewListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerCandidatesFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        personListLabel.setText("Candidates");
        positionListLabel.setText("Positions");
        interviewListLabel.setText("Interviews");
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    private void handleListC(CommandResult commandResult) {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerCandidatesFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        personListLabel.setText("Candidates");
    }

    private void handleListP(CommandResult commandResult) {
        positionListPanel = new PositionListPanel(logic.getFilteredPositionList());
        positionListPanelPlaceholder.getChildren().add(positionListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerPositionsFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
        positionListLabel.setText("Positions");
    }

    private void handleListI(CommandResult commandResult) {
        interviewListPanel = new InterviewListPanel(logic.getFilteredInterviewList());
        interviewListPanelPlaceholder.getChildren().add(interviewListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerInterviewsFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
        interviewListLabel.setText("Interviews");
    }

    private void handleFindC(CommandResult commandResult) {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerCandidatesFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        personListLabel.setText("Candidates (filtered)");
    }

    private void handleFindP(CommandResult commandResult) {
        positionListPanel = new PositionListPanel(logic.getFilteredPositionList());
        positionListPanelPlaceholder.getChildren().add(positionListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerPositionsFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
        positionListLabel.setText("Positions (filtered)");
    }

    private void handleFindI(CommandResult commandResult) {
        interviewListPanel = new InterviewListPanel(logic.getFilteredInterviewList());
        interviewListPanelPlaceholder.getChildren().add(interviewListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerInterviewsFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
        interviewListLabel.setText("Interviews (filtered)");
    }

    private void handleEditC(CommandResult commandResult) { //Update person and interview lists
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        interviewListPanel = new InterviewListPanel(logic.getFilteredInterviewList());
        interviewListPanelPlaceholder.getChildren().add(interviewListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerInterviewsFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    private void handleEditP(CommandResult commandResult) { //Update person and position lists
        positionListPanel = new PositionListPanel(logic.getFilteredPositionList());
        positionListPanelPlaceholder.getChildren().add(positionListPanel.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerInterviewsFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    private void handleEditI(CommandResult commandResult) { //Update person and interview lists
        interviewListPanel = new InterviewListPanel(logic.getFilteredInterviewList());
        interviewListPanelPlaceholder.getChildren().add(interviewListPanel.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getHrManagerInterviewsFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            } else if (commandResult.isExit()) {
                handleExit();
            } else if (commandResult.isListC()) {
                handleListC(commandResult);
            } else if (commandResult.isListP()) {
                handleListP(commandResult);
            } else if (commandResult.isListI()) {
                handleListI(commandResult);
            } else if (commandResult.isFindC()) {
                handleFindC(commandResult);
            } else if (commandResult.isFindP()) {
                handleFindP(commandResult);
            } else if (commandResult.isFindI()) {
                handleFindI(commandResult);
            } else if (commandResult.isEditC()) {
                handleEditC(commandResult);
            } else if (commandResult.isEditP()) {
                handleEditP(commandResult);
            } else if (commandResult.isEditI()) {
                handleEditI(commandResult);
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
