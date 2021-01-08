package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.CrewMemberAssignmentException;
import com.epam.jwd.core_final.exception.CrewMemberCreationException;
import com.epam.jwd.core_final.service.CrewService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private CrewServiceImpl() {
    }

    public static final CrewService INSTANCE = new CrewServiceImpl();
    private final Collection<CrewMember> crewMembersList = NassaContext.INSTANCE.retrieveBaseEntityList(CrewMember.class);

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return crewMembersList.stream().collect(Collectors.toList());
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(CrewMemberCriteria criteria) {
        return crewMembersList.stream().
                filter(crewMember -> crewMember.getId() >= criteria.getMinId()).
                filter(crewMember -> crewMember.getId() <= criteria.getMaxId()).
                filter(crewMember -> criteria.getPartName() == null || crewMember.getName().toLowerCase().
                        contains(criteria.getPartName().toLowerCase())).
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
                filter(crewMember -> criteria.getPartName() == null || crewMember.getName().toLowerCase().
                        contains(criteria.getPartName().toLowerCase())).
                filter(crewMember -> criteria.getRole() == null || crewMember.getRole() == criteria.getRole()).
                filter(crewMember -> criteria.getRank() == null || crewMember.getRank() == criteria.getRank()).
                filter(crewMember -> criteria.isReadyForNextMission() == null ||
                        crewMember.isReadyForNextMission() == criteria.isReadyForNextMission()).
                findAny();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember newCrewMember) {
        for (CrewMember existedCrewMember : crewMembersList) {
            if (existedCrewMember.getId().equals(newCrewMember.getId())) {
                 updateImpl(existedCrewMember, newCrewMember);
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
            if (member.getId().equals(crewMember.getId())) {
                member.setReadyForNextMission(false);
            }
        }
    }

    @Override
    public CrewMember addCrewMember(CrewMember crewMember) throws RuntimeException {
        // It seems to me that this initial method has incorrect signature!
        for (CrewMember member : crewMembersList) {
            if (member.getName().equals(crewMember.getName())) {
                throw new CrewMemberCreationException("Such crew member already exists!");
            }
        }
        crewMembersList.add(crewMember);
        return null;
    }

    private void updateImpl(CrewMember existedCrewMember, CrewMember newCrewMember) {
        existedCrewMember.setName(newCrewMember.getName());
        existedCrewMember.setRank(newCrewMember.getRank());
        existedCrewMember.setRole(newCrewMember.getRole());
        existedCrewMember.setReadyForNextMission(newCrewMember.isReadyForNextMission());
    }
}
