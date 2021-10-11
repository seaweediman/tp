package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.position.Title;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPosition.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

class JsonAdaptedPositionTest {
    private static final String INVALID_TITLE = "Acc-Manager";
    private static final String VALID_TITLE = ADMIN_ASSISTANT.getTitle().fullTitle;

    private static final List<JsonAdaptedPerson> VALID_CANDIDATES = ADMIN_ASSISTANT.getCandidates()
            .stream().map(JsonAdaptedPerson::new).collect(Collectors.toList());

    @Test
    public void toModelType_validPositionDetails_returnsPosition() throws Exception {
        JsonAdaptedPosition position = new JsonAdaptedPosition(ADMIN_ASSISTANT);
        assertEquals(ADMIN_ASSISTANT, position.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedPosition position =
                new JsonAdaptedPosition(INVALID_TITLE, VALID_CANDIDATES);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, position::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedPosition position =
                new JsonAdaptedPosition(null, VALID_CANDIDATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, position::toModelType);
    }


    @Test
    public void toModelType_validDetails_returnsSameTitle() {
        JsonAdaptedPosition position =
                new JsonAdaptedPosition(VALID_TITLE, VALID_CANDIDATES);
        assertEquals(ADMIN_ASSISTANT.getTitle().fullTitle, position.getTitle());
    }
}
