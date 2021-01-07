package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public final class CrewMembersReaderUtil {


    public static Collection<CrewMember> initCrewMembers() throws InvalidStateException {

        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
        Collection<CrewMember> crewMemberCollection = new ArrayList<>();
        String allCrewData;

        try {
            allCrewData = readFile("src/main/resources/" + applicationProperties.getInputRootDir() + "/"
                    + applicationProperties.getCrewFileName());

            for (String s : allCrewData.split(";")) {
                String[] crewArgs = s.split(",");
                CrewMember newCrewMember = CrewMemberFactory.INSTANCE
                        .create(Integer.parseInt(crewArgs[0]), crewArgs[1], Integer.parseInt(crewArgs[2]));
                crewMemberCollection.add(newCrewMember);
            }

        } catch (IOException e) {
            throw new InvalidStateException("Input stream failed");

        }

        return crewMemberCollection;
    }

    private static String readFile(String filePath) throws IOException {

        StringBuilder allCrewMemberData = new StringBuilder();
        String temp;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((temp = reader.readLine()) != null)  {
                if (!temp.startsWith("#")){
                    allCrewMemberData.append(temp);
                }
            }
        }
        return allCrewMemberData.toString();
    }
}
