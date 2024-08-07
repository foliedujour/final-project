package gr.aueb.cf.springfinalproject.model;
/*
    An abstract class where all entities inherit from (the id)
 */
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractEntity implements Serializable, IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
