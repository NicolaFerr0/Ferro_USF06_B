package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket = null;
    private InetAddress address;
    private int port;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.address = clientSocket.getInetAddress();
        this.port = clientSocket.getPort();
        PrintStream var10000 = System.out;
        String var10001 = String.valueOf(this.address);
        var10000.println("Connected: " + var10001 + "with port: " + this.port);
    }

    Boolean readLoop(BufferedReader in, PrintWriter out) {
        String s = "";

        PrintStream var10000;
        String var10001;
        try {
            while((s = in.readLine()) != null) {
                System.out.println(s);
                ClientManager.getInstance().broadcast(s, this);
                if (s.equals("sorted_by_name")) {
                    Cantina.getInstance().sendAllWinesSortedName(out);
                } else if (s.equals("more_expensive")) {
                    //Cantina.getInstance().sendMoreExpensiveCars(out);
                } else {
                    out.println("Errore: comando errato. Riprova.");
                }
            }

            var10000 = System.out;
            var10001 = String.valueOf(this.address);
            var10000.println("Disconnected: " + var10001 + "with port: " + this.port);
            ClientManager.getInstance().remove(this);
            System.out.println("Now we have " + ClientManager.getInstance().nOfClients() + " connected client");
            return true;
        } catch (IOException var5) {
            var10000 = System.out;
            var10001 = String.valueOf(this.address);
            var10000.println("Forcing disconnection for: " + var10001 + "with port: " + this.port);
            return false;
        }
    }

    void handle() {
        this.out = null;

        try {
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.readLoop(in, this.out);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void run() {
        this.handle();
    }

    void write(String s) {
        this.out.println(s);
        this.out.flush();
    }
}

