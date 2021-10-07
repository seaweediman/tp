package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatusTest {

    @Test
    public void isCompleted() {
        assertFalse(Status.NONE.isCompleted());
        assertFalse(Status.APPLIED.isCompleted());
        assertFalse(Status.SCHEDULED.isCompleted());
        assertFalse(Status.INTERVIEWED.isCompleted());
        assertFalse(Status.ACCEPTED.isCompleted());
        assertTrue(Status.REJECTED.isCompleted());
        assertTrue(Status.WITHDRAWN.isCompleted());
    }
}