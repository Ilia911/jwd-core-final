package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private final Long minId;
    private final Long maxId;
    private final String partName;
    private final Long minFlightDistance;
    private final Long maxFlightDistance;
    private final Boolean isReadyForNextMission;
    private final Integer quantityOfCrewMembers;

    private SpaceshipCriteria(Long minId, Long maxId, String partName, Long minFlightDistance,
                              Long maxFlightDistance, Boolean isReadyForNextMission, Integer quantityOfCrewMembers) {
        this.minId = minId;
        this.maxId = maxId;
        this.partName = partName;
        this.minFlightDistance = minFlightDistance;
        this.maxFlightDistance = maxFlightDistance;
        this.isReadyForNextMission = isReadyForNextMission;
        this.quantityOfCrewMembers = quantityOfCrewMembers;
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

    public Long getMinFlightDistance() {
        return minFlightDistance;
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
        private Long minFlightDistance;
        private Long maxFlightDistance;
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

        public Builder setMinFlightDistance(Long minFlightDistance) {
            this.minFlightDistance = minFlightDistance;
            return this;
        }

        public Builder setMaxFlightDistance(Long maxFlightDistance) {
            this.maxFlightDistance = maxFlightDistance;
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
                    this.minFlightDistance,
                    this.maxFlightDistance,
                    this.isReadyForNextMission,
                    this.quantityOfCrewMembers);
        }
    }

}
