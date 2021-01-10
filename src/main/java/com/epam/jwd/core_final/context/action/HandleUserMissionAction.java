package com.epam.jwd.core_final.context.action;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.InvalidUserCommandException;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public enum HandleUserMissionAction {
    INSTANCE;

    private static final MissionService missionServece = MissionServiceImpl.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(HandleUserMissionAction.class);

    public void availableFlightMission(String[] args) {
        if (args.length == 1) {
            print(missionServece.findAllMissions());
        } else {
            final FlightMissionCriteria flightMissionCriteria = createFlightMissionCriteria(args);
            print(missionServece.findAllMissionsByCriteria(flightMissionCriteria));
        }
    }

    public void outputFlightMission() throws InvalidStateException {
        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
        Collection<FlightMission> missions = missionServece.findAllMissions();

        String outputFile = applicationProperties.getOutputRootDir();
        outputFile = "src/main/resources/".concat(outputFile).concat("/mission.txt");

        ObjectMapper mapper = new ObjectMapper();

            try {
                mapper.writeValue(new File(outputFile), missions);
//                mapper.writeValueAsString(missions);
            } catch (IOException e) {
                e.printStackTrace();
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
