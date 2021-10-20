package seedu.address.model.interview;

public class Tuple<S, T> {
    private S first;
    private T second;

    public Tuple(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tuple)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Tuple<S, T> otherTuple = (Tuple<S, T>) other;
        return otherTuple.getFirst().equals(getFirst())
                && otherTuple.getSecond().equals(getSecond());
    }
}
