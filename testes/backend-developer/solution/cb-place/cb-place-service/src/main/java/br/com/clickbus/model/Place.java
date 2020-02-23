package br.com.clickbus.model;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * {@link Place} entity model class
 *
 * @author Danilo Bitencourt
 */
@Document(collection = "places")
@Data
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Column(nullable = false)
    private String name;
    
    private String slug;
    
    @NotNull
    @Column(nullable = false)
    private City city;
    
    @NotNull
    @Column(nullable = false)
    private State state;

    @CreatedDate
    @Column(nullable = false)    
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @CreatedDate
    @Column(nullable = false)
    private ZonedDateTime updatedAt = ZonedDateTime.now();

}
