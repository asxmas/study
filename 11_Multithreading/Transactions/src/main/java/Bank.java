import lombok.Data;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Bank {
    private HashMap<Integer, Account> accounts;
    private final Random random = new Random();
    private AtomicInteger count;
    private long moneyInBank;

    public Bank() {
        count = new AtomicInteger(0);
        accounts = new HashMap<>();
        moneyInBank = 0;

    }

    public synchronized boolean isFraud()
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(int fromAccountNum, int toAccountNum, long amount) throws InterruptedException {

        Account fromAcc = accounts.get(fromAccountNum);
        Account toAcc = accounts.get(toAccountNum);
        if(fromAccountNum == toAccountNum){
            return;
        }

        Account dst;
        Account src;
        if(fromAcc.compareTo(toAcc) > 0){
            dst = fromAcc;
            src = toAcc;
        } else {
            dst = toAcc;
            src = fromAcc;
        }
        synchronized (dst) {
            synchronized (src) {
                if (fromAcc.getMoney() >= amount) {
                    if (!fromAcc.isStatus() || !toAcc.isStatus()) {
                        System.out.println("Один из счетов заблокирован");
                        return;
                    } else if (amount <= 50000) {
                        tranferMoney(fromAcc, toAcc, amount);
                        return;
                    } else if (isFraud()) {
                            blockAccounts(fromAcc, toAcc);
                            return;
                        } else {
                            tranferMoney(fromAcc, toAcc, amount);
                            return;
                        }
                    }
                else {
                    System.out.println("Недостаточно средств");
                    return;
                }

            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */

    public int getNumOfAccs() {
        return accounts.size();
    }

    public void addAccount(int accNumber, Account account){

        accounts.put(accNumber, account);
        moneyInBank = moneyInBank + account.getMoney();
    }

    public void tranferMoney (Account fromAcc, Account toAcc, long amount){
        fromAcc.withdrawAccount(amount);
        toAcc.replenishAccount(amount);
        System.out.println("Перевод денег со счета " + fromAcc.getAccNumber() + " на счет " + toAcc.getAccNumber() + " на сумму " + amount + " прошло успешно.");
        count.incrementAndGet();
    }

    public void blockAccounts (Account fromAcc, Account toAcc){
        fromAcc.setStatus(false);
        toAcc.setStatus(false);
        System.out.println("Счета " + fromAcc.getAccNumber() + " и " + toAcc.getAccNumber() + " заблокированы");
    }

}
