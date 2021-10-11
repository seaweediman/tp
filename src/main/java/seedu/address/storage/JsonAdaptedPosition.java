package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

public class JsonAdaptedPosition {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Position's %s field is missing!";

    private final String title;
    private final List<JsonAdaptedPerson> candidates = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPosition} with the given {@code positionName}.
     */
    @JsonCreator
    public JsonAdaptedPosition(@JsonProperty("title") String title,
                               @JsonProperty("candidates") List<JsonAdaptedPerson> candidates) {
        this.title = title;
        if (candidates != null) {
            this.candidates.addAll(candidates);
        }
    }

    /**
     * Converts a given {@code Position} into this class for Jackson use.
     */
    public JsonAdaptedPosition(Position source) {
        title = source.getTitle().fullTitle;
        candidates.addAll(source.getCandidates().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    public String getTitle() {
        return title;
    }

    /**
     * Converts this Jackson-friendly adapted position object into the model's {@code Position} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted position.
     */
    public Position toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final List<Person> candidates = new ArrayList<>();
        for (JsonAdaptedPerson person : this.candidates) {
            candidates.add(person.toModelType());
        }
        final Title modelTitle = new Title(title);
        final Set<Person> modelCandidates = new HashSet<>(candidates);
        return new Position(modelTitle, modelCandidates);
    }
}
