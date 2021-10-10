package seedu.address.testutil;

import seedu.address.model.position.Position;

public class TypicalPositions {
    public static final Position ADMIN_ASSISTANT = new PositionBuilder().withTitle("Administrative Assistant")
            .withCandidates(TypicalPersons.ALICE, TypicalPersons.CARL)
            .build();

    public static final Position BOOKKEEPER = new PositionBuilder().withTitle("Bookkeeper")
            .withCandidates(TypicalPersons.BOB)
            .build();
}
