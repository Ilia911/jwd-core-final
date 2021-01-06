package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.InputException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.IOException;
import java.util.Properties;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public final class ApplicationProperties {

    private String inputRootDir;
    private String outputRootDir;
    private String crewFileName;
    private String missionsFileName;
    private String spaceshipsFileName;
    private Integer fileRefreshRate;
    private String dateTimeFormat;

    private static ApplicationProperties instance;
    private static Properties properties;

    private ApplicationProperties() {
        this.inputRootDir = properties.getProperty("inputRootDir");
        this.outputRootDir = properties.getProperty("outputRootDir");
        this.crewFileName = properties.getProperty("crewFileName");
        this.missionsFileName = properties.getProperty("missionsFileName");
        this.spaceshipsFileName = properties.getProperty("spaceshipsFileName");
        this.fileRefreshRate = Integer.getInteger(properties.getProperty("fileRefreshRate"));
        this.dateTimeFormat = properties.getProperty("dateTimeFormat");
    }

    public static ApplicationProperties getInstance() throws InvalidStateException {
        if (instance == null) {
            try {
                PropertyReaderUtil.loadProperties();
            } catch (IOException e) {
                throw new InvalidStateException("Invalid properties file name!");
            }
            properties = PropertyReaderUtil.getProperties();
            instance = new ApplicationProperties();
        }
        return instance;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
//todo
}
