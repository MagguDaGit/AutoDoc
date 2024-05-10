package org.rubrum.examples;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
@Setter
@Getter
public class Project extends BaseNode {

    @Relationship("BELONGS_TO")
    List<Domain> domains;
    @Relationship("USES_TECHNOLOGY")
    List<Technology> technologies;

    @Override
    public String getNodeType() {
        return "project";
    }

}
