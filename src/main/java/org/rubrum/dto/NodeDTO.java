package org.rubrum.dto;

import java.util.*;

public class NodeDTO {


    public record relationship(String relationShipName, String nodeType) {}

    String name;
    NodeProperties properties;

    List<relationship> incomingRelationships = new ArrayList<>();
    List<relationship> outgoingRelationships = new ArrayList<>();


    public NodeDTO() {
        properties = new NodeProperties();

    }

    public NodeDTO(String name) {
        setName(name);
        properties = new NodeProperties();
    }


    public void setName(String name) {
        if(name.contains(".java")) name = name.replaceAll(".java","");
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public void addProperty(String propertyName, String type) {
        properties.add(propertyName, type);
    }

    public void addOutgoingRelationship(String relationShipName,String nodeType) {
        outgoingRelationships.add(new relationship(relationShipName, nodeType));
    }

    public void addIncomingRelationship(String relationShipName,String nodeType) {
        incomingRelationships.add(new relationship(relationShipName, nodeType));
    }


    public boolean isValid() {
        return Objects.nonNull(getName());
    }


    @Override
    public String toString() {
        return "NodeDTO{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                ", incomingRelationships=" + incomingRelationships +
                ", outgoingRelationships=" + outgoingRelationships +
                '}';
    }
}
