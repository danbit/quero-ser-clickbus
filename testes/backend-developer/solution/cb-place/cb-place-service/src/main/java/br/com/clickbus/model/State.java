package br.com.clickbus.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Danilo Bitencourt
 */
@Data
public class State implements Serializable{
    
    private String name;
    private String uf;
    
}
