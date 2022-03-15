package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public abstract class Attribute<T> {

    public final T value;

    /**
     * Constructs an {@code Attribute}.
     *
     * @param value A valid value.
     */
    public Attribute(T value) {
        requireNonNull(value);
        checkArgument(isValid(value), getMessageConstraints());
        this.value = value;
    }

    /**
     * Returns message constraints for checking arguments.
     *
     * @return Message constraints.
     */
    protected abstract String getMessageConstraints();

    /**
     * Returns true if the value is valid, false otherwise.
     *
     * @param value Value to test.
     * @return Boolean result.
     */
    public abstract boolean isValid(T value);

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Attribute // instanceof handles nulls
            && value.equals(((Attribute) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
