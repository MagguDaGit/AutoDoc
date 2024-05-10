package org.rubrum.examples;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@NoArgsConstructor
@Setter
@Getter
public class Domain extends BaseNode {

  /* // public Domain(DomainDTO dto) {
        this.setName(dto.getName());
    }*/

}
