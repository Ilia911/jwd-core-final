package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum MissionResult {
    CANCELLED,
    FAILED,
    PLANNED,
    IN_PROGRESS,
    COMPLETED;

    public static MissionResult resolveRankById(int id) {

        MissionResult missionResult;

        try {
            missionResult = MissionResult.values()[id - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownEntityException("Illegal id!");
        }
        return missionResult;
    }
}
