package br.com.clickbus.service.impl;

import br.com.clickbus.model.Place;
import br.com.clickbus.repository.PlaceRepository;
import br.com.clickbus.service.PlaceService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link PlaceService} implementation class
 *
 * @author Danilo Bitencourt
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(final PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place findByName(String name) {
        return placeRepository.findByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place save(Place place) {
        return placeRepository.save(place);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place update(String id, Place place) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String id) {
        placeRepository.deleteById(id);
    }

}
