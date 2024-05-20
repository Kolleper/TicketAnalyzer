package com.example.ticketsanalyzer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TicketsAnalyzer {
    private final String filePath;
    private List<Ticket> vvoToTlvTickets;

    public TicketsAnalyzer(String filePath) {
        this.filePath = filePath;
    }

    public void analyze() throws IOException, ParseException {
        // Чтение и парсинг файла tickets.json
        Gson gson = new Gson();
        Type ticketDataType = new TypeToken<TicketData>() {}.getType();
        TicketData ticketData = gson.fromJson(new FileReader(filePath), ticketDataType);

        // Фильтрация билетов для маршрута Владивосток - Тель-Авив
        vvoToTlvTickets = ticketData.getTickets().stream()
                .filter(ticket -> "VVO".equals(ticket.getOrigin()) && "TLV".equals(ticket.getDestination()))
                .collect(Collectors.toList());

        // Вычисление минимального времени полета для каждого авиаперевозчика
        calculateMinFlightTimes();

        // Вычисление средней цены и медианы
        calculatePriceDifference();
    }

    private void calculateMinFlightTimes() throws ParseException {
        Map<String, Long> minFlightTimes = new HashMap<>();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yy HH:mm");

        for (Ticket ticket : vvoToTlvTickets) {
            String carrier = ticket.getCarrier();
            long departureTime = dateTimeFormat.parse(ticket.getDepartureDate() + " " + ticket.getDepartureTime()).getTime();
            long arrivalTime = dateTimeFormat.parse(ticket.getArrivalDate() + " " + ticket.getArrivalTime()).getTime();
            long flightTime = arrivalTime - departureTime;
            minFlightTimes.put(carrier, Math.min(minFlightTimes.getOrDefault(carrier, Long.MAX_VALUE), flightTime));
        }

        // Вывод минимального времени полета для каждого авиаперевозчика
        System.out.println("Минимальное время полета между Владивостоком и Тель-Авивом для каждого авиаперевозчика:");
        for (Map.Entry<String, Long> entry : minFlightTimes.entrySet()) {
            long hours = entry.getValue() / (1000 * 60 * 60);
            long minutes = (entry.getValue() / (1000 * 60)) % 60;
            System.out.printf("Авиаперевозчик: %s, Время: %d ч %d мин\n", entry.getKey(), hours, minutes);
        }
    }

    private void calculatePriceDifference() {
        List<Integer> prices = vvoToTlvTickets.stream().map(Ticket::getPrice).sorted().toList();
        double averagePrice = prices.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double medianPrice;
        int size = prices.size();
        if (size % 2 == 0) {
            medianPrice = (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            medianPrice = prices.get(size / 2);
        }

        // Вывод разницы между средней ценой и медианой
        System.out.printf("Разница между средней ценой и медианой: %.2f\n", Math.abs(averagePrice - medianPrice));
    }
}