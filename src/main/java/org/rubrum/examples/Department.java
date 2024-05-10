package org.rubrum.examples;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
@Getter
@Setter
public class Department extends BaseNode {
    @Property
    String location;

}
