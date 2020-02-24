package br.com.clickbus.resource;

import br.com.clickbus.mapper.PlaceMapper;
import br.com.clickbus.model.Place;
import br.com.clickbus.service.PlaceService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/places")
public class PlaceResource {

    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    @Autowired
    public PlaceResource(PlaceService placeRepository, PlaceMapper placeMapper) {
        this.placeService = placeRepository;
        this.placeMapper = placeMapper;
    }

    /**
     * GET /places : get all the places.
     *
     * @param pageable the pagination options
     * @return the {@link org.springframework.http.ResponseEntity} with status
     * 200 (OK) and the list of places in body
     */
    @GetMapping
    public ResponseEntity<Page<PlaceDTO>> findAll(@NotNull final Pageable pageable) {
        Page<PlaceDTO> places = placeService.findAll(pageable)
                .map(p -> placeMapper.convertToDTO(p));

        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    /**
     * GET /places/:id : get the "id" place.
     *
     * @param id the id of the place to retrieve
     * @return the {@link org.springframework.http.ResponseEntity} with status
     * 200 (OK) and with body the place, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> findOne(@PathVariable String id) {
        PlaceDTO place = placeService.findById(id).map(p -> placeMapper.convertToDTO(p)).get();

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
    public ResponseEntity<PlaceDTO> createPlace(@Valid @RequestBody PlaceDTO newPlace) throws URISyntaxException {
        if (newPlace.getId() != null) {
            return ResponseEntity.badRequest().body(null);
        }

        Place placeEntity = placeMapper.convertToEntity(newPlace);
        PlaceDTO place = placeService.save(placeEntity).map(p -> placeMapper.convertToDTO(p)).get();

        return ResponseEntity.created(new URI(String.format("/api/places/%s", place.getId())))
                .body(place);
    }

    /**
     * POST /places: Create a new place
     *
     * @param newPlace
     * @param id
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * place, or with status 400 (Bad Request) if the place is not valid, or
     * with status 500 (Internal Server Error) if the place could not be updated
     */
    @PutMapping("{id}")
    public ResponseEntity<PlaceDTO> updatePlace(@Valid @RequestBody PlaceDTO newPlace, @PathVariable String id) {
        Place placeEntity = placeMapper.convertToEntity(newPlace);
        PlaceDTO place = placeService.update(id, placeEntity).map(p -> placeMapper.convertToDTO(p)).get();
        return ResponseEntity.ok().body(place);
    }

    /**
     * DELETE /places/:id : delete the "id" place.
     *
     * @param id
     * @return the {@link org.springframework.http.ResponseEntity} with status
     * 200 (OK)
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable String id) {
        placeService.delete(id);
        return ResponseEntity.ok().body(null);
    }

}
