package org.rubrum.dto;

public class NodeDTO {

    String name;
    NodeProperties properties;


    public NodeDTO(String name) {
        if(name.contains(".java")) name = name.replaceAll(".java","");
        this.name = name;
        properties = new NodeProperties();
    }


    @Override
    public String toString() {
        return "NodeDTO{" +
                "name='" + name + '\'' +
                ", properties=" + properties.toString() +
                '}';
    }
}
