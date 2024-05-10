package org.rubrum.dto;

import java.util.HashMap;

public class NodeProperties {
    HashMap<String,String> properties;


    public NodeProperties() {
        properties = new HashMap<>();
    }
    public void add(String name, String type) {
        properties.put(name, type);
    }

    @Override
    public String toString() {
        return properties.toString();
    }
}
