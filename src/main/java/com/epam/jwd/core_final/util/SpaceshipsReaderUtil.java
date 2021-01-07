package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SpaceshipsReaderUtil {

    public static Collection<Spaceship> initSpaceships() throws InvalidStateException {
        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
        Collection<Spaceship> crewMemberCollection = new ArrayList<>();
        String allCrewData;

        try {
            allCrewData = readFile("src/main/resources/" + applicationProperties.getInputRootDir() + "/"
                    + applicationProperties.getSpaceshipsFileName());

            for (String s : allCrewData.split("\n")) {
                String[] spceshipsArgs = s.split(";");
                Spaceship newSpaceship = SpaceshipFactory.INSTANCE.create(spceshipsArgs[0], Long.parseLong(spceshipsArgs[1]), spceshipsArgs[2]);
                crewMemberCollection.add(newSpaceship);
            }

        } catch (IOException e) {
            throw new InvalidStateException("Input stream failed");

        }

        return crewMemberCollection;
    }

    private static String readFile(String filePath) throws IOException {

        StringBuilder allSpaseshipsData = new StringBuilder();
        String temp;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((temp = reader.readLine()) != null)  {
                if (!temp.startsWith("#")){
                    allSpaseshipsData.append(temp).append("\n");
                }
            }
        }
        return allSpaseshipsData.toString();
    }
}
