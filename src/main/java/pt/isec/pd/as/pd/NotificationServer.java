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
        try{

            while(!this.serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");

                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException e) {

        }
    }

    public void closeServerSocket() {
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
