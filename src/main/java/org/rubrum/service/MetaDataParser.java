package org.rubrum.service;

import org.rubrum.dto.NodeDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses data from files and creates DTO's that can be server to the @MetaDataCollector
 *
 */
//Ignore-AutoDoc
public class MetaDataParser {

    public MetaDataParser() {}

    private List<NodeDTO> parsedNodes = new ArrayList<>();

    public void parseFile(List<String> fileLines, String fileName) {
        if(Objects.equals(fileName, "MetaDataParser.java")) return;
        if(!isNodeEntity(fileLines)) return;
        NodeDTO node = new NodeDTO();
        node.setName(fileName);
        AtomicInteger i = new AtomicInteger();
        for (String currentLine : fileLines) {
            if (isPropertyAnnotation(currentLine)) {
                String nextLine = getNextLine(fileLines, i.get());
                node.addProperty(getPropertyName(nextLine), getPropertyValueType(nextLine));
            }
            if(isRelationshipAnnotation(currentLine)) {
                String relationshipName = getRelationshipName(currentLine);
                String nodeType = getRelationshipNode(getNextLine(fileLines, i.get()));
                if(isRelationshipIncoming(currentLine)) {
                    if (!nodeType.isEmpty())node.addIncomingRelationship(relationshipName, nodeType);
                }
                else {
                    if(!nodeType.isEmpty()) node.addOutgoingRelationship(relationshipName,nodeType);
                }
            }
            i.getAndIncrement();
        }
        if(node.isValid()) parsedNodes.add(node);
    }

    public List<NodeDTO> getParsedData() {
        return parsedNodes;
    }

    public void parseFile(File file) throws IOException {
         parseFile(Files.readAllLines(file.toPath()), file.getName());
    }

    private boolean isNodeEntity(List<String> fileLines) {
        return fileLines.contains("@NodeEntity");
    }

    private boolean isPropertyAnnotation(String currentLine) {
        return currentLine.contains("@Property") && !currentLine.contains("//") && !currentLine.contains("/*");
    }

    private boolean isRelationshipAnnotation(String currentLine) {
        return currentLine.contains("@Relationship") && !currentLine.contains("//") && !currentLine.contains("/*");
    }

    private String getPropertyName(String line) {
        String [] words = line.trim().split(" ");
        return words[1]
                .replaceAll(";","")
                .replaceAll("=","");
    }

    private String getPropertyValueType(String line) {
        String [] words = line.trim().split(" ");
        return words[0]
                .replaceAll(";","")
                .replaceAll("=","");
    }

    private String getRelationshipName(String currentLine) {
        String[] words = currentLine.trim().split("\\(");
        return words[1].replaceAll("\\)","");
    }

    private String getRelationshipNode(String line) {
        String relationshipNode = "";
        //if line contains <> its a collection type. So we have to parse accordingly
        if(line.contains("<") && line.contains(">")) {
            Pattern pattern = Pattern.compile("<(.*?)>");
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) relationshipNode = matcher.group(1);
        }
        else {
            String[] words = line.trim().split(" ");
            relationshipNode = words[0];
        }
        return relationshipNode;
    }

    private boolean isRelationshipIncoming(String currentLine) {
        return currentLine.toLowerCase().contains("incoming");
    }

    private String getNextLine(List<String> fileLines, int currentLine) {
        return fileLines.get(currentLine + 1);
    }


}
