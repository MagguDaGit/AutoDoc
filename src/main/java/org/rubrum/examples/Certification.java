package org.rubrum.examples;


import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.SortedSet;

@NodeEntity
@Setter
@Getter
public class Certification extends BaseNode {

    @Relationship("RELEVANT_TO")
    SortedSet<Technology> technologies;
}
