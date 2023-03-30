package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public final String value;

    /**
     * Creates a new Remark with specified text.
     * @param remark Specified remark to use
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    public String truncateRemark() {
        String ellipsis = "...";
        if (value == null) {
            return value;
        } else if (containsNewLine()) {
            // remark has more than one line
            String firstLine = value.split("\n", 2)[0];
            if (canTruncate(firstLine)) {
                // first line has more than 30 chars.
                return firstLine.substring(0, 30) + ellipsis;
            } else {
                // first lie is less than 30 chars.
                return firstLine + ellipsis;
            }
        } else if (canTruncate(value)) {
            // longer than 30 characters.
            return value.substring(0, 30) + ellipsis;
        } else {
            // short remark < 30 characters
            return value;
        }
    }

    public boolean canTruncate(String str) {
        assert(str != null);
        return str.length() > 30;
    }

    public boolean containsNewLine() {
        assert(value != null);
        return value.contains("\n");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
