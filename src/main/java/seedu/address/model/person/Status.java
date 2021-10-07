package seedu.address.model.person;

public enum Status {
    NONE (0, false),
    APPLIED (1, false),
    SCHEDULED (2, false),
    INTERVIEWED(3, false),
    ACCEPTED (4, false),
    REJECTED (5, true),
    WITHDRAWN (6, true);

    public static final String MESSAGE_CONSTRAINTS = "Enums can take the value:\n0.NONE\n1."
            + "APPLIED\n2.SCHEDULED"
            + "\n3.INTERVIEWED\n4.ACCEPTED\n5.REJECTED\n6.WITHDRAWN";

    private int index;
    private boolean isCompleted;




    private Status(int index, boolean isCompleted) {
        this.index = index;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the index of the Status.
     * @return index of status.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns if the status represent a completed state.
     * @return isCompleted.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        return this.index + "." + this.name();
    }
}
