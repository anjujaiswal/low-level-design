package day9;
//public interface PaymentMethod {
//    void pay(double amount);
//    void refund(double amount);
//    void saveCardForLater();
//    int getRewardPoints(double amount);
//}
//
//public class CreditCard implements PaymentMethod {
//    public void pay(double amount)           { System.out.println("Card paid " + amount); }
//    public void refund(double amount)        { System.out.println("Card refunded " + amount); }
//    public void saveCardForLater()           { System.out.println("Card saved"); }
//    public int getRewardPoints(double amount) { return (int) (amount / 100); }
//}
//
//public class Upi implements PaymentMethod {
//    public void pay(double amount)           { System.out.println("UPI paid " + amount); }
//    public void refund(double amount)        { System.out.println("UPI refunded " + amount); }
//    public void saveCardForLater()           { throw new UnsupportedOperationException("No card"); }
//    public int getRewardPoints(double amount) { return 0; }   // UPI earns no points
//}
//
//public class CashOnDelivery implements PaymentMethod {
//    public void pay(double amount)           { System.out.println("Cash collected " + amount); }
//    public void refund(double amount)        { throw new UnsupportedOperationException("Refund by bank transfer"); }
//    public void saveCardForLater()           { throw new UnsupportedOperationException("No card"); }
//    public int getRewardPoints(double amount) { return 0; }
//}
//List every LSP violation and every ISP violation, and say which sign gave it away. (Careful: is return 0 for reward points a violation, or a legitimate answer? Make the case.)
//Refactor into role interfaces (for example Payable, Refundable, CardSavable, RewardEarning). Each class implements only what it can truly do.
//Write a CheckoutService that takes payment through the narrowest
// interface it needs, and a RefundService whose method accepts only things that can be refunded.
//The proof: in main, try refundService.refund(new CashOnDelivery(), 500).
// It must not compile. Comment it out, and add a comment explaining why it doesn’t compile. That’s the whole point.
//Normal tests: pay with all three methods, refund the card and UPI, and print the card’s reward points.
public class Day9 {
    public static void main(String[] args) {
            CheckoutService checkoutService = new CheckoutService();
            RefundService refundService = new RefundService();
            // Required type:
            // Refundable give comile error cod does not implement refund
            // Doesn't compile: CashOnDelivery is not Refundable, so a COD refund can't be requested.
            // refundService.refund( new CashOnDelivery(), 500);

            // Normal tests: pay with all three methods, refund the card and UPI, and print the card’s reward points.
            checkoutService.checkout(new CreditCard(), 200);
            checkoutService.checkout(new Upi(), 300);
            checkoutService.checkout(new CashOnDelivery(), 400);

            refundService.refund(new CreditCard(), 100);
            refundService.refund(new Upi(), 50);

            System.out.println( new CreditCard().getRewardPoints(2550));
    }
}
