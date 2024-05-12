package org.rubrum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.rubrum.dto.NodeDTO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * responsible for gathering data from @MetaDataParser
 * Collects said data and saves the data as metadata
 * This metadata can then be processed by a service to
 * generate documentation
 */
public class MetaDataCollector {
    List<NodeDTO> data = new ArrayList<>();

    ObjectMapper mapper = new ObjectMapper();


    public MetaDataCollector() {}



    public void transfer(MetaDataParser parser) {
        data.addAll(parser.getParsedData());
    }

    public List<NodeDTO> getCollectedMetadata() {
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
                double version = 0.0;
                for (File f : Objects.requireNonNull(metaDirectory.listFiles())) {
                    String[] words = f.getName().replaceAll(".json","").split("_");
                    double fileVersion = Double.parseDouble(words[words.length-1]);
                    if(fileVersion > version) version = fileVersion + incrementValue;
                }
                metaData = new File(path + "/metadata_"+version+".json");
            }
            FileWriter fileWriter = new FileWriter(metaData);
            fileWriter.write(convertMetadataToJson());

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
