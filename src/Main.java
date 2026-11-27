
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Account account = new Account("ACC-001", "John");

        System.out.println("Account ID: " + account.getId());
        System.out.println("Owner: " + account.getOwnerName());
        System.out.println("Balance: $" + account.getBalance());

        account.deposit(new BigDecimal("500.00"));
        System.out.println("\nDeposited: $500.00");
        System.out.println("Balance: $" + account.getBalance());

        account.withdraw(new BigDecimal("150.00"));
        System.out.println("\nWithdrawn: $150.00");
        System.out.println("Balance: $" + account.getBalance());

        try {
            account.withdraw(new BigDecimal("400.00"));
        } catch (IllegalStateException e) {
            System.out.println("\nError: " + e.getMessage());
        }

        try {
            account.deposit(new BigDecimal("-100.00"));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nFinal balance: $" + account.getBalance());
    }
}
