package org.rubrum.examples;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.id.UuidStrategy;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@NodeEntity
@ToString
public class BaseNode implements Comparable<BaseNode> {

    @Property
    String name;
    @Labels
    List<String> labels = new ArrayList<>(
            List.of(
                    this.getClass().getSimpleName(),
                    this.getClass().getSuperclass().getSimpleName())
    );
    @Transient
    String nodeType = "BaseNode";
    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    @Convert(UuidStringConverter.class)
    private UUID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseNode that = (BaseNode) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public int compareTo(BaseNode other) {
        return getId().compareTo(other.getId());
    }
}
