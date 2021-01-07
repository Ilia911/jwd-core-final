package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private Long minId;
    private Long maxId;
    private String partName;
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMission;

    private CrewMemberCriteria(Long minId, Long maxId, String partName, Role role, Rank rank,
                               Boolean isReadyForNextMission) {
        this.minId = minId;
        this.maxId = maxId;
        this.partName = partName;
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMission = isReadyForNextMission;
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

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public static class Builder {
        private Long minId;
        private Long maxId;
        private String partName;
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMission;

        {
            minId = 0L;
            maxId = Long.MAX_VALUE;
        }

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

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder setRank(Rank rank) {
            this.rank = rank;
            return this;
        }

        public Builder setReadyForNextMission(Boolean isReadyForNextMission) {
            this.isReadyForNextMission = isReadyForNextMission;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this.minId, this.maxId, this.partName, this.role, this.rank,
                    this.isReadyForNextMission);
        }
    }
}
