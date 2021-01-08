package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private Long minId;
    private Long maxId;
    private String partName;
    private Long flightDistance;
    private Boolean isReadyForNextMission;
    private Integer quantityOfCrewMembers;

    private SpaceshipCriteria(Long minId, Long maxId, String partName, Long flightDistance,
                              Boolean isReadyForNextMission, Integer quantityOfCrewMembers) {
        this.minId = minId;
        this.maxId = maxId;
        this.partName = partName;
        this.flightDistance = flightDistance;
        this.isReadyForNextMission = isReadyForNextMission;
        this.quantityOfCrewMembers = quantityOfCrewMembers;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "SpaceshipCriteria{" +
                "minId=" + minId +
                ", maxId=" + maxId +
                ", partName='" + partName + '\'' +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMission=" + isReadyForNextMission +
                ", quantityOfCrewMembers=" + quantityOfCrewMembers +
                '}';
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

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public Integer getQuantityOfCrewMembers() {
        return quantityOfCrewMembers;
    }

    public static class Builder {
        private Long minId;
        private Long maxId;
        private String partName;
        private Long flightDistance;
        private Boolean isReadyForNextMission;
        private Integer quantityOfCrewMembers;

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

        public Builder setFlightDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        public Builder setReadyForNextMission(Boolean isReadyForNextMission) {
            this.isReadyForNextMission = isReadyForNextMission;
            return this;
        }

        public Builder setQuantityOfCrewMembers(Integer quantityOfCrewMembers) {
            this.quantityOfCrewMembers = quantityOfCrewMembers;
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this.minId,
                    this.maxId,
                    this.partName,
                    this.flightDistance,
                    this.isReadyForNextMission,
                    this.quantityOfCrewMembers);
        }
    }

}
