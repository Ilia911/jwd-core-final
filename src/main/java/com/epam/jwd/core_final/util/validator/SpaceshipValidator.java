package com.epam.jwd.core_final.util.validator;

public final class SpaceshipValidator {

    public static boolean isSpaceshipValid(String[] args) {

        boolean checkNull = isNotNull(args);
        boolean checkName = isString(args[0]);
        boolean checkFlightDistance = isPositiveNumber(args[1]);
        boolean checkCrewMap = checkCrewMap(args[2]);

        return checkNull && checkName && checkFlightDistance && checkCrewMap;
    }

    private static boolean checkCrewMap(String arg) {

        for (String roleAndItsQuantity : arg.substring(1, arg.length() - 1).split(",")) {
            String[] temp = roleAndItsQuantity.split(":");
            if (!checkRole(temp[0]) || !isPositiveNumber(temp[1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPositiveNumber(String arg) {
        long flightDistance = Long.parseLong(arg);
        return flightDistance > 0;
    }

    private static boolean checkRole(String object) {
        int roleId = Integer.parseInt(object);
        return roleId >= 1 && roleId <= 4;
    }

    private static boolean isString(String object) {
        return !object.equals("");
    }

    private static boolean isNotNull(String... args) {
        for (int i = 0; i < 3; i++) {
            if (args[i] == null) {
                return false;
            }
        }
        return true;
    }
}
