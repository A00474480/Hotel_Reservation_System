package com.example.hotel_reservation_system.data;

import com.example.hotel_reservation_system.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class MockDataProvider {

        public static List<Hotel> getMockHotels() {
        List<Hotel> hotels = new ArrayList<>();

        hotels.add(new Hotel("Hotel Sunshine", "$150", "Available"));
        hotels.add(new Hotel("Ocean View", "$200", "Not Available"));
        hotels.add(new Hotel("Mountain Retreat", "$100", "Available"));
        hotels.add(new Hotel("Urban Escape", "$130", "Available"));
        hotels.add(new Hotel("Riverside Inn", "$80", "Not Available"));
        hotels.add(new Hotel("Seaside Stay", "$220", "Available"));
        hotels.add(new Hotel("Historic Cozy", "$110", "Not Available"));
        hotels.add(new Hotel("City Central", "$190", "Available"));
        hotels.add(new Hotel("Suburban Comfort", "$250", "Available"));
        hotels.add(new Hotel("Lakeside Lodge", "$120", "Not Available"));

        return hotels;
    }
}
