package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.CrewMemberCreationException;
import com.epam.jwd.core_final.exception.SpaceshipAssignmentException;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {

    private SpaceshipServiceImpl() {
    }

    public static final SpaceshipService INSTANCE = new SpaceshipServiceImpl();
    private final Collection<Spaceship> spaceshipsList = NassaContext.INSTANCE.retrieveBaseEntityList(Spaceship.class);

    @Override
    public List<Spaceship> findAllSpaceships() {
        return spaceshipsList.stream().collect(Collectors.toList());
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(SpaceshipCriteria criteria) {
        return spaceshipsList.stream().
                filter(spaceship -> criteria.getMinId() == null || spaceship.getId() >= criteria.getMinId()).
                filter(spaceship -> criteria.getMaxId() == null || spaceship.getId() <= criteria.getMaxId()).
                filter(spaceship -> criteria.getPartName() == null || spaceship.getName().toLowerCase().
                        contains(criteria.getPartName().toLowerCase())).
                filter(spaceship -> criteria.getMinFlightDistance() == null ||
                        spaceship.getFlightDistance().equals(criteria.getMinFlightDistance())).
                filter(spaceship -> criteria.isReadyForNextMission() == null ||
                        spaceship.isReadyForNextMission() == criteria.isReadyForNextMission()).
                filter(spaceship -> criteria.getQuantityOfCrewMembers() == null ||
                        spaceship.getCrew().values().stream().mapToInt(Short::shortValue).sum() ==
                                criteria.getQuantityOfCrewMembers()).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(SpaceshipCriteria criteria) {
        return spaceshipsList.stream().
                filter(spaceship -> criteria.getMinId() == null || spaceship.getId() >= criteria.getMinId()).
                filter(spaceship -> criteria.getMaxId() == null || spaceship.getId() <= criteria.getMaxId()).
                filter(spaceship -> criteria.getPartName() == null || spaceship.getName().toLowerCase().
                        contains(criteria.getPartName().toLowerCase())).
                filter(spaceship -> criteria.getMinFlightDistance() == null ||
                        spaceship.getFlightDistance().equals(criteria.getMinFlightDistance())).
                filter(spaceship -> criteria.isReadyForNextMission() == null ||
                        spaceship.isReadyForNextMission() == criteria.isReadyForNextMission()).
                filter(spaceship -> criteria.getQuantityOfCrewMembers() == null ||
                        spaceship.getCrew().values().stream().mapToInt(Short::shortValue).sum() ==
                                criteria.getQuantityOfCrewMembers()).
                findAny();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship newSpaceship) {
        for (Spaceship existedSpaceship : spaceshipsList) {
            if (existedSpaceship.getId().equals(newSpaceship.getId())) {
                updateImpl(existedSpaceship, newSpaceship);
            }
        }
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        for (Spaceship spaceshipInStorage : spaceshipsList) {
            if (spaceshipInStorage.getId().equals(spaceship.getId())) {
                if (!spaceshipInStorage.isReadyForNextMission()) {
                    throw new SpaceshipAssignmentException("Such spaceship doesn't ready for next mission!");
                }
                spaceshipInStorage.setReadyForNextMission(false);
                break;
            }
        }
    }

    @Override
    public Spaceship addSpaceship(Spaceship spaceship) throws RuntimeException {
        for (Spaceship spaceshipInStorage : spaceshipsList) {
            if (spaceshipInStorage.getName().equals(spaceship.getName())) {
                throw new CrewMemberCreationException("Spaceship with such name already exists");
            }
        }
        spaceshipsList.add(spaceship);
        return null;
    }

    private void updateImpl(Spaceship existedSpaceship, Spaceship newSpaceship) {
        existedSpaceship.setCrew(newSpaceship.getCrew());
        existedSpaceship.setFlightDistance(newSpaceship.getFlightDistance());
        existedSpaceship.setName(newSpaceship.getName());
        existedSpaceship.setReadyForNextMission(newSpaceship.isReadyForNextMission());
    }
}
