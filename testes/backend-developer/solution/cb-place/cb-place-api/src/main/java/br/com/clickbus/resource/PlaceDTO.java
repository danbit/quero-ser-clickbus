package br.com.clickbus.resource;

import br.com.clickbus.model.City;
import br.com.clickbus.model.State;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Danilo Bitencourt
 */
@Getter
@Setter
public class PlaceDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String slug;
    private City city;
    private State state;
    
}
