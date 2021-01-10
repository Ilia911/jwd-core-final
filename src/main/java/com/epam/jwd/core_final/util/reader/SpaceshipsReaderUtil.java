package com.epam.jwd.core_final.util.reader;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.util.validator.SpaceshipValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public final class SpaceshipsReaderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceshipsReaderUtil.class);
    private static final Collection<Spaceship> crewMemberCollection = new ArrayList<>();

    public static Collection<Spaceship> initSpaceships() throws InvalidStateException {
        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();

        String allCrewData;

        try {
            allCrewData = readFile("src/main/resources/" + applicationProperties.getInputRootDir() + "/"
                    + applicationProperties.getSpaceshipsFileName());

            for (String s : allCrewData.split("\n")) {
                String[] spceshipsArgs = s.split(";");
                if (!SpaceshipValidator.isSpaceshipValid(spceshipsArgs)) {
                    LOGGER.error("Invalid input data for creating Crew Member! Creating Crew Member faild!");
                    continue;
                }
                crewMemberCollection.add(SpaceshipFactory.INSTANCE
                        .create(spceshipsArgs[0], Long.parseLong(spceshipsArgs[1]), spceshipsArgs[2]));
                LOGGER.info("Spaceship was successfully created");
            }
        } catch (IOException e) {
            throw new InvalidStateException("Input stream failed for Spaceships");
        }
        return crewMemberCollection;
    }

    private static String readFile(String filePath) throws IOException {

        StringBuilder allSpaseshipsData = new StringBuilder();
        String temp;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((temp = reader.readLine()) != null) {
                if (!temp.startsWith("#")) {
                    allSpaseshipsData.append(temp).append("\n");
                }
            }
        }
        return allSpaseshipsData.toString();
    }
}
