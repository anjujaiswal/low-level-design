package day10;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

public class InMemoryOrderRepository implements OrderRepository{
    private final HashMap<String, Order> map = new HashMap<>();
    @Override
    public void save(Order order) {
        map.put(order.getId(), order);
    }

    public void printOrders(){
        for(HashMap.Entry<String, Order> mapElement : map.entrySet()){
            System.out.println(mapElement.getValue());
        }
    }



}
