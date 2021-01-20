/*import bankClasses.Bank;
import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BankTest extends TestCase {
    Bank bank;
    List<String> transfersPool;


    @Override
    protected void setUp() throws Exception{
       final int ACCOUNT_AMOUNT = 10;
       final long TRANSFERS_AMOUNT = 1000;
        Bank bank = Bank.createNewBank(ACCOUNT_AMOUNT);

        transfersPool = new ArrayList<>();
        while (true) {
            String s1 = Integer.toString((int) ((ACCOUNT_AMOUNT * Math.random()) + 1));
            String s2 = Integer.toString((int) ((ACCOUNT_AMOUNT * Math.random()) + 1));
            if (!s1.equals(s2) && (!s1.equals(Integer.toString(ACCOUNT_AMOUNT + 1)) && !s2.equals(Integer.toString(ACCOUNT_AMOUNT + 1)))) {
                transfersPool.add(s1 + " " + s2);
            }
            if (transfersPool.size() == TRANSFERS_AMOUNT) {
                System.out.println("Transfer pool is created!");
                break;
            }
        }

        for (String rec : transfersPool) {
            String from = rec.split(" ", 2)[0];
            String to = rec.split(" ", 2)[1];
            bank.transfer(from, to, 1);
        }
        BigInteger bb = new BigInteger("0");
        for (String accNum : bank.getAccounts().keySet()){
            bb = bb.add(BigInteger.valueOf(bank.getAccounts().get(accNum).getMoney()));
        }
        bank.getThreadPool().shutdown();
    }

    public void testGetBalance() throws InterruptedException {
        long actual = bank.getBalance("3");
        long startBalance = bank.getAccounts().get("3").getSTART_MONEY();
        long shipted = bank.getAccounts().get("3").getShipped();
        long credited = bank.getAccounts().get("3").getCredited();
        long expected = startBalance + credited - shipted;
        assertEquals(actual,expected);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }



}

 */
