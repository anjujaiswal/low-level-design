package day3;
//The real test: after everything works, add a PushChannel (constructed with a non-empty device ID). Add it to the list.
//NotificationService must not change at all. If you had to edit it, the design is wrong.
public class PushChannel implements NotificationChannel{
    private  final String deviceId;

    public PushChannel(String deviceId) {
        if(deviceId == null || deviceId.isBlank()){
            throw new IllegalArgumentException("Device Id is wrong");
        }
        this.deviceId = deviceId;
    }

    @Override
    public void send(String message) {
        System.out.println("Message to "+deviceId +" : "+ message);
    }
}
