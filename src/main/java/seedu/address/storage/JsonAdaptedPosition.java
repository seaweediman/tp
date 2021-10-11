package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HrManager;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

public class JsonAdaptedPosition {

    private final String positionName;

    /**
     * Constructs a {@code JsonAdaptedPosition} with the given {@code positionName}.
     */
    @JsonCreator
    public JsonAdaptedPosition(String positionName) {
        this.positionName = positionName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedPosition(Position source) {
        positionName = source.getTitle().fullTitle;
    }

    @JsonValue
    public String getPositionName() {
        return positionName;
    }

    /**
     * Converts this Jackson-friendly adapted position object into the model's {@code Position} object.
     *
     */
    public Position toModelType() {
        HrManager hrManager = new HrManager();
        Title title = new Title(positionName);
        Position toAdd = new Position(title);

        return hrManager.getPosition(toAdd); //Returns the reference to the position
    }
}
