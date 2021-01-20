import Account.Account;
import Account.Deposit;
import Account.Card;

public class Main {
    public static void main(String[] args) {
        Account vasiliy = new Account();
        vasiliy.replenishAccount(500000.0);
        vasiliy.withdrawAccount(150000.0);
        vasiliy.balance();

        Account alex = new Account();
        alex.replenishAccount(20000.0);
        alex.balance();

        Deposit michael = new Deposit();
        michael.replenishAccount(70000.0);
        michael.withdrawAccount(30000.0);
        michael.balance();

        Deposit tatiana = new Deposit();
        tatiana.replenishAccount(50000.0);
        tatiana.withdrawAccount(30000);
        tatiana.balance();

        Card evgeniy = new Card();
        evgeniy.replenishAccount(100000.0);
        evgeniy.withdrawAccount(80000.0);
        evgeniy.balance();
    }
}
