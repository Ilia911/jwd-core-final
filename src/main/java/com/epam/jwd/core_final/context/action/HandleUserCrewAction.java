package com.epam.jwd.core_final.context.action;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidUserCommandException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public enum HandleUserCrewAction {
    INSTANCE;
    private static final CrewService crewService = CrewServiceImpl.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(HandleUserCrewAction.class);

    public void updateCrewMember(String[] args) {
        final Optional<CrewMember> crewMember;
        if (args.length == 1) {
            crewMember = crewService.findAllCrewMembers().stream().findAny();
        } else {
            try {
                final CrewMemberCriteria crewMemberCriteria = createCrewMemberCriteria(args);
                crewMember = crewService.findCrewMemberByCriteria(crewMemberCriteria);
            } catch (InvalidUserCommandException e) {
                System.out.println("Invalid command!");
                return;
            }
        }
        if (crewMember.isPresent()) {
            handleUserCrewMemberChangeAction(crewMember.get());
        } else {
            System.out.println("Crew member with such data isn't exist");
        }
    }

    public void availableCrewMember(String[] args) {
        if (args.length == 1) {
            print(crewService.findAllCrewMembers());
        } else {
            try {
                final CrewMemberCriteria crewMemberCriteria = createCrewMemberCriteria(args);
                print(crewService.findAllCrewMembersByCriteria(crewMemberCriteria));
            } catch (InvalidUserCommandException e) {
                System.out.println("Invalid command!");
            }
        }
    }

    private void handleUserCrewMemberChangeAction(CrewMember crewMember) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("You are going to update data of the next crew member:");
        System.out.println(crewMember);
        System.out.println("Please input new data using crew update modifiers: 'name' (string value), \n'role' (values 1-4), " +
                "'rank' (values 1-4), 'isReady' (values 'true', 'false') - is ready for next mission.\n" +
                "'-exit' - for exit updating information");
        System.out.println("Example: name:Pety Pupkin;role:1;rank:4;isReady:2");
        System.out.println("-->");
        String newData = scanner.nextLine();
        String[] modifiersAndValues = newData.split(";");
        if (modifiersAndValues[0].equals("-exit")) {
            scanner.close();
            return;
        }
        try {
            changeData(crewMember, modifiersAndValues);
        } catch (InvalidUserCommandException e) {
            LOGGER.error(e.getMessage());
            System.out.println(e.getMessage());
//            System.out.println("-->");
//            handleUserCrewMemberChangeAction(crewMember);
        }
        scanner.close();
        crewService.updateCrewMemberDetails(crewMember);
    }

    private void changeData(CrewMember member, String[] modifiersAndValues) throws InvalidUserCommandException {
        for (String modifiersAndValue : modifiersAndValues) {
            String[] temp = modifiersAndValue.split(":");
            switch (temp[0]) {
                case "name":
                    member.setName(temp[1]);
                    break;
                case "role":
                    member.setRole(Role.resolveRoleById(Integer.parseInt(temp[1])));
                    break;
                case "rank":
                    member.setRank(Rank.resolveRankById(Integer.parseInt(temp[1])));
                    break;
                case "isReady":
                    member.setReadyForNextMission(Boolean.parseBoolean(temp[1]));
                    break;
                default:
                    throw new InvalidUserCommandException("Incorrect input information");
            }
        }
    }

    private CrewMemberCriteria createCrewMemberCriteria(String[] args) throws InvalidUserCommandException {
        CrewMemberCriteria.Builder builder = CrewMemberCriteria.builder();

        for (int i = 1; i < args.length; i++) {
            String[] temp = args[i].split(":");
            switch (temp[0]) {
                case "minId":
                    builder = builder.setMinId(Long.parseLong(temp[1]));
                    break;
                case "maxId":
                    builder = builder.setMaxId(Long.parseLong(temp[1]));
                    break;
                case "partName":
                    builder = builder.setPartName(temp[1]);
                    break;
                case "role":
                    builder = builder.setRole(Role.resolveRoleById(Integer.parseInt(temp[1])));
                    break;
                case "rank":
                    builder = builder.setRank(Rank.resolveRankById(Integer.parseInt(temp[1])));
                    break;
                case "isReady":
                    builder = builder.setReadyForNextMission(Boolean.getBoolean(temp[1]));
                    break;
                default:
                    throw new InvalidUserCommandException("Invalid user command. '-help' for list command");
            }
        }
        return builder.build();
    }

    private void print(Collection<? extends BaseEntity> collection) {
        for (BaseEntity baseEntity : collection) {
            System.out.println(baseEntity);
        }
        System.out.println("-->");
    }
}
