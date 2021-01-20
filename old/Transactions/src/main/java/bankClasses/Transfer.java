package bankClasses;

import enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.jcip.annotations.ThreadSafe;

@Setter
@Getter
@ThreadSafe
public class Transfer implements Runnable {
    private Account fromAccount;
    private Account toAccount;
    private long amount;
    private Bank bank;
    private String transferNumber;


    public Transfer(Account fromAcc, Account toAcc, Bank bank, long amount, String transferNumber) {
        this.fromAccount = fromAcc;
        this.toAccount = toAcc;
        this.amount = amount;
        this.bank = bank;
        this.transferNumber = transferNumber;
    }

    @SneakyThrows
    @Override
    public void run() {

        System.out.println(Thread.currentThread().

                getName() +" complite" + "\n" + transferNumber);

    }

}

