package br.com.clickbus.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Danilo Bitencourt
 */
@Document(collection = "places")
@Data
public class Place implements Serializable {

    @Id
    private String id;

    private String name;
    private String slug;
    private City city;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
