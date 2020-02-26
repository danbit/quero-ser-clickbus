package br.com.clickbus.repository;

import br.com.clickbus.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Danilo Bitencourt
 */
public interface PlaceRepository extends MongoRepository<Place, String>{

    Page<Place> findByName(String name, Pageable pageable);
    
}
