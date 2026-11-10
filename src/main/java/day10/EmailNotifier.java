package day10;

public class EmailNotifier implements Notifier{
    @Override
    public void notify(String email, String message) { System.out.println("[SMTP] to " + email + ": " + message); }
}
