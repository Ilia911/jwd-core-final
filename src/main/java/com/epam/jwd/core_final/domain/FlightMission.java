package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {

    private static Long totalNumberOfFlightMissions;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long distance;
    private Spaceship assignedSpaceship;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;
    private final Long id;

    static {
        totalNumberOfFlightMissions = 0L;
    }
    {
        id = ++totalNumberOfFlightMissions;
        missionResult = MissionResult.PLANNED;
        name = "Private mission";
    }

    public FlightMission() {}

    public FlightMission(String missionName, LocalDate startDate, LocalDate endDate, Long distance,
                         Spaceship assignedSpaceship, List<CrewMember> assignedCrew) {
        this.name = missionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceship = assignedSpaceship;
        this.assignedCrew = assignedCrew;
    }

    public FlightMission(String missionName, LocalDate startDate, LocalDate endDate, Long distance) {
        this.name = missionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
    }

    public void setName(String missionName) {
        this.name = missionName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public void setAssignedSpaceship(Spaceship assignedSpaceship) {
        this.assignedSpaceship = assignedSpaceship;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightMission)) return false;

        FlightMission that = (FlightMission) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (assignedSpaceship != null ? !assignedSpaceship.equals(that.assignedSpaceship) : that.assignedSpaceship != null)
            return false;
        if (assignedCrew != null ? !assignedCrew.equals(that.assignedCrew) : that.assignedCrew != null) return false;
        if (missionResult != that.missionResult) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (assignedSpaceship != null ? assignedSpaceship.hashCode() : 0);
        result = 31 * result + (assignedCrew != null ? assignedCrew.hashCode() : 0);
        result = 31 * result + (missionResult != null ? missionResult.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", distance=" + distance +
                ", assignedSpaceship=" + assignedSpaceship +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                ", id=" + id +
                '}';
    }
// todo
}
