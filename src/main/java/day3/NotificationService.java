package day3;

import java.util.ArrayList;
import java.util.List;


//NotificationService
//Its constructor takes a List<NotificationChannel>.
//        Method notifyAll(String message) sends the message through every channel.
//No instanceof, no type checks.
public class NotificationService {

    private final List<NotificationChannel> notificationChannels;

    public NotificationService(List<NotificationChannel> notificationChannels) {
        this.notificationChannels = new ArrayList<>(notificationChannels);
    }

    public void addChannel(NotificationChannel channel){
        if(channel == null){
            throw new IllegalArgumentException("Channel cannot be null");
        }
        notificationChannels.add(channel);
    }
    public void notifyAll(String message){
        for(NotificationChannel channel: notificationChannels){
            channel.send(message);
        }
    }
}
