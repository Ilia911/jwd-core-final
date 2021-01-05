package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {

    private static Long totalNumberOfCrewMembers = 0L;
    private final Long id;
    private String name;
    private Role role;
    private Rank rank;
    private boolean isReadyForNextMission;

    {
        id = ++totalNumberOfCrewMembers;
        isReadyForNextMission = true;
        name = "Unknown crew member";
    }

    public CrewMember() {}

    public CrewMember(int roleId, String name, int rankId) {
        this.role = Role.resolveRoleById(roleId);
        this.rank = Rank.resolveRankById(rankId);
        this.name = name;

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
// todo
}
