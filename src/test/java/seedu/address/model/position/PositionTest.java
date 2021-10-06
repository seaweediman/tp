package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Position(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidPositionName = "";
        assertThrows(IllegalArgumentException.class, () -> new Position(invalidPositionName));
    }

    @Test
    public void isValidPositionName() {
        // null position name
        assertThrows(NullPointerException.class, () -> Position.isValidPositionName(null));
    }

    @Test
    public void variousPositionNames() {
        //invalid position names
        assertFalse(Position.isValidPositionName(("Administrative & Finance Assistant")));
        assertFalse(Position.isValidPositionName(("Administrative Assistant #2")));
        assertFalse(Position.isValidPositionName(("Administrative Assistant *")));
        assertFalse(Position.isValidPositionName(("Administrative Assistant ^^")));
        assertFalse(Position.isValidPositionName(("Acc-Manager")));
        assertFalse(Position.isValidPositionName(("")));
        assertFalse(Position.isValidPositionName((" ")));
        assertFalse(Position.isValidPositionName(("Administrative Manager (temp)")));
        assertFalse(Position.isValidPositionName(("'Fun!' project manager")));
        assertFalse(Position.isValidPositionName(("data + admin clerk")));

        //valid position names
        assertTrue(Position.isValidPositionName(("Administrative Assistant")));
        assertTrue(Position.isValidPositionName(("Receptionist")));
        assertTrue(Position.isValidPositionName(("Office Manager")));
        assertTrue(Position.isValidPositionName(("Auditing Clerk")));
        assertTrue(Position.isValidPositionName(("Bookkeeper")));
        assertTrue(Position.isValidPositionName(("Account Executive Officer Assistant")));
        assertTrue(Position.isValidPositionName(("Branch Manager")));
        assertTrue(Position.isValidPositionName(("Business Manager")));
        assertTrue(Position.isValidPositionName(("Quality Control Coordinator")));
        assertTrue(Position.isValidPositionName(("Administrative Manager")));
        assertTrue(Position.isValidPositionName(("Chief Executive Officer")));
        assertTrue(Position.isValidPositionName(("Business Analyst")));
        assertTrue(Position.isValidPositionName(("Risk Manager")));
        assertTrue(Position.isValidPositionName(("Human Resources")));
        assertTrue(Position.isValidPositionName(("Office Assistant")));
        assertTrue(Position.isValidPositionName(("Secretary")));
        assertTrue(Position.isValidPositionName(("Office Clerk")));
        assertTrue(Position.isValidPositionName(("File Clerk")));
        assertTrue(Position.isValidPositionName(("Account Collector")));
        assertTrue(Position.isValidPositionName(("Administrative Specialist")));
        assertTrue(Position.isValidPositionName(("Executive Assistant")));
        assertTrue(Position.isValidPositionName(("Program Administrator")));
        assertTrue(Position.isValidPositionName(("Program Manager")));
        assertTrue(Position.isValidPositionName(("Administrative Analyst")));
        assertTrue(Position.isValidPositionName(("Data Entry")));
    }
}
