package com.epam.jwd.core_final.context.action;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.exception.InvalidUserCommandException;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public enum HandleUserSpaceshipAction {

    INSTANCE;
    private static final SpaceshipService spaceshipService = SpaceshipServiceImpl.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(HandleUserCrewAction.class);

    public void availableSpaceship(String[] args) {
        if (args.length == 1) {
            print(spaceshipService.findAllSpaceships());
        } else {
            final SpaceshipCriteria spaceshipCriteria = createSpaceshipCriteria(args);
            print(spaceshipService.findAllSpaceshipsByCriteria(spaceshipCriteria));
        }
    }

    private SpaceshipCriteria createSpaceshipCriteria(String[] args) {
        SpaceshipCriteria.Builder builder = SpaceshipCriteria.builder();
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
                case "mibDistance":
                    builder = builder.setMinFlightDistance(Long.parseLong(temp[1]));
                    break;
                case "maxDistance":
                    builder = builder.setMaxFlightDistance(Long.parseLong(temp[1]));
                    break;
                case "crew":
                    builder = builder.setQuantityOfCrewMembers(Integer.parseInt(temp[1]));
                    break;
                case "isReady":
                    builder = builder.setReadyForNextMission(Boolean.getBoolean(temp[1]));
                    break;
                default:
                    LOGGER.error("fetching of flight missions failed. Incorrect user input");
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
