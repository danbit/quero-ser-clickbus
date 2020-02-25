package br.com.clickbus.service.impl;

import br.com.clickbus.domain.Place;
import br.com.clickbus.mapper.PlaceMapper;
import br.com.clickbus.model.PlaceDTO;
import br.com.clickbus.repository.PlaceRepository;
import br.com.clickbus.service.PlaceService;
import java.util.Optional;
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
    private final PlaceMapper placeMapper;

    @Autowired
    public PlaceServiceImpl(final PlaceRepository placeRepository, final PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlaceDTO> findAll(Pageable pageable) {
        return placeRepository.findAll(pageable).map(p -> placeMapper.convertToDTO(p));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlaceDTO> findById(String id) {
        Assert.notNull(id, "The place name must be not null");
        return placeRepository.findById(id).map(p -> placeMapper.convertToDTO(p));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlaceDTO> findByName(String name) {
        Assert.notNull(name, "The place name must be not null");
        return Optional.of(placeRepository.findByName(name)).map(p -> placeMapper.convertToDTO(p));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PlaceDTO> save(PlaceDTO place) {
        Assert.notNull(place, "The place object must be not null");
        log.debug("Request to save Place : {}", place);
        Place newPlace = placeMapper.convertToEntity(place);
        return Optional.of(placeRepository.save(newPlace)).map(p -> placeMapper.convertToDTO(p));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PlaceDTO> update(String id, PlaceDTO place) {
        //TODO valid if id exists before update
        return placeRepository.findById(id).map(p -> {
            p.setName(place.getName());
            p.setSlug(place.getSlug());
            p.setCity(place.getCity());
            p.setState(place.getState());
            return Optional.of(placeRepository.save(p)).map(pl -> placeMapper.convertToDTO(pl));
        }).orElseGet(() -> {
            place.setId(id);
            return this.save(place);
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
