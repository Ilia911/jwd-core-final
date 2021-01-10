package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.hardcore.HardcoreFlightMission;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.reader.CrewMembersReaderUtil;
import com.epam.jwd.core_final.util.reader.SpaceshipsReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    public static final ApplicationContext INSTANCE = new NassaContext();
    // no getters/setters for them
    private static final Logger LOGGER = LoggerFactory.getLogger(NassaContext.class);
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    private NassaContext() {
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {

        Collection<T> resultCollection = null;

        if (CrewMember.class.equals(tClass)) {
            resultCollection = (Collection<T>) crewMembers;
        }
        if (Spaceship.class.equals(tClass)) {
            resultCollection = (Collection<T>) spaceships;
        }
        if (FlightMission.class.equals(tClass)) {
            resultCollection = (Collection<T>) flightMissions;
        }
        return resultCollection;
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() {

        try {
            crewMembers = CrewMembersReaderUtil.initCrewMembers();
        } catch (InvalidStateException e) {
            LOGGER.error("Check properties file!" + e.getMessage());
        }
        try {
            spaceships = SpaceshipsReaderUtil.initSpaceships();
        } catch (InvalidStateException e) {
            LOGGER.error("Check properties file!" + e.getMessage());
        }
        flightMissions = HardcoreFlightMission.INSTANCE.hardcoreFlightMissions();
    }
}
