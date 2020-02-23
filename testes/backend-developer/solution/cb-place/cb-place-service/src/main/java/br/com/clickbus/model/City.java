package br.com.clickbus.model;

import java.io.Serializable;
import lombok.Data;

/**
 * {@link City} model class
 *
 * @author Danilo Bitencourt
 */
@Data
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

}
