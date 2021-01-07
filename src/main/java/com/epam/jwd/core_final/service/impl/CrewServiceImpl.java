package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.CrewMemberAssignmentException;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private CrewServiceImpl(){}

    public static final CrewService INSNANCE = new CrewServiceImpl();
    private Collection<CrewMember> crewMembersList = NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class);

    @Override
    public List<CrewMember> findAllCrewMembers() {

        return crewMembersList.stream().collect(Collectors.toList());
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria) {

        return crewMembersList.stream().
                filter(crewMember -> crewMember.getId() >= criteria.getMinId()).
                filter(crewMember -> crewMember.getId() <= criteria.getMaxId()).
                filter(crewMember -> criteria.getPartName() == null || crewMember.getName().toLowerCase().contains(criteria.getPartName().toLowerCase())).
                filter(crewMember -> criteria.getRole() == null || crewMember.getRole() == criteria.getRole()).
                filter(crewMember -> criteria.getRank() == null || crewMember.getRank() == criteria.getRank()).
                filter(crewMember -> criteria.isReadyForNextMission() == null ||
                        crewMember.isReadyForNextMission() == criteria.isReadyForNextMission()).
                collect(Collectors.toList());

    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(CrewMemberCriteria criteria) {
        return crewMembersList.stream().
                filter(crewMember -> crewMember.getId() >= criteria.getMinId()).
                filter(crewMember -> crewMember.getId() <= criteria.getMaxId()).
                filter(crewMember -> criteria.getPartName() == null || crewMember.getName().toLowerCase().contains(criteria.getPartName().toLowerCase())).
                filter(crewMember -> criteria.getRole() == null || crewMember.getRole() == criteria.getRole()).
                filter(crewMember -> criteria.getRank() == null || crewMember.getRank() == criteria.getRank()).
                filter(crewMember -> criteria.isReadyForNextMission() == null ||
                        crewMember.isReadyForNextMission() == criteria.isReadyForNextMission()).
                findAny();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        for (CrewMember member : crewMembersList) {
            if (member.getId() == crewMember.getId()) {
                member.setName(crewMember.getName());
                member.setRank(crewMember.getRank());
                member.setRole(crewMember.getRole());
                member.setReadyForNextMission(crewMember.isReadyForNextMission());
            }

        }
        return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        if (!crewMember.isReadyForNextMission()) {
            throw new CrewMemberAssignmentException("This crew member isn't ready for next mission!");
        }
        for (CrewMember member : crewMembersList) {
            if (member.getId() == crewMember.getId()) {
                member.setReadyForNextMission(false);
            }
         }
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        // It seems to me that this method has incorrect signature!
        return null;
    }
}
