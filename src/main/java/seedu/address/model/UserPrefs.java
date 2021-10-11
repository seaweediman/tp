package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path hrManagerCandidatesFilePath = Paths.get("data" , "candidates.json"); //TODO
    private Path hrManagerPositionsFilePath = Paths.get("data" , "positions.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setHrManagerCandidatesFilePath(newUserPrefs.getHrManagerCandidatesFilePath());
        setHrManagerPositionsFilePath(newUserPrefs.getHrManagerPositionsFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getHrManagerCandidatesFilePath() {
        return hrManagerCandidatesFilePath;
    }

    public Path getHrManagerPositionsFilePath() {
        return hrManagerPositionsFilePath;
    }

    public void setHrManagerCandidatesFilePath(Path hrManagerCandidatesFilePath) {
        requireNonNull(hrManagerCandidatesFilePath);
        this.hrManagerCandidatesFilePath = hrManagerCandidatesFilePath;
    }

    public void setHrManagerPositionsFilePath(Path hrManagerPositionsFilePath) {
        requireNonNull(hrManagerPositionsFilePath);
        this.hrManagerPositionsFilePath = hrManagerPositionsFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && hrManagerCandidatesFilePath.equals(o.hrManagerCandidatesFilePath)
                && hrManagerPositionsFilePath.equals(o.hrManagerPositionsFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, hrManagerCandidatesFilePath, hrManagerPositionsFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal candidates data file location : " + hrManagerCandidatesFilePath);
        sb.append("\nLocal positions data file location : " + hrManagerPositionsFilePath);
        return sb.toString();
    }

}
