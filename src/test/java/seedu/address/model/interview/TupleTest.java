package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.testutil.InterviewBuilder;

class TupleTest {

    @Test
    public void equals() {
        Interview interview = new InterviewBuilder().build();
        Index index = Index.fromZeroBased(1);

        Interview otherInterview = new InterviewBuilder().withDate(LocalDate.now()).build();
        Index otherIndex = Index.fromZeroBased(2);

        Tuple tuple = new Tuple(interview, index);
        Tuple otherTuple = new Tuple(otherInterview, otherIndex);
        Tuple tupleCopy = new Tuple(interview, index);

        //same Object returns true
        assertEquals(tuple, tuple);
        assertEquals(otherTuple, otherTuple);

        //same values returns true
        assertEquals(tuple, tupleCopy);

        //different value returns false
        assertNotEquals(tuple, otherTuple);

        //incompatible types returns false
        assertNotEquals(tuple, new Tuple(index, otherIndex));

        //different type returns false
        assertNotEquals(tuple, 6);
    }
}
