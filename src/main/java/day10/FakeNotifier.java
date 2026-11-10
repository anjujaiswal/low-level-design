package day10;

import java.util.ArrayList;
import java.util.List;

public class FakeNotifier implements Notifier {
    private final List<String> sent = new ArrayList<>();
    public List<String> getSent() { return sent; }

    @Override
    public void notify(String email, String message) {
        System.out.println("To email : " + email + " message : " + message);
        sent.add(email + ": " + message);
    }
}
