package day10;

public class MySqlOrderRepository implements OrderRepository{
    @Override
    public void save(Order order) {
        System.out.println("[MySQL] INSERT order " + order.getId());
    }
}
