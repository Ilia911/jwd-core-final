package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    public static final ApplicationContext INSTANCE = new NassaContext();
    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    private NassaContext() {}

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
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {

        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
        crewMembers = initCrewMembers(applicationProperties.getCrewFileName());
    }

    private Collection<CrewMember> initCrewMembers(String crewFileName) throws InvalidStateException {

        try(FileInputStream inputStream = new FileInputStream(new File(crewFileName))) {

        } catch (FileNotFoundException e) {
            throw new InvalidStateException("Invalid crewMembers file name");
        } catch (IOException e) {
            throw new InvalidStateException("Input stream failed");

        }

        return null;
    }


}
