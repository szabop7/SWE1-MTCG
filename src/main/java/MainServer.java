import rest.Message;
import rest.MessageCollection;
import rest.MessageProcesser;
import rest.OutputHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    private static ServerSocket _listener = null;

    public static void main(String[] args) {
        System.out.println("start server...");
        try {
            ServerSocket listener = new ServerSocket(8000, 5);
            System.out.println("Waiting for Connection ...");
            while (true) {
                Socket socket = listener.accept();
                System.out.println("New Client Connected");
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                            OutputHandler output = new OutputHandler(writer);


                            Message message;

                            //Mit MessageProcess wird eine neue Message erstellt, wo alle Attribute ausgelesen und gespeichert werden
                            MessageProcesser m_process = new MessageProcesser(reader);
                            message = m_process.process();

                            output.response(message, MessageCollection.getInstance());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Client gone.");
                    }
                };
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
