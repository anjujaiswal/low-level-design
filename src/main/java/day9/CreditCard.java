package day9;

public class CreditCard implements Refundable,Payable, RewardEarning, CardSavable{
    @Override
    public void saveCardForLater() {
        System.out.println("Card saved");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Card paid " + amount);

    }

    @Override
    public void refund(double amount) {
        System.out.println("Card refunded " + amount);
    }

    @Override
    public int getRewardPoints(double amount) {
        return (int) (amount / 100);
    }
}
