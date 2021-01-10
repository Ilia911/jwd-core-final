package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {

    private MissionServiceImpl() {
    }

    public static final MissionService INSTANCE = new MissionServiceImpl();
    private final Collection<FlightMission> missionsList =
            NassaContext.INSTANCE.retrieveBaseEntityList(FlightMission.class);

    @Override
    public List<FlightMission> findAllMissions() {
        return missionsList.stream().collect(Collectors.toList());
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(FlightMissionCriteria criteria) {
        return missionsList.stream().filter(flightMission -> criteria.getMinId() == null
                || flightMission.getId() >= criteria.getMinId())
                .filter(flightMission -> criteria.getMaxId() == null || flightMission.getId() <= criteria.getMaxId())
                .filter(flightMission -> criteria.getPartName() == null
                        || flightMission.getName().toLowerCase().equals(criteria.getPartName().toLowerCase()))
                .filter(flightMission -> criteria.getStartDate() == null
                        || flightMission.getStartDate().compareTo(criteria.getStartDate()) >= 0)
                .filter(flightMission -> criteria.getEndDate() == null
                        || flightMission.getEndDate().compareTo(criteria.getEndDate()) <= 0)
                .filter(flightMission -> criteria.getMinDistance() == null
                        || flightMission.getDistance() >= criteria.getMinDistance())
                .filter(flightMission -> criteria.getMaxDistance() == null
                        || flightMission.getDistance() <= criteria.getMaxDistance())
                .filter(flightMission -> criteria.getAssignedSpaceship() == null
                        || flightMission.getAssignedSpaceship().equals(criteria.getAssignedSpaceship()))
                .filter(flightMission -> criteria.getAssignedCrew() == null
                        || flightMission.getAssignedCrew().stream()
                        .anyMatch(crewMember -> crewMember.getId().equals(criteria.getAssignedCrew().getId())))
                .filter(flightMission -> criteria.getMissionResult() == null
                        || flightMission.getMissionResult().equals(criteria.getMissionResult()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(FlightMissionCriteria criteria) {
        return missionsList.stream().filter(flightMission -> criteria.getMinId() == null
                || flightMission.getId() >= criteria.getMinId())
                .filter(flightMission -> criteria.getMaxId() == null || flightMission.getId() <= criteria.getMaxId())
                .filter(flightMission -> criteria.getPartName() == null
                        || flightMission.getName().toLowerCase().equals(criteria.getPartName().toLowerCase()))
                .filter(flightMission -> criteria.getStartDate() == null
                        || flightMission.getStartDate().compareTo(criteria.getStartDate()) >= 0)
                .filter(flightMission -> criteria.getEndDate() == null
                        || flightMission.getEndDate().compareTo(criteria.getEndDate()) <= 0)
                .filter(flightMission -> criteria.getMinDistance() == null
                        || flightMission.getDistance() >= criteria.getMinDistance())
                .filter(flightMission -> criteria.getMaxDistance() == null
                        || flightMission.getDistance() <= criteria.getMaxDistance())
                .filter(flightMission -> criteria.getAssignedSpaceship() == null
                        || flightMission.getAssignedSpaceship().equals(criteria.getAssignedSpaceship()))
                .filter(flightMission -> criteria.getAssignedCrew() == null
                        || flightMission.getAssignedCrew().stream()
                        .anyMatch(crewMember -> crewMember.getId().equals(criteria.getAssignedCrew().getId())))
                .filter(flightMission -> criteria.getMissionResult() == null
                        || flightMission.getMissionResult().equals(criteria.getMissionResult()))
                .findAny();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission newMission) {
        for (FlightMission missionInStorage : missionsList) {
            if (missionInStorage.getId().equals(newMission.getId())) {
                updateImpl(missionInStorage, newMission);
                break;
            }
        }
        return null;
    }

    @Override
    public FlightMission addMission(FlightMission flightMission) {
        missionsList.add(flightMission);
        return null;
    }

    private void updateImpl(FlightMission missionInStorage, FlightMission newMission) {
        missionInStorage.setAssignedCrew(newMission.getAssignedCrew());
        missionInStorage.setAssignedSpaceship(newMission.getAssignedSpaceship());
        missionInStorage.setDistance(newMission.getDistance());
        missionInStorage.setEndDate(newMission.getEndDate());
        missionInStorage.setStartDate(newMission.getStartDate());
        missionInStorage.setMissionResult(newMission.getMissionResult());
        missionInStorage.setName(newMission.getName());
    }
}
