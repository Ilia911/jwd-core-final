package com.epam.jwd.core_final.util.reader;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.util.validator.CrewMemberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public final class CrewMembersReaderUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(CrewMembersReaderUtil.class);

    public static Collection<CrewMember> initCrewMembers() throws InvalidStateException {

        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
        Collection<CrewMember> crewMemberCollection = new ArrayList<>();
        String allCrewData;

        try {
            allCrewData = readFile("src/main/resources/" + applicationProperties.getInputRootDir() + "/"
                    + applicationProperties.getCrewFileName());

            for (String s : allCrewData.split(";")) {
                String[] crewArgs = s.split(",");
                if (!CrewMemberValidator.isCrewMemberValid(crewArgs)) {
                    LOGGER.error("Invalid input data for creating Crew Member! Creating Crew Member faild!");
                    continue;
                }
                CrewMember newCrewMember = CrewMemberFactory.INSTANCE
                        .create(Integer.parseInt(crewArgs[0]), crewArgs[1], Integer.parseInt(crewArgs[2]));
                crewMemberCollection.add(newCrewMember);
                LOGGER.info("Crew Member was successfully created");
            }
        } catch (IOException e) {
            throw new InvalidStateException("Input stream failed for Crew Members");
        }
        return crewMemberCollection;
    }

    private static String readFile(String filePath) throws IOException {

        StringBuilder allCrewMemberData = new StringBuilder();
        String temp;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((temp = reader.readLine()) != null) {
                if (!temp.startsWith("#")) {
                    allCrewMemberData.append(temp);
                }
            }
        }
        return allCrewMemberData.toString();
    }
}
