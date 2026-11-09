package day9;

public class CheckoutService {
    public void checkout(Payable method, double amount){
        if(method == null){
            throw new IllegalArgumentException("Provide valid method");
        }
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be valid");
        }
        method.pay(amount);
    }
}
