package org.example;

public class RequestHandler {
    private static RequestHandler INSTANCE;

    private RequestHandler() {}

    public static RequestHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestHandler();
        }
        return INSTANCE;
    }

    String[] cmdList = {"more_expensive", "all", "all_sorted", "help"};

    public String getAction(String inCmd) {
        String output = "";

        switch (inCmd) {
            case "red":
                output = Cantina.getInstance().sendAllRedWines();
                break;

            case "white":
                output = Cantina.getInstance().sendAllWhiteWines();
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