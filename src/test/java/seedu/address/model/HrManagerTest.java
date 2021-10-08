package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class HrManagerTest {

    private final HrManager hrManager = new HrManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hrManager.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHrManager_replacesData() {
        HrManager newData = getTypicalHrManager();
        hrManager.resetData(newData);
        assertEquals(newData, hrManager);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        HrManagerStub newData = new HrManagerStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> hrManager.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hrManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInHrManager_returnsFalse() {
        assertFalse(hrManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInHrManager_returnsTrue() {
        hrManager.addPerson(ALICE);
        assertTrue(hrManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInHrManager_returnsTrue() {
        hrManager.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(hrManager.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hrManager.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class HrManagerStub implements ReadOnlyHrManager {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        HrManagerStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
