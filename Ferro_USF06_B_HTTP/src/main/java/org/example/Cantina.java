package org.example;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cantina {
    private static Cantina INSTANCE;
    private List<Wine> winesList = new ArrayList();

    private Cantina() {
        this.winesList.add(new Wine(13, "Dom Perignon Vintage Moet e Chandon 2008", 225.94, "white"));
        this.winesList.add(new Wine(14, "Cignoli Radikon Radikon 2009", 133.0, "red"));
        this.winesList.add(new Wine(124, "Ca dei frati", 20.5, "red"));
        this.winesList.add(new Wine(1, "DonPerignon", 100.0, "white"));
    }

    public static Cantina getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Cantina();
        }
        return INSTANCE;
    }


    public String getSortedByName() {
        List<Wine> listCopy = new ArrayList<>();
        listCopy.addAll(winesList);

        listCopy.sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });

        String s = "<tr>";

        for (Wine wine:
                listCopy) {
            s += (
                    "<td>" + wine.getId() + "</td>" +
                            "<td>" + wine.getName() + "</td>" +
                            "<td>" + wine.getPrice() + "</td>" +
                            "<td>" + wine.getType() + "</td>" +
                            "</tr>\n"
            );
        }

        return s;
    }
    public String getSortedByPrice() {
        List<Wine> listCopy = new ArrayList<>();
        listCopy.addAll(winesList);

        listCopy.sort((o1, o2) -> {
            // Ordina i numeri in ordine decrescente
            return Double.compare(o2.getPrice(), o1.getPrice());
        });

        String s = "<tr>";

        for (Wine wine : listCopy) {
            s += (
                    "<td>" + wine.getId() + "</td>" +
                            "<td>" + wine.getName() + "</td>" +
                            "<td>" + wine.getPrice() + "</td>" +
                            "<td>" + wine.getType() + "</td>" +
                            "</tr>\n"
            );
        }

        return s;
    }
    public String sendAllRedWines() {
        String s = "<tr>";

        for (Wine wine:
                winesList)
        {
            if (wine.getType().equalsIgnoreCase("red"))
            {
                s += (
                        "<td>" + wine.getId() + "</td>" +
                                "<td>" + wine.getName() + "</td>" +
                                "<td>" + wine.getPrice() + "</td>" +
                                "<td>" + wine.getType() + "</td>" +
                                "</tr>\n"
                );
            }

        }

        return s;
    }
    public String sendAllWhiteWines() {
        String s = "<tr>";

        for (Wine wine:
                winesList)
        {
            if (wine.getType().equalsIgnoreCase("white"))
            {
                s += (
                        "<td>" + wine.getId() + "</td>" +
                                "<td>" + wine.getName() + "</td>" +
                                "<td>" + wine.getPrice() + "</td>" +
                                "<td>" + wine.getType() + "</td>" +
                                "</tr>\n"
                );
            }
        }

        return s;
    }



}