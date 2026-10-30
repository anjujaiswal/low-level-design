package day4;
//1 - enum OrderStatus: CREATED, PAID, SHIPPED, DELIVERED, CANCELLED
    //Method boolean canMoveTo(OrderStatus next) with these rules:
    //CREATED → PAID or CANCELLED
    //PAID → SHIPPED or CANCELLED
    //SHIPPED → DELIVERED
    //DELIVERED, CANCELLED → nothing (final states)
    //Hint: a switch on this works well here.
public enum OrderStatus {
    CREATED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    //notes:  enum constructors are always private


    public boolean canMoveTo(OrderStatus next) {
        return switch (this) {
            case CREATED -> next == PAID || next == CANCELLED;
            case PAID -> next == SHIPPED || next == CANCELLED;
            case SHIPPED -> next == DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
    }
}
