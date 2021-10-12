package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");
    public static Set<Position> SAMPLE_POSITIONS = new HashSet<>();


    public static Person[] getSamplePersons() {
        SAMPLE_POSITIONS.add(new Position(new Title("Admin Assistant")));
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                getTagSet("friends"), SAMPLE_POSITIONS),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                getTagSet("colleagues", "friends"), SAMPLE_POSITIONS),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                getTagSet("neighbours"), SAMPLE_POSITIONS),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                getTagSet("family"), SAMPLE_POSITIONS),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                getTagSet("classmates"), SAMPLE_POSITIONS),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                getTagSet("colleagues"), SAMPLE_POSITIONS)
        };
    }

    public static ReadOnlyHrManager getSampleHrManager() {
        HrManager sampleHm = new HrManager();
        for (Person samplePerson : getSamplePersons()) {
            sampleHm.addPerson(samplePerson);
        }
        return sampleHm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<Position> getPositionSet(String... strings) {
        return Arrays.stream(strings)
                .map(Title::new)
                .map(Position::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a person set containing the list of persons given.
     * @param persons the given list of persons.
     * @return a person set.
     */
    public static Set<Person> getPersonSet(Person... persons) {
        return new HashSet<Person>(Arrays.asList(persons));
    }
}
