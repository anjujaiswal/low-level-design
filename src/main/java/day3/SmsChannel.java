package day3;
//SmsChannel: constructed with a phone number.
//        Validation: exactly 10 digits. (Hint: phone.matches("\\d{10}"))
//send() prints "SMS to 9876543210: <message>".
public class SmsChannel implements NotificationChannel {
    private  final String phoneNumber;

    public SmsChannel(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}") ){
            throw new IllegalArgumentException("Phone number must be exactly 10 digits");
        }
        this.phoneNumber = phoneNumber;

    }

    @Override
    public void send(String message) {
        System.out.println("SMS to " + phoneNumber + " : " + message);
    }
}
