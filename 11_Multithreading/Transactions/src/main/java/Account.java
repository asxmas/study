import lombok.Data;

@Data
public class Account implements Comparable<Account> {

    private long money;
    private Integer accNumber;
    private boolean status;

    public Account(int accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
        this.status = true;
    }

    public void replenishAccount (long sum) {

        money = money + sum;
    }
    public void withdrawAccount (long sum){
            money = money - sum;
    }

    public long getMoney (){
        return money;
    }


    @Override
    public int compareTo(Account o) {
        return this.getAccNumber().compareTo(o.getAccNumber());
    }
}
