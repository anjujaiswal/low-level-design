package day4;
//class Order: id, customerId, amount, status
//A new order always starts as CREATED (not a constructor argument; why?)
//amount must be positive
//void moveTo(OrderStatus next) throws IllegalStateException if the transition isn’t allowed, with a message like "Cannot move from DELIVERED to PAID"
//Getters only. No setStatus() (remember Day 1).
public class Order {

    private final String id;
    private final String customerId;
    private final double amount;
    private OrderStatus status = OrderStatus.CREATED;

    public Order(String id, String customerId, double amount) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Order id is required");
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("Customer id is required");
        if(amount <= 0 ){
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.amount = amount;
        this.id = id;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void moveTo(OrderStatus next){
        if(!status.canMoveTo(next)){
            throw new IllegalStateException("Cannot move from " + status + " to "+ next);
        }
        this.status = next;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
