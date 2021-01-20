package bankClasses;

import enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;
import net.jcip.annotations.GuardedBy;
import org.junit.runner.notification.RunListener;

@Getter
@Setter
@RunListener.ThreadSafe
public class Account {
    @GuardedBy("this")
    private  long money;
    private String accNumber;
    private  AccountStatus accountStatus;



    public Account(String accNumber){
        this.accNumber = accNumber;

        setAccountStatus(AccountStatus.NORMAL);
    }
    protected void incrementMoney(long amount){

            money = money + amount;

    }
    protected void decrementMoney(long amount){

            money = money - amount;

    }



}
