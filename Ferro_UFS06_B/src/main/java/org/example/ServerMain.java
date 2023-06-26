package org.example;

/**
 * Hello world!
 *
 */
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    static final int portNumber = 1234;
    static final int maxRetries = 10;

    public ServerMain() {
    }

    static Wine[] pro(String jsonStr) {
        Gson gson = new Gson();
        Wine[] wines = (Wine[])gson.fromJson(jsonStr, Wine[].class);
        return wines;
    }

    static Boolean readLoop(BufferedReader in, PrintWriter out) {
        String s = "";

        try {
            while((s = in.readLine()) != null) {
                Wine[] x = pro(s);

                for(int i = 0; i < x.length; ++i) {
                    System.out.println(x[i].toString());
                }

                out.println(s.toUpperCase());
                out.flush();
            }

            return true;
        } catch (IOException var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Server started!");
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        Socket clientSocket = null;

        while(true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException var5) {
                var5.printStackTrace();
            }

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            ClientManager.getInstance().add(clientHandler);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
}

