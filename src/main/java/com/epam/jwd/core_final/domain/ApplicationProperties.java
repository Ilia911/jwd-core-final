package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.reader.PropertyReaderUtil;

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

    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;
    private final Integer fileRefreshRate;
    private final String dateTimeFormat;

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
                throw new InvalidStateException("Invalid properties file name or input stream failed!");
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

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "inputRootDir='" + inputRootDir + '\'' +
                ", outputRootDir='" + outputRootDir + '\'' +
                ", crewFileName='" + crewFileName + '\'' +
                ", missionsFileName='" + missionsFileName + '\'' +
                ", spaceshipsFileName='" + spaceshipsFileName + '\'' +
                ", fileRefreshRate=" + fileRefreshRate +
                ", dateTimeFormat='" + dateTimeFormat + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationProperties)) return false;

        ApplicationProperties that = (ApplicationProperties) o;

        if (inputRootDir != null ? !inputRootDir.equals(that.inputRootDir) : that.inputRootDir != null) return false;
        if (outputRootDir != null ? !outputRootDir.equals(that.outputRootDir) : that.outputRootDir != null)
            return false;
        if (crewFileName != null ? !crewFileName.equals(that.crewFileName) : that.crewFileName != null) return false;
        if (missionsFileName != null ? !missionsFileName.equals(that.missionsFileName) : that.missionsFileName != null)
            return false;
        if (spaceshipsFileName != null ? !spaceshipsFileName.equals(that.spaceshipsFileName) : that.spaceshipsFileName != null)
            return false;
        if (fileRefreshRate != null ? !fileRefreshRate.equals(that.fileRefreshRate) : that.fileRefreshRate != null)
            return false;
        return dateTimeFormat != null ? dateTimeFormat.equals(that.dateTimeFormat) : that.dateTimeFormat == null;
    }

    @Override
    public int hashCode() {
        int result = inputRootDir != null ? inputRootDir.hashCode() : 0;
        result = 31 * result + (outputRootDir != null ? outputRootDir.hashCode() : 0);
        result = 31 * result + (crewFileName != null ? crewFileName.hashCode() : 0);
        result = 31 * result + (missionsFileName != null ? missionsFileName.hashCode() : 0);
        result = 31 * result + (spaceshipsFileName != null ? spaceshipsFileName.hashCode() : 0);
        result = 31 * result + (fileRefreshRate != null ? fileRefreshRate.hashCode() : 0);
        result = 31 * result + (dateTimeFormat != null ? dateTimeFormat.hashCode() : 0);
        return result;
    }
    //todo
}
