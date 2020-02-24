package br.com.clickbus.mapper;

import br.com.clickbus.model.Place;
import br.com.clickbus.resource.PlaceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Danilo Bitencourt
 */
@Mapper
public class PlaceMapper implements DTOConverter<PlaceDTO, Place>{

    private final ModelMapper modelMapper;

    @Autowired
    public PlaceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    @Override
    public PlaceDTO convertToDTO(Place e) {
        return modelMapper.map(e, PlaceDTO.class);
    }

    @Override
    public Place convertToEntity(PlaceDTO d) {
        return modelMapper.map(d, Place.class);
    }
    
}
