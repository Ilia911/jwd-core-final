package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.action.HandleUserCrewAction;
import com.epam.jwd.core_final.context.action.HandleUserMissionAction;
import com.epam.jwd.core_final.context.action.HandleUserSpaceshipAction;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.Scanner;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default void printAvailableOptions() {
        System.out.println("You can use the following options: ");
        System.out.println("Key: '-help' - list available options");

        System.out.println("Key: '-availableSpaceships' - to see available spaceships\n" +
                "\t You can use one or all modifiers: minId, maxId, partName, crew (for total quantity of crew members), \n" +
                "\t minDistance (for minimum flight distance, km),  maxDistance (for maximum flight distance, km),\n" +
                "\t isReady ('true' - if ready for next mission, 'false' - if isn't ready for next mission \n" +
                "\t Example: -availableSpaceships minId:5 maxId:20 partName:ll isReady:true");

        System.out.println("Key: '-availableCrewMembers' - to see available crew members. \n" +
                "\t You can use one or all modifiers: minId, maxId, partName, " +
                "role ('1' - MISSION_SPECIALIST, '2' - FLIGHT_ENGINEER, '3' - PILOT, '4' - COMMANDER), \n" +
                "\t rank ('1' - TRAINEE, '2' - SECOND_OFFICER, '3' - FIRST_OFFICER, '4' - CAPTAIN), \n" +
                "\t isReady ('true' - if ready for next mission, 'false' - if isn't ready for next mission \n" +
                "\t Example: -availableCrewMembers minId:5 maxId:20 partName:per role:2 rank:3 isReady:1");

        System.out.println("Key: '-availableMissions' - to see available missions. \n" +
                "\t You can use one or all modifiers: minId, maxId, partName, \n" +
                "\t 'startDate' - format YYYY_mm_dd,  'endDate' - format YYYY_mm_dd, \n" +
                "\t 'minDistance' - km, 'maxDistance' - km ,'spaceship' - id of the assigned spaceship \n" +
                "\t 'status' (values: '1' - CANCELLED, '2' - FAILED, '3' - PLANNED, '4' - IN_PROGRESS, '5' - COMPLETED \n" +
                "\t Example: -availableMissions minId:1 maxId:5 partName:mission spaceship:1");

        System.out.println("Key: '-createMission' to create missions \n" +
                "\t You must write values without modifiers in strict order: name, start date (format YYYY_mm_dd),  " +
                "end date (format YYYY_mm_dd),  distance (km). \n" +
                "\t Example: -createMission Frantic 2021_5_12 2021_8_1 234986");

        System.out.println("Key: '-updateSpaceship' with all modifiers for spaceships");

        System.out.println("Key: '-updateCrewMember' with all modifiers for crew members");

//        System.out.println("Key: '-updateMission' with all modifiers for flight missions");

        System.out.println("Key: '-outputMission' to store mission in file (modified in application.properties\n" +
                "\t You can use one or all modifiers: id, partName, \n" +
                "\t 'startDate' - format YYYY_mm_dd,  'endDate' - format YYYY_mm_dd, \n" +
                "\t 'minDistance' - km, 'maxDistance' - km ,'spaceship' - id of the assigned spaceship \n" +
                "\t 'status' (values: '1' - CANCELLED, '2' - FAILED, '3' - PLANNED, '4' - IN_PROGRESS, '5' - COMPLETED \n" +
                "\t Example: -availableMissions id:1 partName:mission spaceship:1");

//          to do:
//        System.out.println("Key: '-createMission' - for creating mission");
//        System.out.println("Key: '-changeMission' - to change mission composition");
//        System.out.println("Key: '-addCrewMember' - to add Crew member");
//        System.out.println("Key: '-addSpaceship' - to add Spaceship");
        System.out.println("Key: '-exit' - to close the application");
        System.out.println("Divide modifiers and values with ':' ");
        System.out.println("Divide command and modifiers with whitespace ");
        System.out.println("value of the modifier 'partName' without whitespaces!");
        System.out.println("-->");
    }


    default void handleUserInput() throws InvalidStateException {
        final Scanner scanner = new Scanner(System.in);
        final HandleUserCrewAction handleUserCrewAction = HandleUserCrewAction.INSTANCE;
        final HandleUserSpaceshipAction handleUserSpaceshipAction = HandleUserSpaceshipAction.INSTANCE;
        final HandleUserMissionAction handleUserMissionAction = HandleUserMissionAction.INSTANCE;

        String data;
        String[] commandAndModifiers;
        while (true) {

            if (scanner.hasNext()) {
                data = scanner.nextLine();
                commandAndModifiers = data.split(" ");
                String command = commandAndModifiers[0];

                switch (command) {
                    case "-help":
                        printAvailableOptions();
                        break;
                    case "-availableSpaceships":
                        handleUserSpaceshipAction.availableSpaceship(commandAndModifiers);
                        break;
                    case "-availableCrewMembers":
                        handleUserCrewAction.availableCrewMember(commandAndModifiers);
                        break;
                    case "-availableMissions":
                        handleUserMissionAction.availableFlightMission(commandAndModifiers);
                        break;
                    case "-outputMission":
                        handleUserMissionAction.outputFlightMission(commandAndModifiers);
                        break;
                    case "-createMission":
                        handleUserMissionAction.createMission(commandAndModifiers);
                        break;
                    case "-updateSpaceship":
                        handleUserSpaceshipAction.updateSpaceship(commandAndModifiers);
                        break;
//                    to do:
//                case "-changeMission":
//
//                    break;
//                case "-addCrewMember":
//
//                    break;
//                case "-addSpaceship":
//
//                    break;
//                case "-createMission":
////
////                    break;
                    case "-updateCrewMember":
                        handleUserCrewAction.updateCrewMember(commandAndModifiers);
                        break;
                    case "-exit":
                        scanner.close();
                        return;
                    default:
                        System.out.println("Unknown command. Enter '-help' to list available options");
                        System.out.println("-->");
                        break;
                }
            }
        }
    }

    default void greeting() {
        System.out.println("EPAM Systems");
        System.out.println("Java Web Development course. November 2020 - March 2021");
        System.out.println("Mentor: Mike Nekrasov, Student: Ilia Eriomkin \n");
        System.out.println("Welcome to our flight mission application!");
    }
}
