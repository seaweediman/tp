package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindCommandPredicate implements Predicate<Person> {

    private List<String> nameKeywords = new ArrayList<String>();
    private List<String> phoneKeywords = new ArrayList<String>();
    private List<String> emailKeywords = new ArrayList<String>();
    private List<String> addressKeywords = new ArrayList<String>();
    private List<String> tagKeywords = new ArrayList<String>();
    private List<String> statusKeywords = new ArrayList<String>();

    public FindCommandPredicate() {
    }

    /**
     * Alternative Constructor with all fields.
     * @param nameKeywords
     * @param phoneKeywords
     * @param emailKeywords
     * @param addressKeywords
     * @param tagKeywords
     * @param statusKeywords
     */
    public FindCommandPredicate(List<String> nameKeywords, List<String> phoneKeywords, List<String> emailKeywords,
                                List<String> addressKeywords, List<String> tagKeywords, List<String> statusKeywords) {
        this.nameKeywords = nameKeywords;
        this.phoneKeywords = phoneKeywords;
        this.emailKeywords = emailKeywords;
        this.addressKeywords = addressKeywords;
        this.tagKeywords = tagKeywords;
        this.statusKeywords = statusKeywords;
    }

    /**
     * Alternative Constructor with only keyword field.
     * @param nameKeywords
     */
    public FindCommandPredicate(List<String> nameKeywords) {
        this.nameKeywords = nameKeywords;
    }

    @Override
    public boolean test(Person person) {
        boolean nameCheck = nameKeywords.isEmpty() || nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));

        boolean phoneCheck = phoneKeywords.isEmpty() || phoneKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));

        boolean emailCheck = emailKeywords.isEmpty() || emailKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));

        boolean addressCheck = addressKeywords.isEmpty() || addressKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));

        boolean tagCheck = tagKeywords.isEmpty() || tagKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getTags().toString(), keyword));

        boolean statusCheck = statusKeywords.isEmpty() || statusKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getStatus().toString(), keyword));

        return nameCheck && phoneCheck && emailCheck && addressCheck && tagCheck && statusCheck;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommandPredicate // instanceof handles nulls
                && nameKeywords.equals(((FindCommandPredicate) other).nameKeywords) // state check
                && phoneKeywords.equals(((FindCommandPredicate) other).phoneKeywords)
                && addressKeywords.equals(((FindCommandPredicate) other).addressKeywords)
                && tagKeywords.equals(((FindCommandPredicate) other).tagKeywords)
                && statusKeywords.equals(((FindCommandPredicate) other).statusKeywords));
    }

    public void setNameKeywords(List<String> name) {
        this.nameKeywords = name;
    }

    public void setPhoneKeywords(List<String> phone) {
        this.phoneKeywords = phone;
    }

    public void setEmailKeywords(List<String> email) {
        this.emailKeywords = email;
    }

    public void setAddressKeywords(List<String> address) {
        this.addressKeywords = address;
    }

    public void setStatusKeywords(List<String> status) {
        this.statusKeywords = status;
    }

    public void setTagKeywords(List<String> tags) {
        this.tagKeywords = tags;
    }

    /**
     * If any field is provided.
     * @return if any field is provided.
     */
    public boolean isAnyField() {
        return !Stream.of(nameKeywords, phoneKeywords, emailKeywords, addressKeywords, statusKeywords,
                tagKeywords)
                .allMatch(List::isEmpty);
    }

}
