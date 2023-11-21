package pt.isec.pd.as.pd;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

@Component
public class NotificationServer extends Thread
{
    private ServerSocket serverSocket;

    public NotificationServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public NotificationServer() {

    }

    public void notificationServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startServer();
    }

    public void run() {
        startServer();
    }

    public void startServer() {
        System.out.println("Start Server");
        try{

            while(!this.serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");

                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
                System.out.println("DEPOIS THREAD");
            }
        }catch (IOException e) {

        }
    }

    public void closeServerSocket() {
        System.out.println("AQUI");
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //    private final int port;
//    private final Set<Socket> clientSockets;
//
//    public Set<Socket> getClientSockets() {
//        return clientSockets;
//    }
//
//    public NotificationServer(int port) {
//        this.port = port;
//        this.clientSockets = new HashSet<>();
//    }
//
//    public void start() {
//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//            System.out.println("Notification server is listening on port " + port);
//
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("Client connected: " + clientSocket.getInetAddress());
//                clientSockets.add(clientSocket);
//
//                // Start a new thread to handle client communication
//                new Thread(() -> handleClient(clientSocket)).start();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Error starting notification server", e);
//        }
//    }
//
//    private void handleClient(Socket clientSocket) {
//        try {
//            OutputStream outputStream = clientSocket.getOutputStream();
//
//            while (true) {
//                // Send a notification to the client
//                String notification = "Change occurred!";
//                outputStream.write(notification.getBytes());
//                outputStream.flush();
//
//                // Wait for a while before sending the next notification
//                Thread.sleep(5000);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
