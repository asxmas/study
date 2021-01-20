import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Test extends TestCase {
    Bank bank;
    Random random;
    int numOfAccs = 10;
    int sum;
    ArrayList<Account> accounts = new ArrayList<>();


    @Override
    public void setUp() {
        numOfAccs = 10;
        sum = 50020;
        bank = new Bank();
        random = new Random();
        for (int i = 0; i < numOfAccs; i++) {
            int accNumber = i;
            Account account = new Account(accNumber, 1000000);
            accounts.add(account);
            bank.addAccount(accNumber, account);
        }
    }

    @org.junit.Test
    public void testBank() throws InterruptedException {

        long sumOfMoney = bank.getAccounts().values().stream().mapToLong(Account::getMoney).sum();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int accFrom = random.nextInt(bank.getNumOfAccs());
                    int accTo = random.nextInt(bank.getNumOfAccs());
                    int transferSum = random.nextInt(sum);
                    try {
                        bank.transfer(accFrom, accTo, transferSum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

        long result = bank.getAccounts().values().stream().mapToLong(Account::getMoney).sum();
        System.out.println(sumOfMoney + " " + result);
        System.out.println(bank.getCount());
        System.out.println(accounts.stream().map(account -> account.getAccNumber() + "-" + account.isStatus()).collect(Collectors.toList()));
    }


}



