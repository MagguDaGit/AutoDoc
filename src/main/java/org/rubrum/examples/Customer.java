package org.rubrum.examples;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.SortedSet;
import java.util.TreeSet;

@NodeEntity
@Getter
@Setter
public class Customer extends BaseNode {


    @Relationship("HAS_PROJECT")
    SortedSet<Project> projects;

    @Override
    public String getNodeType() {
        return "Customer";
    }

    public boolean assignProject(Project project) {
        if (project == null) {
            return false; // Don't add null project
        }
        if (projects == null) {
            projects = new TreeSet<>(); // Initialize projects if not already initialized
        }
        return projects.add(project);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
