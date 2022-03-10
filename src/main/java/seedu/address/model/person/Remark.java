package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 */
public class Remark {
    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid name.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        boolean isSameObject = other == this;
        boolean isNotNull = other instanceof Remark;
        boolean hasSameValue = value.equals(((Remark) other).value); // state check

        return isSameObject || isNotNull && hasSameValue;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
