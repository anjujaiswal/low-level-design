package day4;
//Build a mini order-management system:
//
//Main, using the Day 3 structure (happy path → failure tests → summary):
//Create 4 orders: 2 from the same customer, and move them to different statuses
//Print orders by status, the unique customers, and the count per status
//        Failure tests, one try each: DELIVERED → PAID, a duplicate ID, findById("XYZ"), a negative amount
public class Day4 {
    public static void main(String[] args) {
        //happy path
        Order order1 = new Order("1","anju", 10);
        Order order2 = new Order("2","anju", 20);
        Order order3 = new Order("3","isha", 20);
        Order order4 = new Order("4","isha", 30);

        OrderManager manager = new OrderManager();
        manager.addOrder(order1);
        manager.addOrder(order2);
        manager.addOrder(order3);
        manager.addOrder(order4);

       order1.moveTo(OrderStatus.PAID);
       order2.moveTo(OrderStatus.CANCELLED);
       order3.moveTo(OrderStatus.CANCELLED);
       order4.moveTo(OrderStatus.PAID);

       System.out.println(manager.getOrdersByStatus(OrderStatus.PAID));

       System.out.println(manager.getUniqueCustomers());

       System.out.println(manager.countByStatus());

       System.out.println(manager.findById("1"));

       //failure
        // DELIVERED → PAID, a duplicate ID, findById("XYZ"), a negative amount
        order1.moveTo(OrderStatus.SHIPPED);
        order1.moveTo(OrderStatus.DELIVERED);
        try{
            order1.moveTo(OrderStatus.PAID);
        }catch (IllegalStateException e){
            System.out.println("Error : " + e.getMessage());
        }

        try{
            manager.addOrder(order1);
        }catch (IllegalArgumentException e){
            System.out.println("Error : " + e.getMessage());
        }

        try{
            new Order("5","viv", -1);
        }catch (IllegalArgumentException e){
            System.out.println("Error : " + e.getMessage());
        }

        try{
            manager.findById("xyz");
        }catch (IllegalArgumentException e){
            System.out.println("Error : " + e.getMessage());
        }


    }
}
