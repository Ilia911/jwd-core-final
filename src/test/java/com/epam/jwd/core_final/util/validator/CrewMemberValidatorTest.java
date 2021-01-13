package com.epam.jwd.core_final.util.validator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CrewMemberValidatorTest {

    @Test
    public void isCrewMemberValid_shouldValidateDataForCreatingCrewMember_whenRoleAndRankHaveLowestBorders() {
        boolean actual = true;
        boolean expected = CrewMemberValidator.isCrewMemberValid("1", "roy", "1");
        assertEquals(actual, expected);
    }
    @Test
    public void isCrewMemberValid_shouldValidateDataForCreatingCrewMember_whenRoleAndRankHaveHighestBorders() {
        boolean actual = true;
        boolean expected = CrewMemberValidator.isCrewMemberValid("4", "roy", "4");
        assertTrue(expected);
    }
    @Test
    public void isCrewMemberValid_shouldValidateDataForCreatingCrewMember_whenRoleIncorrect() {
        boolean expected = CrewMemberValidator.isCrewMemberValid("5", "roy", "4");
        assertFalse(expected);
    }
    @Test
    public void isCrewMemberValid_shouldValidateDataForCreatingCrewMember_whenRankIncorrect() {
        boolean expected = CrewMemberValidator.isCrewMemberValid("2", "roy", "0");
        assertFalse(expected);
    }
}
