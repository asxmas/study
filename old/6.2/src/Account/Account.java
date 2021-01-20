package Account;

import java.time.LocalDate;

public class Account {
    private static int clientNumber;
    private String accountNumber = "4081781010332000000";
    private double amount;
    protected LocalDate replenishAccountDate;
    private LocalDate openAccountDate;

    public Account() {
        openAccountDate = LocalDate.now();
        replenishAccountDate = openAccountDate;
        clientNumber = clientNumber + 1;
        accountNumber = accountNumber + clientNumber;
        amount = 0;
    }

    public void replenishAccount(double replenishAmount) {
        replenishAccountDate = LocalDate.now();
        amount = amount + replenishAmount;
    }

    public void withdrawAccount(double withdrawAmount) {
        if (amount >= withdrawAmount) {
            amount = amount - withdrawAmount;
        } else System.out.println("У вас недостаточно средств на счете");
    }

    public void balance() {
        if (replenishAccountDate.equals(1970 - 01 - 01)) {
            replenishAccountDate = openAccountDate;
        }

        System.out.println("На счете " + accountNumber + ": " + amount + " RUR" +
                "\nСчет открыт: " + openAccountDate +
                "\nПоследнее пополнение счета: " + replenishAccountDate);
    }
}
