package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

    private Long minId;
    private Long maxId;
    private String partName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long minDistance;
    private Long maxDistance;
    private Spaceship assignedSpaceship;
    private CrewMember assignedCrew;
    private MissionResult missionResult;

    private FlightMissionCriteria(Long minId, Long maxId, String partName, LocalDate startDate, LocalDate endDate,
                                  Long minDistance, Long maxDistance, Spaceship assignedSpaceship,
                                  CrewMember assignedCrew, MissionResult missionResult) {
        this.minId = minId;
        this.maxId = maxId;
        this.partName = partName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.assignedSpaceship = assignedSpaceship;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getMinId() {
        return minId;
    }

    public Long getMaxId() {
        return maxId;
    }

    public String getPartName() {
        return partName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getMinDistance() {
        return minDistance;
    }

    public Long getMaxDistance() {
        return maxDistance;
    }

    public Spaceship getAssignedSpaceship() {
        return assignedSpaceship;
    }

    public CrewMember getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    @Override
    public String toString() {
        return "FlightMissionCriteria{" +
                "minId=" + minId +
                ", maxId=" + maxId +
                ", partName='" + partName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", minDistance=" + minDistance +
                ", maxDistance=" + maxDistance +
                ", assignedSpaceship=" + assignedSpaceship +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                '}';
    }

    public static class Builder {
        private Long minId;
        private Long maxId;
        private String partName;
        private LocalDate startDate;
        private LocalDate endDate;
        private Long minDistance;
        private Long maxDistance;
        private Spaceship assignedSpaceship;
        private CrewMember assignedCrew;
        private MissionResult missionResult;

        public Builder setMinId(Long minId) {
            this.minId = minId;
            return this;
        }

        public Builder setMaxId(Long maxId) {
            this.maxId = maxId;
            return this;
        }

        public Builder setPartName(String partName) {
            this.partName = partName;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setMinDistance(Long minDistance) {
            this.minDistance = minDistance;
            return this;
        }

        public Builder setMaxDistance(Long maxDistance) {
            this.maxDistance = maxDistance;
            return this;
        }

        public Builder setAssignedSpaceship(Spaceship assignedSpaceship) {
            this.assignedSpaceship = assignedSpaceship;
            return this;
        }

        public Builder setAssignedCrew(CrewMember assignedCrew) {
            this.assignedCrew = assignedCrew;
            return this;
        }

        public Builder setMissionResult(MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }

        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(
                    this.minId,
                    this.maxId,
                    this.partName,
                    this.startDate,
                    this.endDate,
                    this.minDistance,
                    this.maxDistance,
                    this.assignedSpaceship,
                    this.assignedCrew,
                    this.missionResult);
        }
    }
}
