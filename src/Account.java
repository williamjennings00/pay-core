
import java.math.BigDecimal;

public class Account {

    private final String id;
    private final String ownerName;
    private BigDecimal balance;

    public Account(String id, String ownerName) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = new BigDecimal("0.00");
    }

    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        validateAmount(amount);

        if (amount.compareTo(balance) > 0) {
            throw new IllegalStateException("Insufficient funds");
        }

        balance = balance.subtract(amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
