package com.example.ticketsanalyzer;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java com.example.ticketsanalyzer.Main <path to tickets.json>");
            System.exit(1);
        }

        String filePath = args[0];
        System.out.println("File path: " + filePath);  // Отладочный вывод
        try {
            TicketsAnalyzer analyzer = new TicketsAnalyzer(filePath);
            analyzer.analyze();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}