package org.rubrum.examples;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.RelationshipEntity;

@NodeEntity
public class Person {

    @Property
    Long id;
    @Property
    String name;
    @Property
    int age;

    @Relationship("LIVES_IN")
    Country country;

}
