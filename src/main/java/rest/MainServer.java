package rest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MainServer {

    public static ServerSocket listener = null;

    public static void main(String[] args) {



        System.out.println("start server...");
        try {
            listener = new ServerSocket(8000, 5);
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

                            //Mit MessageProcess wird eine neue Message erstellt, wo alle Attribute ausgelesen und gespeichert werden
                            MessageProcesser m_process = new MessageProcesser(reader);
                            Message message = m_process.process();

                            //Die erzeugte Message und die Die Sammlung aller Nachricht-Texte muss übergeben werden
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
