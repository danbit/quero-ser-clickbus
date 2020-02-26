package br.com.clickbus.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * {@link Place} entity model class
 *
 * @author Danilo Bitencourt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "places")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;
    private String slug;
    private City city;
    private State state;

    @CreatedDate
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @CreatedDate
    private ZonedDateTime updatedAt = ZonedDateTime.now();

}
