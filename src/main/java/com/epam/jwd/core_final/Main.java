package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvalidStateException, IOException {
        Application.start();

        CrewMemberCriteria crewMemberCriteria = CrewMemberCriteria.builder().setPartName("HA").setReadyForNextMission(false).build();

        List<CrewMember> list = CrewServiceImpl.INSNANCE.findAllCrewMembersByCriteria(crewMemberCriteria);
        System.out.println(list);


    }
}