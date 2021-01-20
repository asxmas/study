package Account;

import java.time.LocalDate;

public class Deposit extends Account {
    public Deposit() {
        super();
    }

    @Override
    public void withdrawAccount(double withdrawAmount) {
        if (replenishAccountDate.isBefore(LocalDate.now().minusDays(30))) {
            super.withdrawAccount(withdrawAmount);
        } else
            System.out.println("Вы не можете снять с Вашего депозита деньги, так как прошло менее месяца с последнего пополнения");
    }
}

