package org.rubrum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.rubrum.dto.NodeDTO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * responsible for gathering data from @MetaDataParser
 * Collects said data and saves the data as metadata
 * This metadata can then be processed by a service to
 * generate documentation
 */
public class MetaDataCollector {
    SortedSet<NodeDTO> data = new TreeSet<>();

    ObjectMapper mapper = new ObjectMapper();


    public MetaDataCollector() {}



    public void transfer(MetaDataParser parser) {
        data.addAll(parser.getParsedData());
    }

    public SortedSet<NodeDTO> getCollectedMetadata() {
        return data;
    }

    public String convertMetadataToJson() throws JsonProcessingException {
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(data);
    }

    public void saveMetadata(String path, boolean majorVersion) {
        try {
            File metaDirectory = new File(path);
            String initialFileName = "metadata_1.0.json";
            File metaData;
            double incrementValue = majorVersion ? 1.0 : 0.1;
            if(Objects.requireNonNull(metaDirectory.listFiles()).length == 0)
            metaData = new File(path +"/"+ initialFileName);
            else {
                double latestVersion = 0.0;
                for (File f : Objects.requireNonNull(metaDirectory.listFiles())) {
                    String[] words = f.getName().replaceAll(".json","").split("_");
                    double fileVersion = Double.parseDouble(words[words.length-1]);
                    if(fileVersion > latestVersion) latestVersion = fileVersion;
                }
                double newVersion = (double) Math.round((latestVersion + incrementValue) * 100) / 100;
                metaData = new File(path + "/metadata_"+newVersion+".json");
            }
            try(FileWriter fileWriter = new FileWriter(metaData)) {
                fileWriter.write(convertMetadataToJson());
            }

        }
        catch (IOException ioException) {
            System.out.println("Error occured while trying to write metadata: " + ioException.getMessage());
        }
    }


    //TODO: Implement this, is for now hardcoded

    /**
     * NOT implemented, return false for. Need to come back and finish this implementation
     * @return
     */
    public boolean doesCurrentIncludeMajorChanges() {
        return false;
    }
}
