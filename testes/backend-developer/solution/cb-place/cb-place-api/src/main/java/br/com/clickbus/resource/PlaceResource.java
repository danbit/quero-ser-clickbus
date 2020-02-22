package br.com.clickbus.resource;

import br.com.clickbus.model.Place;
import br.com.clickbus.repository.PlaceRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Danilo Bitencourt
 */
@RestController
@RequestMapping("/api/places")
public class PlaceResource {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceResource(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @PostMapping
    public Place newPlace(@RequestBody Place place) {
        return placeRepository.save(place);
    }

    @GetMapping("/{id}")
    public Place findOne(@PathVariable String id) {
        return placeRepository.findById(id).orElseThrow(()
                -> new RuntimeException(String.format("Id %s not found", id)));
    }

    @PutMapping("{id}")
    public Place updatePlace(@RequestBody Place newPlace, @PathVariable String id) {
        return placeRepository.findById(id).map(p -> {
            p.setName(newPlace.getName());
            p.setSlug(newPlace.getSlug());
            p.setCity(newPlace.getCity());
            p.setState(newPlace.getState());
            p.setUpdatedAt(LocalDateTime.now());
            return placeRepository.save(p);
        }).orElseGet(() -> {
            newPlace.setId(id);
            return placeRepository.save(newPlace);
        });
    }

    @DeleteMapping("{id}")
    public void deletePlace(@PathVariable String id) {
        placeRepository.deleteById(id);
    }

}
