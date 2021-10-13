package seedu.address.testutil;

import seedu.address.model.position.Position;

public class TypicalPositions {
    public static final Position ADMIN_ASSISTANT = new PositionBuilder().withTitle("Administrative Assistant")
            .build();

    public static final Position BOOKKEEPER = new PositionBuilder().withTitle("Bookkeeper")
            .build();

    public static final Position HR_MANAGER = new PositionBuilder().withTitle("HR Manager")
            .build();
}
