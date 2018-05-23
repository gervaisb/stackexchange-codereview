package q188306;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountIdTest {

    private final AccountId target = new AccountId();
    private final String value = target.value;

    @Test(expected = IllegalArgumentException.class)
    public void valueOf_should_reject_invalid_format() {
        AccountId.valueOf("123456789");
    }


    @Test(expected = IllegalArgumentException.class)
    public void valueOf_should_produce_AccountId() {
        AccountId other = AccountId.valueOf(value);
        assertThat(other.value).isEqualTo(value);
    }

    @Test
    public void two_AccountId_with_same_values_are_equals() {
        AccountId other = new AccountId(value);
        assertThat(other)
                .isEqualTo(target);
        EqualsVerifier.forClass(AccountId.class)
                .verify();
    }

    @Test
    public void should_be_unique() {
        long size = IntStream.range(0, 900).parallel()
                .mapToObj(i -> new AccountId())
                .distinct()
                .count();
        assertThat(size).isEqualTo(900);
    }
}