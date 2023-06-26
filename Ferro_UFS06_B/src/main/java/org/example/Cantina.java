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
        this.winesList.add(new Wine(13, "Dom Perignon Vintage Moet & Chandon 2008", 225.94, "white"));
        this.winesList.add(new Wine(14, "Pignoli Radikon Radikon 2009", 133.0, "red"));
        this.winesList.add(new Wine(124, "Pinot Nero Elena Walch Elena Walch 2018", 43.0, "red"));
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
            out.println("{\"cars\": []}");
        } else {
            List<Wine> sortedList = new ArrayList(this.winesList);
            sortedList.sort(Comparator.comparing(Wine::getName));
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(sortedList);
            out.println(jsonResponse);
        }

    }
}


