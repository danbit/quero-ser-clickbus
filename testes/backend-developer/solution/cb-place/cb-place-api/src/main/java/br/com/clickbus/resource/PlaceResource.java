package br.com.clickbus.resource;

import br.com.clickbus.model.Place;
import br.com.clickbus.service.PlaceService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Danilo Bitencourt
 */
@RestController
@RequestMapping("/api/places")
public class PlaceResource {

    private final PlaceService placeService;

    @Autowired
    public PlaceResource(PlaceService placeRepository) {
        this.placeService = placeRepository;
    }

    /**
     * GET /places : get all the places.
     *
     * @return
     */
    @GetMapping
    public List<Place> findAll() {
        return placeService.findAll();
    }

    /**
     * GET /places/:id : get the "id" place.
     *
     * @param id the id of the place to retrieve
     * @return a ResponseEntity with status 200 and with body the place, or with
     * status 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Place> findOne(@PathVariable String id) {
        Place place = placeService.findById(id);

        return Optional.ofNullable(place).map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * POST /places: Create a new place
     *
     * @param newPlace the place to create
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    public ResponseEntity<Place> createPlace(@Valid @RequestBody Place newPlace) throws URISyntaxException {
        if (newPlace.getId() != null) {
            return ResponseEntity.badRequest().body(null);
        }
        Place place = placeService.save(newPlace);

        return ResponseEntity.created(new URI(String.format("/api/places/%s", place.getId())))
                .body(place);
    }

    /**
     * POST /places: Create a new place
     *
     * @param newPlace
     * @param id
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<Place> updatePlace(@RequestBody Place newPlace, @PathVariable String id) {
        Place place = placeService.update(id, newPlace);
        return ResponseEntity.ok().body(place);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable String id) {
        placeService.delete(id);
        return ResponseEntity.ok().body(null);
    }

}
