package day3;

import java.util.ArrayList;
import java.util.List;

public class FakeChannel implements NotificationChannel {
    private final List<String> received = new ArrayList<>();

    @Override
    public void send(String message) {
        received.add(message);
    }

    public List<String> getReceived() {
        return received;
    }
}