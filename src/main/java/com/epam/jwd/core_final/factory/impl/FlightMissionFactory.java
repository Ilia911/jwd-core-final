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
        LocalDate startDate = (LocalDate) args[1];
        LocalDate endDate = (LocalDate) args[2];
        Long distance = (Long) args[3];

        flightMission = new FlightMission(missionName, startDate, endDate, distance);

        return flightMission;
    }
}
