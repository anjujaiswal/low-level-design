package day10;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
//    make Order a small class with id, customerEmail, amount and createdAt
    private final String id;
    private final String email;
    private final long amount;
    private LocalDateTime createdAt;

    public Order(String id, String email, long amount) {
        this.id = id;
        this.email = email;
        this.amount = amount;

    }
    public void makeCreated(LocalDateTime time){
//        System.out.println(this.createdAt);
        if(this.createdAt != null){
            throw new IllegalStateException("Already set");
        }
        this.createdAt = time;
    }

    public String getCustomerEmail(){
        return this.email;
    }

    public String getId(){
        return this.id;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
