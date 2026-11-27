
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("ACC-001", "John");
    }

    @Test
    void accountShouldStartWithZeroBalance() {
        assertEquals(new BigDecimal("0.00"), account.getBalance());
    }

    @Test
    void accountShouldRetainIdAndOwner() {
        assertEquals("ACC-001", account.getId());
        assertEquals("John", account.getOwnerName());
    }

    @Test
    void depositShouldIncreaseBalance() {
        account.deposit(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        account.deposit(new BigDecimal("100.00"));
        account.withdraw(new BigDecimal("30.00"));
        assertEquals(new BigDecimal("70.00"), account.getBalance());
    }

    @Test
    void shouldAllowWithdrawalOfEntireBalance() {
        account.deposit(new BigDecimal("100.00"));
        account.withdraw(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("0.00"), account.getBalance());
    }

    @Test
    void shouldCalculateMoneyExactly() {
        account.deposit(new BigDecimal("0.10"));
        account.deposit(new BigDecimal("0.20"));
        assertEquals(new BigDecimal("0.30"), account.getBalance());
    }

    @Test
    void shouldRejectNegativeDeposit() {
        assertInvalidDeposit(new BigDecimal("-10.00"));
    }

    @Test
    void shouldRejectZeroDeposit() {
        assertInvalidDeposit(BigDecimal.ZERO);
    }

    @Test
    void shouldRejectNullDeposit() {
        assertInvalidDeposit(null);
    }

    @Test
    void shouldRejectNegativeWithdrawal() {
        assertInvalidWithdrawal(new BigDecimal("-10.00"));
    }

    @Test
    void shouldRejectZeroWithdrawal() {
        assertInvalidWithdrawal(BigDecimal.ZERO);
    }

    @Test
    void shouldRejectNullWithdrawal() {
        assertInvalidWithdrawal(null);
    }

    @Test
    void shouldRejectOverdraft() {
        account.deposit(new BigDecimal("100.00"));

        assertThrows(IllegalStateException.class,
                () -> account.withdraw(new BigDecimal("150.00")));

        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }

    private void assertInvalidDeposit(BigDecimal amount) {
        account.deposit(new BigDecimal("100.00"));

        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(amount));

        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }

    private void assertInvalidWithdrawal(BigDecimal amount) {
        account.deposit(new BigDecimal("100.00"));

        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(amount));

        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }
}
