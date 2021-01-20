
import bankClasses.Account;
import bankClasses.Bank;
import bankClasses.Transfer;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        File file = new File("logs/lock.log");
        if (file.exists()){
            file.delete();
        }
        int accountAmount = 10000;
        Bank bank = Bank.createNewBank(accountAmount);
        Vector<Transfer> transfers = new Vector<>();
        int i = 0;
        while (true) {
            String s1 = Integer.toString((int) ((accountAmount * Math.random()) + 1));
            String s2 = Integer.toString((int) ((accountAmount * Math.random()) + 1));
            if (!s1.equals(s2) && (!s1.equals(Integer.toString(accountAmount + 1)) && !s2.equals(Integer.toString(accountAmount+1)))) {
                i++;
                String transferNumber = Integer.toString(i);
                Account accountFrom = bank.getAccounts().get(s1);
                Account accountTo = bank.getAccounts().get(s2);
                Transfer transfer = new Transfer(accountFrom, accountTo, bank, 1, transferNumber);
                transfers.add(transfer);
            }
            if (transfers.size() == 10000000) {
                System.out.println("Transfer pool is created!");
                break;
            }
        }


        bank.transfer(transfers);




        long bankBalance = 0;
        for (String key : bank.getAccounts().keySet()){
            long money = bank.getAccounts().get(key).getMoney();
            bankBalance = bankBalance + money;
        }

        System.err.println(bank.getBankBalance());
        System.err.println(bank.getStartBankBalance());
        System.err.println(bankBalance);


    }
}
//bank.getAccounts().get(from).getMoney() / 10