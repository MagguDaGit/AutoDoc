package org.rubrum.examples;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.SortedSet;

@NodeEntity
@Setter
@Getter
@NoArgsConstructor
public class Consultant extends BaseNode {

    @Property
    String firstName;
    @Property
    String lastName;
    @Relationship("BELONGS_TO")
    Department department;
    @Relationship("HAS_CERTIFICATION")
    SortedSet<Certification> certifications;
    @Relationship("INTERESTED_IN")
    SortedSet<Technology> technologiesOfInterest;
    @Relationship("INTERESTED_IN")
    SortedSet<Domain> domainsOfInterest;
    //@Relationship("EXPERIENCED_IN")
    //@JsonIgnoreProperties("consultant")
    //SortedSet<TechnologyExperience> technologyExperience;
    /*@Relationship("EXPERIENCED_IN")
    SortedSet<DomainExperience> domainsOfExperience;*/
    @Relationship("WORKS_ON")
    SortedSet<Project> activeProjects;

    public Consultant(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
