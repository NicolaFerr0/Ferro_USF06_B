package org.example;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private static RequestHandler INSTANCE;

    private RequestHandler() {}

    public static RequestHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestHandler();
        }
        return INSTANCE;
    }



    public String getAction(String inCmd) {
        String output = "";

        switch (inCmd) {
            case "red":
                output = Cantina.getInstance().sendAllWines("red");
                break;

            case "white":
                output = Cantina.getInstance().sendAllWines("white");
                break;

            case "sorted_by_name":
                output = Cantina.getInstance().getSortedByName();
                break;

            case "sorted_by_price":
                output = Cantina.getInstance().getSortedByPrice();
                break;

            default:
                output = "<p>Command not found!</p>";
                break;
        }

        return output;
    }
}