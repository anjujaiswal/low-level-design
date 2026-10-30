package day3;

import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        // ---------- 1. Happy path: no try/catch ----------
        NotificationChannel email = new EmailChannel("aj@gmail.com");
        NotificationChannel sms = new SmsChannel("1234567891");

        NotificationService service = new NotificationService(List.of(email, sms));
        service.notifyAll("Your order has shipped");

        // Open/Closed test: new channel, service unchanged
        service.addChannel(new PushChannel("anju-device"));
        service.notifyAll("Your order has been delivered");

        // ---------- 2. Failure tests: one try/catch each ----------
        try {
            new SmsChannel("123456789");               // 9 digits
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            new EmailChannel("ajgmail.com");           // no @
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            new EmailChannel(null);                    // null email
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            new PushChannel("   ");                    // blank device ID
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            service.addChannel(null);                  // null channel
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ---------- 3. Unit test with a fake ----------
        FakeChannel fake = new FakeChannel();
        NotificationService testService = new NotificationService(List.of(fake));
        testService.notifyAll("hi");
        System.out.println("Fake received: " + fake.getReceived());
    }
}