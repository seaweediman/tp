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
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Position toModelType() throws IllegalValueException {
        HrManager hrManager = new HrManager();
        Title title = new Title(positionName);
        Position toAdd = new Position(title);

        if (!(hrManager.hasPosition(toAdd))) { //If Position does not exist
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }

        return hrManager.getPosition(toAdd); //Returns the reference to the position
    }
}
