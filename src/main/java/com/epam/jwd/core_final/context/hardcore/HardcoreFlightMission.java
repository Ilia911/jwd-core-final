package com.epam.jwd.core_final.context.hardcore;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public enum HardcoreFlightMission {
    INSTANCE;

    private final Collection<CrewMember> crewMembers = NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class);
    private final Collection<Spaceship> spaceships = NassaContext.INSTANCE.retrieveBaseEntityList(Spaceship.class);

    public Collection<FlightMission> hardcoreFlightMissions() {
        Collection<FlightMission> flightMissionCollection = new ArrayList<>();

        FlightMission flightMission1 = FlightMissionFactory.getInstance().create("FirstMission",
                LocalDate.of(2021, 2, 5), LocalDate.of(2021, Month.APRIL, 11), "150000");
        FlightMission flightMission2 = FlightMissionFactory.getInstance().create("SecondMission",
                LocalDate.of(2021, 3, 2), LocalDate.of(2021, Month.MARCH, 15), "230000");

        flightMissionCollection.addAll(Arrays.asList(flightMission1, flightMission2));
        return flightMissionCollection;
    }
}
