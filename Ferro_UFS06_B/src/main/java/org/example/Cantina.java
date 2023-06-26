package org.example;

import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Cantina {
    private static Cantina istance;
    private List<Wine> winesList = new ArrayList();

    private Cantina() {
        this.winesList.add(new Wine(13, "Oom Perignon Vintage Moet e Chandon 2008", 225.94, "white"));
        this.winesList.add(new Wine(14, "Cignoli Radikon Radikon 2009", 133.0, "red"));
        this.winesList.add(new Wine(124, "Ca dei frati", 20.5, "red"));
        this.winesList.add(new Wine(1, "DonPerignon", 100.0, "white"));

    }

    public static Cantina getInstance() {
        if (istance == null) {
            istance = new Cantina();
        }

        return istance;
    }

    public String toJson() {
        Gson gson = new Gson();
        String s = gson.toJson(this.winesList);
        return s;
    }


    public void sendAllWinesSortedName(PrintWriter out) {
        if (this.winesList.isEmpty()) {
            out.println("{\"wines\": []}");
        } else {
            List<Wine> sortedList = new ArrayList(this.winesList);
            sortedList.sort(Comparator.comparing(Wine::getName));
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(sortedList);
            out.println(jsonResponse);
        }

    }
    public void sendAllWinesSortedPrice(PrintWriter out) {
        if (this.winesList.isEmpty()) {
            out.println("{\"wines\": []}");
        } else {
            List<Wine> sortedList = new ArrayList(this.winesList);
            sortedList.sort(Comparator.comparing(Wine::getPrice).reversed());
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(sortedList);
            out.println(jsonResponse);
        }

    }
    public List<Wine> sendAllRedWines() {
        List<Wine> redWines = new ArrayList<>();

        for (Wine wine : winesList) {
            if (wine.getType().equalsIgnoreCase("red")) {
                redWines.add(wine);
            }
        }

        return redWines;
    }
    public List<Wine> sendAllWhiteWines() {
        List<Wine> redWines = new ArrayList<>();

        for (Wine wine : winesList) {
            if (wine.getType().equalsIgnoreCase("white")) {
                redWines.add(wine);
            }
        }

        return redWines;
    }


}


