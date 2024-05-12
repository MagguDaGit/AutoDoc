package org.rubrum.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class NodeProperties {
    HashMap<String,String> properties;


    public NodeProperties() {
        properties = new HashMap<>();
    }
    public void add(String name, String type) {
        properties.put(name, type);
    }

}
