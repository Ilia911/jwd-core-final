package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        return this.name();
        // but in java documentation:
        // Most programmers should use the toString method in preference to this one,
        // as the toString method may return a more user-friendly name.
        //return this.toString();
    }

    /**
     * todo via java.lang.enum methods!
     * @throws UnknownEntityException if such id does not exist
     */
    public static Role resolveRoleById(int id) {

        Role role;

        try {
            role = Role.values()[id - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownEntityException("Illegal id!");
        }

        return role;
    }
}
