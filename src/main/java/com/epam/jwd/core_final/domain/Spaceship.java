package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {

    private static Long totalNumberOfSpaceships = 0L;

    private String name;
    private Long flightDistance;
    private boolean isReadyForNextMission;
    private final Long id;
    private Map<Role, Short> crew;

    {
        isReadyForNextMission = true;
        id = ++totalNumberOfSpaceships;
    }

    public Spaceship(String name, Long flightDistance, Map<Role, Short> crew) {
        this.name = name;
        this.flightDistance = flightDistance;
        this.crew = crew;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public void setReadyForNextMission(boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "name='" + name + '\'' +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMission=" + isReadyForNextMission +
                ", id=" + id +
                ", crew=" + crew +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spaceship)) return false;

        Spaceship spaceship = (Spaceship) o;

        if (isReadyForNextMission != spaceship.isReadyForNextMission) return false;
        if (name != null ? !name.equals(spaceship.name) : spaceship.name != null) return false;
        if (flightDistance != null ? !flightDistance.equals(spaceship.flightDistance) : spaceship.flightDistance != null)
            return false;
        if (id != null ? !id.equals(spaceship.id) : spaceship.id != null) return false;
        return crew != null ? crew.equals(spaceship.crew) : spaceship.crew == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (flightDistance != null ? flightDistance.hashCode() : 0);
        result = 31 * result + (isReadyForNextMission ? 1 : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (crew != null ? crew.hashCode() : 0);
        return result;
    }
//todo
}
