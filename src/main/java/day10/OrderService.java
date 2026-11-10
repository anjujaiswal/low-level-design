package day10;

import java.time.Clock;
import java.time.LocalDateTime;

public class OrderService {
        private final OrderRepository repository;
        private final Notifier notifier;
        private final Clock clock;

        public OrderService(OrderRepository repository, Notifier notifier, Clock clock) {
            this.repository = repository;
            this.notifier = notifier;
            this.clock = clock;
        }

        public void placeOrder(Order order) {
            order.makeCreated(LocalDateTime.now(clock));
            repository.save(order);
            notifier.notify(order.getCustomerEmail(), "Order placed");
        }


    }


