package seedu.address.model.tag;

import seedu.address.model.Attribute;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag extends Attribute<String> {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        super(tagName);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    protected String getMessageConstraints() {
        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public boolean isValid(String value) {
        return isValidTagName(value);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + value + ']';
    }
}
