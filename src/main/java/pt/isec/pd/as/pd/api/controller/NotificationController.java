package pt.isec.pd.as.pd.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import pt.isec.pd.as.pd.NotificationServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

@RestController
@RequestMapping("/notificacao")
public class NotificationController
{
   /* private final NotificationServer notificationServer;

    @Autowired
    public NotificationController(NotificationServer notificationServer) {
        this.notificationServer = notificationServer;
    }

    @PostMapping("/trigger")
    public void triggerNotification() {
        // Iterate over connected clients and send a notification
        for (Socket clientSocket : notificationServer.getClientSockets()) {
            try {
                // Send notification to the client
                OutputStream outputStream = clientSocket.getOutputStream();
                String notification = "Change occurred!";
                outputStream.write(notification.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                // Handle communication error with the client
                e.printStackTrace();
            }
        }
    }*/
}
