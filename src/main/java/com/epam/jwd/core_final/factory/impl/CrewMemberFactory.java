package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    public static final CrewMemberFactory INSTANCE = new CrewMemberFactory();

    private CrewMemberFactory() {}


    @Override
    public CrewMember create(Object... args) {

        CrewMember crewMember;

        int roleId = (Integer) args[0];
        String name = (String) args[1];
        int rankId = (Integer) args[2];

        crewMember = new CrewMember(roleId, name, rankId);

        return crewMember;
    }
}
