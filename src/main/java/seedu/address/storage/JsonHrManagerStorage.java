package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * A class to access HrManager data stored as a json file on the hard disk.
 */
public class JsonHrManagerStorage implements HrManagerStorage {


    private static final Logger logger = LogsCenter.getLogger(JsonHrManagerStorage.class);

    private Path candidatesFilePath;
    private Path positionsFilePath;

    /**
     * Constructs a {@code JsonHrManagerStorage} with the given paths.
     */
    public JsonHrManagerStorage(Path candidatesFilePath, Path positionsFilePath) {
        this.candidatesFilePath = candidatesFilePath;
        this.positionsFilePath = positionsFilePath;
    }

    public Path getHrManagerCandidatesFilePath() {
        return candidatesFilePath;
    }

    public Path getHrManagerPositionsFilePath() {
        return positionsFilePath;
    }

    @Override
    public Optional<ReadOnlyHrManager> readHrManager() throws DataConversionException {
        return readHrManager(candidatesFilePath, positionsFilePath);
    }

    /**
     * Similar to {@link #readHrManager()}.
     *
     * @param candidatesFilePath location of the data. Cannot be null.
     * @param positionsFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHrManager> readHrManager(Path candidatesFilePath,
                                                     Path positionsFilePath) throws DataConversionException {
        requireNonNull(candidatesFilePath);
        requireNonNull(positionsFilePath);

        Optional<JsonSerializableHrManagerCandidates> candidates = JsonUtil.readJsonFile(
                candidatesFilePath, JsonSerializableHrManagerCandidates.class);

        Optional<JsonSerializableHrManagerPositions> positions = JsonUtil.readJsonFile(
                positionsFilePath, JsonSerializableHrManagerPositions.class);

        //merge data from both files
        try {
            HrManager merge = new HrManager();
            if (candidates.isPresent()) {
                for (Person person : candidates.get().toModelType().getPersonList()) {
                    merge.addPerson(person);
                }
            }
            if (positions.isPresent()) {
                for (Position position : positions.get().toModelType().getPositionList()) {
                    merge.addPosition(position);
                }
            }
            if (candidates.isEmpty() && positions.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(merge);
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found when merging data from " + candidatesFilePath + " and "
                    + positionsFilePath + ":" + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHrManager(ReadOnlyHrManager hrManager) throws IOException {
        saveHrManager(hrManager, candidatesFilePath, positionsFilePath);
    }

    /**
     * Similar to {@link #saveHrManager(ReadOnlyHrManager)}.
     *
     * @param hrManager cannot be null.
     * @param candidatesFilePath location of the data. Cannot be null.
     * @param positionsFilePath location of the data. Cannot be null.
     */
    public void saveHrManager(ReadOnlyHrManager hrManager, Path candidatesFilePath,
                              Path positionsFilePath) throws IOException {
        requireNonNull(hrManager);
        requireNonNull(candidatesFilePath);
        requireNonNull(positionsFilePath);

        //save candidates
        FileUtil.createIfMissing(candidatesFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableHrManagerCandidates(hrManager), candidatesFilePath);

        //save positions
        FileUtil.createIfMissing(positionsFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableHrManagerPositions(hrManager), positionsFilePath);
    }
}
