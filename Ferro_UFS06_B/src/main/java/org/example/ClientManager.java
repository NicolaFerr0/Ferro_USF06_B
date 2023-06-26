package org.example;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ClientManager {
    private static ClientManager INSTANCE;
    private List<ClientHandler> clientList = new ArrayList();

    private ClientManager() {
    }

    public static ClientManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientManager();
        }

        return INSTANCE;
    }

    void add(ClientHandler clientHandler) {
        this.clientList.add(clientHandler);
    }

    void remove(ClientHandler clientHandler) {
        this.clientList.remove(clientHandler);
    }

    int nOfClients() {
        return this.clientList.size();
    }

    void broadcast(String s, ClientHandler client) {
        Iterator var3 = this.clientList.iterator();

        while(var3.hasNext()) {
            ClientHandler c = (ClientHandler)var3.next();
            if (!c.equals(client)) {
                c.write(s);
            }
        }

    }
}

