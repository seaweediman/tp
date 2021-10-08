package seedu.address.testutil;

import seedu.address.model.HrManager;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building HrManager objects.
 * Example usage: <br>
 *     {@code HrManager hr = new HrManagerBuilder().withPerson("John", "Doe").build();}
 */
public class HrManagerBuilder {

    private HrManager hrManager;

    public HrManagerBuilder() {
        hrManager = new HrManager();
    }

    public HrManagerBuilder(HrManager hrManager) {
        this.hrManager = hrManager;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public HrManagerBuilder withPerson(Person person) {
        hrManager.addPerson(person);
        return this;
    }

    public HrManager build() {
        return hrManager;
    }
}
