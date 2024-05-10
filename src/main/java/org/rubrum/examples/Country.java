package org.rubrum.examples;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class Country {
    @Property
    Long id;
    @Property
    String name;
    @Property
    String code;

}
