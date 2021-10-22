package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FindCandidateCommandPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCandidateCommandPredicate firstPredicate = new FindCandidateCommandPredicate(firstPredicateKeywordList);
        FindCandidateCommandPredicate secondPredicate = new FindCandidateCommandPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCandidateCommandPredicate firstPredicateCopy = new FindCandidateCommandPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new FindCandidateCommandPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new FindCandidateCommandPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new FindCandidateCommandPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCandidateCommandPredicate predicate = new FindCandidateCommandPredicate();

        // Non-matching keyword
        predicate = new FindCandidateCommandPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new FindCandidateCommandPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}