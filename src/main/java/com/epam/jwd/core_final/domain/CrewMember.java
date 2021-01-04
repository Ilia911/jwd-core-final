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
    private Role role;
    private Rank rank;
    private boolean isReadyForNextMission;

    {
        id = ++totalNumberOfCrewMembers;
        isReadyForNextMission = true;
    }


    public CrewMember(int id, String name, int rank) {
        this.role = Role.resolveRoleById(id);
        this.name = name;
    }
    // todo
}
