package org.rubrum.service;

import org.rubrum.dto.NodeDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Parses data from files and creates DTO's that can be server to the @MetaDataCollector
 *
 */
public class MetaDataParser {

    public MetaDataParser() {}



    public void parseFile(List<String> fileLines, String fileName) {
        fileLines.forEach( line -> {
            if(isNodeEntity(fileLines, line)) {
                //System.out.println("Candidate for node entity: " + fileName);
                NodeDTO node = new NodeDTO(fileName);
                //TODO: Add the properties when parsing this file!
                System.out.println(node);
            }
        });
    }

    public void parseFile(File file) throws IOException {
        parseFile(Files.readAllLines(file.toPath()), file.getName());
    }

    //NodeEntity Annotation is supposed to be above the class declaration.
    private boolean isNodeEntity(List<String> fileLines,String currentLine) {
        return currentLine.contains("class") && fileLines.get(fileLines.indexOf(currentLine)-1).contains("@NodeEntity");
    }



}
