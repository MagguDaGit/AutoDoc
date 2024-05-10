package org.rubrum.examples;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Country {
    Long id;
    String name;
    String code;

}
