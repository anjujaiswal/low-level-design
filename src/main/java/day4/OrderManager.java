package day4;

import java.util.*;

//class OrderManager: stores orders in a Map<String, Order>
//void addOrder(Order order): reject null and duplicate IDs
//Order findById(String id): throw IllegalArgumentException if not found (don’t return null)
//List<Order> getOrdersByStatus(OrderStatus status)
//Set<String> getUniqueCustomers()
//Map<OrderStatus, Integer> countByStatus(): use an EnumMap
public class OrderManager {
    private final Map<String ,Order> ordersById = new HashMap<>();
    public void addOrder(Order order) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        if (ordersById.containsKey(order.getId())) {
            throw new IllegalArgumentException("Order already exists: " + order.getId());
        }
        ordersById.put(order.getId(), order);
    }

    public Order findById(String id){
        if(!ordersById.containsKey(id)){
            throw new IllegalArgumentException("Id not found " + id);
        }
        return ordersById.get(id);
    }

    public List<Order> getOrdersByStatus(OrderStatus status){
        List<Order> orders = new ArrayList<>();
        for(Order order : ordersById.values()){
            if(order.getStatus() == status){
                orders.add(order);
            }
        }
        return orders;
    }

    public Set<String> getUniqueCustomers(){
        Set<String> customers = new HashSet<>();
        for(Order order : ordersById.values()){
            customers.add(order.getCustomerId());
        }
        return customers;
    }

    public Map<OrderStatus, Integer> countByStatus(){
        Map<OrderStatus , Integer> statusCount = new EnumMap<>(OrderStatus.class);
        for(Order order : ordersById.values()){
            statusCount.put(order.getStatus(), statusCount.getOrDefault(order.getStatus(),0)+1);
        }
        return statusCount;
    }
}
