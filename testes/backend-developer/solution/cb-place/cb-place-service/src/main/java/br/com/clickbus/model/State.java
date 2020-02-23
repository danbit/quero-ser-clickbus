package br.com.clickbus.model;

import java.io.Serializable;
import lombok.Data;

/**
 * {@link State} model class
 * 
 * @author Danilo Bitencourt
 */
@Data
public class State implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String name;
    private String uf;
    
}
