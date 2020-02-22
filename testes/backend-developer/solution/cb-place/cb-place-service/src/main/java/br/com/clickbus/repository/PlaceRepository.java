package br.com.clickbus.repository;

import br.com.clickbus.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Danilo Bitencourt
 */
public interface PlaceRepository extends MongoRepository<Place, String>{

    Place findByName(String name);
    
}
