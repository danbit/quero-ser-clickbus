package br.com.clickbus.mapper;

/**
 * Interface to DTOMapper
 * 
 * @param <D> the type of DTO class
 * @param <E> the type of Entity class
 *
 * * @author Danilo Bitencourt
 */
public interface DTOConverter<D, E> {

    D convertToDTO(E e);
    
    E convertToEntity(D d);
}
