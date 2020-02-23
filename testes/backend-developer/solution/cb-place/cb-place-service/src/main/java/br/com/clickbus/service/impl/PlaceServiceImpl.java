package br.com.clickbus.service.impl;

import br.com.clickbus.model.Place;
import br.com.clickbus.repository.PlaceRepository;
import br.com.clickbus.service.PlaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * {@link PlaceService} implementation class
 *
 * @author Danilo Bitencourt
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final Logger log = LogManager.getLogger(PlaceServiceImpl.class);

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(final PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Place> findAll(Pageable pageable) {
        return placeRepository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Place findById(String id) {
        Assert.notNull(id, "The place name must be not null");
        return placeRepository.findById(id).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Place findByName(String name) {
        Assert.notNull(name, "The place name must be not null");
        return placeRepository.findByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place save(Place place) {
        Assert.notNull(place, "The place object must be not null");
        log.debug("Request to save Place : {}", place);
        return placeRepository.save(place);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place update(String id, Place place) {
        //TODO valid if id exists before update
        return placeRepository.findById(id).map(p -> {
            p.setName(place.getName());
            p.setSlug(place.getSlug());
            p.setCity(place.getCity());
            p.setState(place.getState());
            return placeRepository.save(p);
        }).orElseGet(() -> {
            place.setId(id);
            return placeRepository.save(place);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String id) {
        Assert.notNull(id, "The place id must be  not null");
        placeRepository.deleteById(id);
    }

}
