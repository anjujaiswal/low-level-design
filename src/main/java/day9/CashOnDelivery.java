package day9;

public class CashOnDelivery implements Payable, RewardEarning {
    // cod earns no rewards; 0 is a valid answer, not an unsupported operation
    @Override
    public int getRewardPoints(double amount) {
        return 0;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Cash collected " + amount);
    }
}
