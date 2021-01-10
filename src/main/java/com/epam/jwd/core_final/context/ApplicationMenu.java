package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.action.HandleUserCrewAction;
import com.epam.jwd.core_final.context.action.HandleUserSpaceshipAction;

import java.util.Scanner;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default void printAvailableOptions() {
        System.out.println("You can use the following options: ");
        System.out.println("Key: '-help' - list available options");
//        System.out.println("Key: '-createMission' - for creating mission");
        System.out.println("Key: '-availableSpaceships' - to see available spaceships\n" +
                "\t You can use one or all modifiers: minId, maxId, partName, crew (for total quantity of crew members), \n" +
                "\t minDistance (for minimum flight distance, km),  maxDistance (for maximum flight distance, km),\n" +
                "\t isReady ('true' - if ready for next mission, 'false' - if isn't ready for next mission");
        System.out.println("Key: '-availableCrewMembers' - to see available crew members. \n" +
                "\t You can use one or all modifiers: minId, maxId, partName, " +
                "role ('1' - MISSION_SPECIALIST, '2' - FLIGHT_ENGINEER, '3' - PILOT, '4' - COMMANDER), \n" +
                "\t rank ('1' - TRAINEE, '2' - SECOND_OFFICER, '3' - FIRST_OFFICER, '4' - CAPTAIN), \n" +
                "\t isReady ('true' - if ready for next mission, 'false' - if isn't ready for next mission");
        System.out.println("Key: -updateCrewMember with all modifiers for crew members");
//        System.out.println("Key: '-changeMission' - to change mission composition");
//        System.out.println("Key: '-addCrewMember' - to add Crew member");
//        System.out.println("Key: '-addSpaceship' - to add Spaceship");
        System.out.println("Divide modifiers and values with ':' ");
        System.out.println("Divide command and modifiers with whitespace ");
        System.out.println("Example: -availableCrewMembers minId:5 maxId:20 partName:per role:2 rank:3 isReady:1");
        System.out.println("-->");
    }


    default void handleUserInput() {
        final Scanner scanner = new Scanner(System.in);
        final HandleUserCrewAction handleUserCrewAction = HandleUserCrewAction.INSTANCE;
        final HandleUserSpaceshipAction handleUserSpaceshipAction = HandleUserSpaceshipAction.INSTANCE;

        while (true) {
            String data = scanner.nextLine();
            String[] commandAndModifiers = data.split(" ");
            String command = commandAndModifiers[0];

            switch (command) {
                case "-help":
                    printAvailableOptions();
                    break;
//                case "-createMission":
//
//                    break;
                case "-availableSpaceships":
                    handleUserSpaceshipAction.availableSpaceship(commandAndModifiers);
                    break;
                case "-availableCrewMembers":
                    handleUserCrewAction.availableCrewMember(commandAndModifiers);
                    break;
//                case "-changeMission":
//
//                    break;
//                case "-addCrewMember":
//
//                    break;
//                case "-addSpaceship":
//
//                    break;
                case "-updateCrewMember":
                    handleUserCrewAction.updateCrewMember(commandAndModifiers);
                    break;
                default:
                    System.out.println("Unknown command. Enter '-help' to list available options");
                    System.out.println("-->");
                    break;
            }
        }
    }

    default void greeting() {
        System.out.println("EPAM Systems");
        System.out.println("Java Web Development course. November 2020 - March 2021 \n");
        System.out.println("Welcome to our flight mission application!");
        System.out.println("Mentor: Mike Nekrasov, Student: Ilia Eriomkin \n");

    }
}
