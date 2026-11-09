package day9;

public class Upi implements Payable, Refundable, RewardEarning {
    // UPI earns no rewards; 0 is a valid answer, not an unsupported operation
    @Override
    public int getRewardPoints(double amount) {
        return 0;
    }

    @Override
    public void pay(double amount) {
        System.out.println("UPI paid " + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("UPI refunded " + amount);
    }
}
