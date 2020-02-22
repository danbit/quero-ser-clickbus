package br.com.clickbus.service;

import br.com.clickbus.model.Place;
import java.util.List;

/**
 * Interface to define the Place Services Behavior
 *
 * @author Danilo Bitencourt
 */
public interface PlaceService {

    /**
     * Find all registered places
     *
     * @return An {@link List} of {
     * @Place} registered
     */
    List<Place> findAll();

    /**
     * Find registered place by name parameter
     *
     * @param name {@link java.lang.String} instance representing the place name
     *
     * @return {@link Place} instance representing the saved place
     */
    Place findByName(final String name);

    /**
     * Save the Place
     *
     * @param place {@link Place} instance representing the place
     *
     * @return {@link Place} instance representing the saved place
     */
    Place save(final Place place);

    /**
     * Save the Place
     *
     * @param id {@link java.lang.String} instance representing the place id
     *
     * @param place {@link Place} instance representing the place
     *
     * @return {@link Place} instance representing the saved place
     */
    Place update(String id, final Place place);

    /**
     * Delete the place correspondent to id
     *
     * @param id {@link java.lang.String} instance representing the place id
     */
    void delete(final String id);

}
