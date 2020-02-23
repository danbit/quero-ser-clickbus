package br.com.clickbus.service.impl;

import br.com.clickbus.model.Place;
import br.com.clickbus.repository.PlaceRepository;
import br.com.clickbus.service.PlaceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * {@link PlaceService} implementation class
 *
 * @author Danilo Bitencourt
 */
@Service
@Transactional
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
    @Transactional(readOnly = true)
    public Page<Place> findAll(Pageable pageable) {
        System.out.println(pageable.getSort());
//        String customDir = StringUtils.isEmpty(dir) ? "ASC" : dir.toUpperCase();
//        String customSort = StringUtils.isEmpty(sort) ? "name" : sort;
//        
//        Sort placeSort = Sort.by(Sort.Direction.fromString(customDir), customSort);       
//        Pageable pageable = PageRequest.of(page, size, placeSort);

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
        return placeRepository.save(place);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place update(String id, Place place) {
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
