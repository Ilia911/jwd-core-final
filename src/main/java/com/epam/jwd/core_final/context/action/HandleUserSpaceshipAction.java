package com.epam.jwd.core_final.context.action;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidUserCommandException;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import static com.epam.jwd.core_final.context.ApplicationMenu.APP_SCANNER;

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

    public void updateSpaceship(String[] args) {

        if (args.length < 2) {
            System.out.println("User command doesn't have enough information!");
            System.out.println("-->");
            return;
        }

        final Optional<Spaceship> optionalSpaceship;

        final SpaceshipCriteria spaceshipCriteria = createSpaceshipCriteria(args);
        optionalSpaceship = spaceshipService.findSpaceshipByCriteria(spaceshipCriteria);

        if (optionalSpaceship.isPresent()) {
            handleUserSpaceshipChangeAction(optionalSpaceship.get());
        } else {
            System.out.println("Spaceship with such data isn't exist");
        }
    }

    private void handleUserSpaceshipChangeAction(Spaceship spaceship) {

        System.out.println("You are going to update data of the next spaceship:");
        System.out.println(spaceship);
        printAvailableOptions();

        String newData = APP_SCANNER.nextLine();
        String[] modifiersAndValues = newData.split(";");

        try {
            changeData(spaceship, modifiersAndValues);
        } catch (InvalidUserCommandException e) {
            LOGGER.error(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("Something was wrong!");
        }
        spaceshipService.updateSpaceshipDetails(spaceship);
    }

    private void changeData(Spaceship spaceship, String[] modifiersAndValues) throws InvalidUserCommandException {
        for (String modifiersAndValue : modifiersAndValues) {
            String[] temp = modifiersAndValue.split(":");
            switch (temp[0]) {
                case "name":
                    spaceship.setName(temp[1]);
                    break;
                case "distance":
                    spaceship.setFlightDistance(Long.parseLong(temp[1]));
                    break;
                case "crew":
                    spaceship.setCrew(createMap(temp[1]));
                    break;
                case "isReady":
                    spaceship.setReadyForNextMission(Boolean.parseBoolean(temp[1]));
                    break;
                default:
                    throw new InvalidUserCommandException("Incorrect input information");
            }
        }
    }

    private Map<Role, Short> createMap(String mapData) {
        Map<Role, Short> map = new HashMap<>();

        for (String roleAndItsQuantity : mapData.split(",")) {
            String[] temp = roleAndItsQuantity.split("-");
            map.put(Role.resolveRoleById(Integer.parseInt(temp[0])), Short.parseShort(temp[1]));
        }
        return map;
    }

    private void printAvailableOptions() {
        System.out.println("Please input new data using spaceship update modifiers: 'name' (string value), " +
                "\n'distance', 'isReady' (values 'true', 'false') - is ready for next mission.\n" +
                "'crew' - (values Role-quantity separated by ','\n");
        System.out.println("Example: name:Blessed;distance:845000;crew:1-5,2-9,3-3,4-3;isReady:1");
        System.out.println("-->");
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
                case "minDistance":
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
    }
}
