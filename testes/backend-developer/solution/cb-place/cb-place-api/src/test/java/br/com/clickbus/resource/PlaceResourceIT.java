package br.com.clickbus.resource;

import br.com.clickbus.domain.City;
import br.com.clickbus.domain.State;
import br.com.clickbus.model.PlaceDTO;
import br.com.clickbus.service.PlaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author Danilo Bitencourt
 */
@ExtendWith(MockitoExtension.class)
public class PlaceResourceIT {

    private MockMvc mockMvc;

    @Mock
    private PlaceService placeService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private PlaceResource placeResource;

    private PlaceDTO validPlaceDTO;
    private PlaceDTO validPlaceDTOWithoutId;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(placeResource).build();

        City salvador = new City("Salvador");
        State bahia = new State("Bahia", "BA");

        this.validPlaceDTO = PlaceDTO.builder().id("100").name("Mercado Modelo")
                .slug("mercado-modelo").city(salvador).state(bahia).build();
        this.validPlaceDTOWithoutId = PlaceDTO.builder().name(validPlaceDTO.getName())
                .slug(validPlaceDTO.getSlug()).city(salvador).state(bahia).build();

    }

    /**
     * Test of findAll method, of class PlaceDTOResource.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll() throws Exception {
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "name"));

        Page<PlaceDTO> places = new PageImpl<>(createPlaceDTOs(), pageable, 3);

        Mockito.when(placeService.findAll(pageable)).thenReturn(places);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/places?size=3&page=0&sort=name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", Matchers.hasSize(3)))
                .andReturn();
    }

    /**
     * Test of findOne method, of class PlaceDTOResource.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFindOne() throws Exception {
        String id = "100";
        Mockito.when(placeService.findById(id))
                .thenReturn(Optional.of(validPlaceDTO));

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/places/%s", id))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(validPlaceDTO.getId())))
                .andReturn();
    }

    /**
     * Test of createPlaceDTO method, of class PlaceDTOResource.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreatePlaceDTO() throws Exception {
        Mockito.when(placeService.save(any(PlaceDTO.class)))
                .thenReturn(Optional.of(validPlaceDTO));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/places")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(validPlaceDTOWithoutId)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", Matchers.is(validPlaceDTO.getId())))
                .andReturn();
    }

    /**
     * Test of updatePlaceDTO method, of class PlaceDTOResource.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdatePlaceDTO() throws Exception {
        String id = "100";
        validPlaceDTO.setName("updated name");
        
        Mockito.when(placeService.update(eq(id), any(PlaceDTO.class)))
                .thenReturn(Optional.of(validPlaceDTO));

        mockMvc.perform(MockMvcRequestBuilders.put(String.format("/api/places/%s", id))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(validPlaceDTO)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", Matchers.is(validPlaceDTO.getName())))
                .andReturn();
    }

    /**
     * Test of deletePlaceDTO method, of class PlaceDTOResource.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeletePlaceDTO() throws Exception {
        String id = "100";        
        Mockito.doNothing().when(placeService).delete(id);
        
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/places/%s", id))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    private List<PlaceDTO> createPlaceDTOs() {
        final List<PlaceDTO> places = new ArrayList<>(3);
        City salvador = new City("Salvador");
        State bahia = new State("Bahia", "BA");

        PlaceDTO pelourinho = PlaceDTO.builder().id("1").name("Pelourinho")
                .slug("pelourinho").city(salvador).state(bahia).build();
        PlaceDTO farol = PlaceDTO.builder().id("2").name("Farol da Barra")
                .slug("farol-da-barra").city(salvador).state(bahia).build();
        PlaceDTO bonfim = PlaceDTO.builder().id("3").name("Igreja do Bonfim")
                .slug("igreja-do-bonfim").city(salvador).state(bahia).build();

        places.add(pelourinho);
        places.add(farol);
        places.add(bonfim);

        return places;
    }

}
