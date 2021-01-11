package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    private static FlightMissionFactory instance;

    private FlightMissionFactory() {
    }

    public static FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public FlightMission create(Object... args) {

        FlightMission flightMission;

        String missionName = (String) args[0];

        String[] start = ((String) args[1]).split("_");
        String[] end = ((String) args[2]).split("_");

        LocalDate startDate = LocalDate.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2]));
        Long distance = Long.parseLong((String) args[3]);

        flightMission = new FlightMission(missionName, startDate, endDate, distance);

        return flightMission;
    }
}
