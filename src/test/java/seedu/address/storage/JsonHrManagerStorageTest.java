package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;

public class JsonHrManagerStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonHrManagerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHrManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHrManager(null, null));
    }

    private java.util.Optional<ReadOnlyHrManager> readHrManager(String candidatesFilePath,
                                                                String positionsFilePath) throws Exception {
        return new JsonHrManagerStorage(Paths.get(candidatesFilePath), Paths.get(positionsFilePath))
                .readHrManager(addToTestDataPathIfNotNull(candidatesFilePath),
                        addToTestDataPathIfNotNull(positionsFilePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHrManager("NonExistentCandidateFile.json",
                "NonExistentPositionFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readHrManager(
                "notJsonFormatHrManagerCandidates.json",
                "notJsonFormatHrManagerPositions.json"));
    }

    @Test
    public void readHrManager_invalidPersonHrManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrManager("invalidPersonHrManager.json",
                "invalidPositionHrManager.json"));
    }

    @Test
    public void readHrManager_invalidAndValidPersonHrManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHrManager(
                "invalidAndValidPersonHrManager.json",
                "invalidAndValidPositionHrManager.json"));
    }

    @Test
    public void readAndSaveHrManager_allInOrder_success() throws Exception {
        Path candidatesFilePath = testFolder.resolve("HrManagerCandidates.json");
        Path positionsFilePath = testFolder.resolve("HrManagerPositions.json");
        HrManager original = getTypicalHrManager();
        JsonHrManagerStorage jsonHrManagerStorage = new JsonHrManagerStorage(candidatesFilePath, positionsFilePath);

        // Save in new file and read back
        jsonHrManagerStorage.saveHrManager(original, candidatesFilePath, positionsFilePath);
        ReadOnlyHrManager readBack = jsonHrManagerStorage.readHrManager(candidatesFilePath, positionsFilePath).get();
        assertEquals(original, new HrManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonHrManagerStorage.saveHrManager(original, candidatesFilePath, positionsFilePath);
        readBack = jsonHrManagerStorage.readHrManager(candidatesFilePath, positionsFilePath).get();
        assertEquals(original, new HrManager(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonHrManagerStorage.saveHrManager(original); // file path not specified
        readBack = jsonHrManagerStorage.readHrManager().get(); // file path not specified
        assertEquals(original, new HrManager(readBack));

    }

    @Test
    public void saveHrManager_nullHrManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHrManager(null,
                "SomeCandidatesFile.json", "SomePositionsFile.json"));
    }

    /**
     * Saves {@code hrManager} at the specified {@code filePath}.
     */
    private void saveHrManager(ReadOnlyHrManager hrManager, String candidatesFilePath, String positionsFilePath) {
        try {
            new JsonHrManagerStorage(Paths.get(candidatesFilePath), Paths.get(positionsFilePath))
                    .saveHrManager(hrManager, addToTestDataPathIfNotNull(candidatesFilePath),
                            addToTestDataPathIfNotNull(positionsFilePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHrManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHrManager(new HrManager(),
                null, null));
    }
}
