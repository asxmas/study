package bankClasses;

import bankClasses.Account;
import enums.AccountStatus;
import enums.TransferType;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class Bank {
    private final Logger logger = LogManager.getRootLogger();


    private BigInteger bankBalance;
    private HashMap<String, Account> accounts;
    private ExecutorService threadPool = Executors.newFixedThreadPool(100);
    private final Random random = new Random();
    private  BigInteger startBankBalance;




    public boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }



    public void transfer (Vector<Transfer> transfers) {

        for(Transfer t : transfers) {
            threadPool.execute(t);
        }

    }

    //Выполнение перевода
    protected void makeATransfer(Account fromAccount, Account toAccount, long amount) throws InterruptedException {
        while (true) {


                if (fromAccount.getMoney() >= amount) {

                    if (fromAccount.getAccountStatus().equals(AccountStatus.NORMAL)) {
                        fromAccount.decrementMoney(amount);
                        setBankBalance(getBankBalance().subtract(BigInteger.valueOf(amount)));


                        toAccount.incrementMoney(amount);
                        setBankBalance(getBankBalance().add(BigInteger.valueOf(amount)));


                        System.out.println("The transfer was completed successfully.\n" +
                                amount + " was debited from account № " + fromAccount.getAccNumber() + "\n" +
                                amount + " is credited to account № " + toAccount.getAccNumber());

                        break;
                    } else if (fromAccount.getAccountStatus().equals(AccountStatus.FROZEN)) {
                        System.out.println("Check in progress, please wait.");


                    } else {
                        System.out.println("The translation cannot be performed.\n" +
                                "Account № " + fromAccount.getAccNumber() + " is blocked.");

                        break;
                    }
                } else {
                    System.err.println("There are not enough funds on the balance, the transfer has been canceled.\n" +
                            "Account balance = " + getBalance(fromAccount.getAccNumber()) + ".\n" +
                            "Transfer amount = " + amount + ".");

                    break;
                }


        }
    }


    // Заблокировать счета
    protected void blockTheAccounts (Account fromAccount, Account toAccount) {

            if (!toAccount.getAccountStatus().equals(AccountStatus.LOCKED)) {
                toAccount.setAccountStatus(AccountStatus.LOCKED);
                System.err.println("The transfer did not pass the fraud check.\n" +
                        "Account № " + toAccount.getAccNumber() + " blocked");
                //logger.info(LOCK, "The transfer did not pass the fraud check.\n" +
                //        "Account №  " + toAccount.getAccNumber() + " blocked");
            }


            if (!fromAccount.getAccountStatus().equals(AccountStatus.LOCKED)) {
                fromAccount.setAccountStatus(AccountStatus.LOCKED);
                System.err.println("The transfer did not pass the fraud check.\n" +
                        "Account №  " + fromAccount.getAccNumber() + " blocked");
                //logger.info(LOCK, "The transfer did not pass the fraud check.\n" +
                //        "Account №  " + fromAccount.getAccNumber() + " blocked");
            }

    }
    //Возвращает остаток на счете
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
            return account.getMoney();
        }

    public static Bank createNewBank(int accountsAmount){
        Bank bank = new Bank();
        HashMap<String, Account> accounts = new HashMap<>();
        BigInteger bankBalance = new BigInteger("0");
        for (long i = 0; i < accountsAmount; i++){

            String accNum = Long.toString(i + 1);
            BigInteger accBalance = new BigInteger(String.valueOf((long) (1000000 * (Math.random()))));
            Account account = new Account(accNum);
            account.setMoney(accBalance.longValue());
            bankBalance = bankBalance.add(accBalance);
            accounts.put(accNum, account);
        }
        bank.setAccounts(accounts);

        bank.setBankBalance(bankBalance);
        bank.setStartBankBalance(bankBalance);
        System.out.println(bankBalance);
        return bank;
    }
}
