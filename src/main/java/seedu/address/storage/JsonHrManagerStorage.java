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
import seedu.address.model.ReadOnlyHrManager;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonHrManagerStorage implements HrManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHrManagerStorage.class);

    private Path filePath;

    public JsonHrManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHrManagerCandidatesFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHrManager> readHrManager() throws DataConversionException {
        return readHrManager(filePath);
    }

    /**
     * Similar to {@link #readHrManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHrManager> readHrManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHrManager> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableHrManager.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHrManager(ReadOnlyHrManager hrManager) throws IOException {
        saveHrManager(hrManager, filePath);
    }

    /**
     * Similar to {@link #saveHrManager(ReadOnlyHrManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHrManager(ReadOnlyHrManager hrManager, Path filePath) throws IOException {
        requireNonNull(hrManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHrManager(hrManager), filePath);
    }

}
