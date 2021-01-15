package com.epam.jwd.core_final.context.action;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.InvalidUserCommandException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static com.epam.jwd.core_final.context.ApplicationMenu.APP_SCANNER;

public enum HandleUserMissionAction {
    INSTANCE;

    private static final MissionService missionService = MissionServiceImpl.INSTANCE;
    private static final SpaceshipService spaceshipService = SpaceshipServiceImpl.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(HandleUserMissionAction.class);


    public void createMission(String[] args) {
        final FlightMissionFactory factory = FlightMissionFactory.getInstance();
        FlightMission mission = factory.create(args[1], args[2], args[3], args[4]);
        missionService.addMission(mission);
        LOGGER.info("Mission successfully created and added in mission list");
    }

    public void availableFlightMission(String[] args) {
        if (args.length == 1) {
            print(missionService.findAllMissions());
        } else {
            final FlightMissionCriteria flightMissionCriteria = createFlightMissionCriteria(args);
            print(missionService.findAllMissionsByCriteria(flightMissionCriteria));
        }
    }

    public void outputFlightMission(String[] args) throws InvalidStateException {

        Collection<FlightMission> mission;

        if (args.length == 1) {
            System.out.println("Isn't enough information for fetching mission!");
        } else {
            final FlightMissionCriteria flightMissionCriteria = createFlightMissionCriteria(args);
            mission = missionService.findAllMissionsByCriteria(flightMissionCriteria);
            executeOutputMissions(mission);
        }
    }

    public void updateMission(String[] commandAndModifiers) {
        FlightMission mission;

        Optional<FlightMission> optionalFlightMissionission =
                missionService.findMissionByCriteria(createFlightMissionCriteria(commandAndModifiers));
        if (optionalFlightMissionission.isPresent()) {
            mission = optionalFlightMissionission.get();
            printAvailableOptionsToUpdateMission(mission);
            String updateData = APP_SCANNER.nextLine();
            updateMissionData(mission, updateData);
            missionService.updateSpaceshipDetails(mission);

        }

    }

    private void updateMissionData(FlightMission mission, String updateData) {
        for (String commandAndModifiers : updateData.split(";")) {
            String[] commandAndModifier = commandAndModifiers.split(":");
            switch (commandAndModifier[0]) {
                case "name":
                    mission.setName(commandAndModifier[1]);
                    break;
                case "startDate":
                    mission.setStartDate(createLocalDate(commandAndModifier[1]));
                    break;
                case "endDate":
                    mission.setEndDate(createLocalDate(commandAndModifier[1]));
                    break;
                case "distance":
                    mission.setDistance(Long.parseLong(commandAndModifier[1]));
                    break;
                case "spaceship":
                    mission.setAssignedSpaceship(fetchSpaceship(commandAndModifier[1]));
                    break;
//                    to do: complete this method
                default:
                    System.out.println("Invalid input data!");
                    break;
            }
        }
    }
    private Spaceship fetchSpaceship (String id) {
        Optional<Spaceship> optionalSpaceship =
                spaceshipService.findSpaceshipByCriteria(SpaceshipCriteria.builder().setMaxId(Long.parseLong(id)).build());
        if (optionalSpaceship.isPresent() && optionalSpaceship.get().isReadyForNextMission()) {
            return optionalSpaceship.get();
        }
        return null;
    }

    private LocalDate createLocalDate(String date) {
        String[] stringDate = date.split("_");
        return LocalDate.of(Integer.parseInt(stringDate[0]), Integer.parseInt(stringDate[1]), Integer.parseInt(stringDate[2]));
    }

    private void printAvailableOptionsToUpdateMission(FlightMission mission) {
        System.out.println("You are going to update the following mission:");
        System.out.println(mission);
        System.out.println("List of available command:");
        System.out.println("Key: 'name' to update name ");
        System.out.println("Key: 'startDate' to update start date (format: YYYY_MM_DD");
        System.out.println("Key: 'endDate' to update end date (format: YYYY_MM_DD");
        System.out.println("Key: 'distance' to update distance");
        System.out.println("Key: 'spaceship' to update assigned spaceship (value: id of the assigned spaceship");
        System.out.println("Key: 'status' to update status of the mission " +
                "(values: '1' - CANCELLED, '2' - FAILED, '3' - PLANNED, '4' - IN_PROGRESS, '5' - COMPLETED");
        System.out.println("Key: 'remove' to remove crew member");
        System.out.println("Key: 'add' to add crew member");
        System.out.println("Example name:Awesome Mission;startDate:2021_05_28;endDate:2021_12_31;distance:570000;" +
                "spaceship:13;status:4;add:22;add:43;add48");
    }

    private void executeOutputMissions(Collection<? extends BaseEntity> collection) throws InvalidStateException {
        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
        String outputFile = applicationProperties.getOutputRootDir();
        outputFile = "src/main/resources/".concat(outputFile).concat("/mission.txt");

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(outputFile), collection);
        } catch (IOException e) {
            LOGGER.error("Output stream failed");
        }
    }

    // to do complete this method
    private FlightMissionCriteria createFlightMissionCriteria(String[] args) {
        FlightMissionCriteria.Builder builder = FlightMissionCriteria.builder();

        for (int i = 1; i < args.length; i++) {
            String[] temp = args[i].split(":");
            switch (temp[0]) {
                case "minId":
                    builder = builder.setMinId(Long.parseLong(temp[1]));
                    break;
                case "maxId":
                    builder = builder.setMaxId(Long.parseLong(temp[1]));
                    break;
                case "id":
                    builder = builder.setMinId(Long.parseLong(temp[1]));
                    builder = builder.setMaxId(Long.parseLong(temp[1]));
                    break;
                case "partName":
                    builder = builder.setPartName(temp[1]);
                    break;
                case "startDate":
                    builder = builder.setStartDate(temp[1].split("_"));
                    break;
                case "endDate":
                    builder = builder.setEndDate(temp[1].split("_"));
                    break;
                case "minDistance":
                    builder = builder.setMinDistance(Long.parseLong(temp[1]));
                    break;
                case "maxDistance":
                    builder = builder.setMaxDistance(Long.parseLong(temp[1]));
                    break;
                case "status":
                    builder = builder.setMissionResult(Integer.parseInt(temp[1]));
                    break;
                default:
                    System.out.println("Invalid user modifier. '-help' for list command");
            }
        }
        return builder.build();
    }

    private void print(Collection<? extends BaseEntity> collection) {
        for (BaseEntity baseEntity : collection) {
            System.out.println(baseEntity);
        }
    }

}
