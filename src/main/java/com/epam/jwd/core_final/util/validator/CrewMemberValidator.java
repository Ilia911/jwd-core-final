package com.epam.jwd.core_final.util.validator;

public final class CrewMemberValidator {

    public static boolean isCrewMemberValid(String... args) {

        boolean checkNull = checkNullObject(args);
        boolean checkRole = checkRole(args[0]);
        boolean checkName = checkStrings(args[1]);
        boolean checkRank = checkRank(args[2]);

        return checkNull && checkRole && checkName && checkRank;
    }

    private static boolean checkRank(String object) {
        int rankId = Integer.parseInt(object);
        return rankId >= 1 && rankId <= 4;
    }

    private static boolean checkRole(String object) {
        int roleId = Integer.parseInt(object);
        return roleId >= 1 && roleId <= 4;
    }

    private static boolean checkStrings(String object) {
        return !object.equals("");
    }

    private static boolean checkNullObject(String... args) {
        for (int i = 0; i < 3; i++) {
            if (args[i] == null) {
                return false;
            }
        }
        return true;
    }
}
