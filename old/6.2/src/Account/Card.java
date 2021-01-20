package Account;

public class Card extends Account {
    public Card() {
        super();
    }

    @Override
    public void withdrawAccount(double withdrawAmount) {
        super.withdrawAccount(withdrawAmount + withdrawAmount * 0.01);
    }
}
