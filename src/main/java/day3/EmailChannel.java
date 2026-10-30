package day3;
//EmailChannel: constructed with an email address.
//        Validation: it must contain @.
//send() prints "EMAIL to ravi@x.com: <message>".
public class EmailChannel implements NotificationChannel{
    private final String email;

    public EmailChannel(String email) {
        if(email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email id is wrong");

        }
        this.email = email;
    }

    @Override
    public void send(String message) {
        System.out.println("EMAIL to " + email + " : "+ message);
    }
}
