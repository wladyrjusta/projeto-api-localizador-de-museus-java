package com.betrybe.museumfinder.solution;

import static com.betrybe.museumfinder.util.ModelDtoConverter.dtoToModel;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testa os métodos da classe MuseumController")
public class MuseumControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MuseumServiceInterface museumService;

  @Test
  @DisplayName("Testa o retorno de testGetMuseum quando id válido")
  void testGetMuseum() throws Exception {

    double lat = -15.75063;
    double lgn = -47.9001824;
    Coordinate coordinate = new Coordinate(lat, lgn);
    MuseumDto museumDto = new MuseumDto(
        1L,
        "Museu Casa Memória dos Ex-Combatentes da Segunda Guerra Mundial",
        "Preservação da memória dos ex-combatentes da Segunda Guerra.",
        "SGAN 913, s/n, conjunto F , Asa Norte, 70790-130, Brasília, DF",
        "História",
        "História",
        "",
        coordinate
    );
    Museum museumMock = dtoToModel(museumDto);

    Mockito
        .when(museumService.getMuseum(any()))
        .thenReturn(museumMock);

    String url = "/museums/1";
    mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(museumMock.getId()))
        .andExpect(jsonPath("$.name").value(museumMock.getName()))
        .andExpect(jsonPath("$.description").value(museumMock.getDescription()))
        .andExpect(jsonPath("$.address").value(museumMock.getAddress()))
        .andExpect(jsonPath("$.collectionType").value(museumMock.getCollectionType()))
        .andExpect(jsonPath("$.subject").value(museumMock.getSubject()))
        .andExpect(jsonPath("$.url").value(museumMock.getUrl()));
  }

  @Test
  @DisplayName("Testa o retorno de testGetMuseum quando id inválido")
  void testGetMuseumNotFound() throws Exception {
    Mockito
        .when(museumService.getMuseum(any()))
        .thenThrow(MuseumNotFoundException.class);

    String url = "/museums/999";
    mockMvc.perform(get(url))
        .andExpect(status().isNotFound());
  }
}
