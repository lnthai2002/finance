package info.noip.darkportal.finance.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Drawback of the fluent setteris that it returns the instance of this class, thus, cannot be chained to the setters of the subclass*/
@MappedSuperclass//this annotation make sure spring JPA does not create a table for this class
@Setter //auto generate setter
@Getter //auto generate getter
@Accessors(chain = true, fluent = true)
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
