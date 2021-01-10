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
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public void setReadyForNextMission(boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrewMember)) return false;

        CrewMember that = (CrewMember) o;

        if (isReadyForNextMission != that.isReadyForNextMission) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (role != that.role) return false;
        return rank == that.rank;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (isReadyForNextMission ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", rank=" + rank +
                ", isReadyForNextMission=" + isReadyForNextMission +
                '}';
    }
// todo
}
