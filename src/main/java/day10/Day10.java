package day10;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//Refactor the violating OrderService above (make Order a small class with id, customerEmail, amount and createdAt):
//
//List every low-level dependency hidden in it. There are three. Which is the easiest to miss?
// ans-  lld dependenx y db save , email sender, localtime date  , easy to miss  datetime
//Define OrderRepository and Notifier, written from the business side’s point of view.
//        Inject them, plus a Clock, through the constructor. No new and no now() inside OrderService.
//Write test implementations: InMemoryOrderRepository (a Map) and FakeNotifier (records the messages).
//Write two “real-looking” implementations that just print (MySqlOrderRepository, EmailNotifier),
//    since we’re not connecting to real systems.
//In main: run the same OrderService code once with the fakes and a fixed clock (check the saved order’s createdAt equals
//   the fixed time, and that the fake received one message), then once with the “real” ones. OrderService
//   must not change between the two.
public class Day10 {
    public static void main(String[] args) {
        OrderRepository repository = new InMemoryOrderRepository();
        FakeNotifier fakeNotifier = new FakeNotifier();
        Order order = new Order("123","jais@gmail.com",12424);
        Clock fixed = Clock.fixed(Instant.parse("2026-11-10T10:00:00Z"), ZoneOffset.UTC);
        OrderService orderService = new OrderService(repository, fakeNotifier, fixed);
        orderService.placeOrder(order);
        System.out.println("createdAt correct? " +
                order.getCreatedAt().equals(LocalDateTime.of(2026, 11, 10, 10, 0)));   // expect true
        System.out.println("messages sent: " + fakeNotifier.getSent().size());       // expect 1
        OrderRepository repository1 = new MySqlOrderRepository();
        Notifier emailNotifier = new EmailNotifier();
        OrderService orderService1 = new OrderService(repository1, emailNotifier, fixed);
        orderService1.placeOrder(new Order("142", "isha@gmail.com",124));
    }
}
