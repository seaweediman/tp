package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.HrManager;
import seedu.address.model.position.Position;

public class JsonAdaptedPosition {
    private final String positionName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedPosition(String positionName) {
        this.positionName = positionName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedPosition(Position source) {
        positionName = source.getTitle().toString();
    }

    @JsonValue
    public String getPositionName() {
        return positionName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code position} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted position.
     */
    public Position toModelType() throws IllegalValueException {
        HrManager hrManager = new HrManager();
        Position toAdd = new Position(positionName);
        if (!hrManager.hasPosition(toAdd)) {
            throw new ParseException(String.format(Position.NOT_FOUND, positionName));
        }

        return hrManager.getPosition(positionName);
    }
}
