package q188306;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public final class AccountId {

    public static AccountId valueOf(String string) {
        if ( !string.matches("A-[0-9]{7}-[0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}") ) {
            throw new IllegalArgumentException("Invalid AccountId format, `"+string+"`.");
        } else {
            return new AccountId(string);
        }
    }

    public final String value;

    public AccountId() {
        this(String.format("A-%1$tY%1$tj-%1$tH%1$tM-%2$s",
                ZonedDateTime.now(),
                UUID.randomUUID().toString().substring(15, 32)));
    }

    AccountId(final String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountId accountId = (AccountId) o;
        return Objects.equals(value, accountId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "AccountId{" + value + '}';
    }
}
