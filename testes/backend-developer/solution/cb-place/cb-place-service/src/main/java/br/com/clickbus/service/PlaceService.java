package br.com.clickbus.service;

import br.com.clickbus.model.PlaceDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface to define the Place Services Behavior
 *
 * @author Danilo Bitencourt
 */
public interface PlaceService {

    /**
     * Find all registered places
     *
     * @param pageable {@link org.springframework.data.domain.Pageable} instance
     * representing the pagination options
     * @return An {@link List} of {
     * @Place} registered
     */
    Page<PlaceDTO> findAll(Pageable pageable);

    /**
     * Find registered place by id parameter
     *
     * @param id {@link java.lang.String} instance representing the place id
     * @return {@link PlaceDTO} instance representing the saved place
     */
    Optional<PlaceDTO> findById(String id);

    /**
     * Find registered place by name parameter
     *
     * @param name {@link java.lang.String} instance representing the place name
     * @return {@link PlaceDTO} instance representing the saved place
     */
    Optional<PlaceDTO> findByName(final String name);

    /**
     * Save the Place
     *
     * @param place {@link PlaceDTO} instance representing the place
     * @return {@link PlaceDTO} instance representing the saved place
     */
    Optional<PlaceDTO> save(final PlaceDTO place);

    /**
     * Save the Place
     *
     * @param id {@link java.lang.String} instance representing the place id
     * @param place {@link PlaceDTO} instance representing the place
     * @return {@link PlaceDTO} instance representing the saved place
     */
    Optional<PlaceDTO> update(String id, final PlaceDTO place);

    /**
     * Delete the place correspondent to id
     *
     * @param id {@link java.lang.String} instance representing the place id
     */
    void delete(final String id);

}
