package com.betrybe.museumfinder.solution;

import static com.betrybe.museumfinder.util.ModelDtoConverter.dtoToModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testa os métodos da classe CollectionTypeService")
public class MuseumServiceTest {

  @Autowired
  private MuseumServiceInterface museumService;

  @MockBean
  private MuseumFakeDatabase database;

  @Test
  @DisplayName("Testa o retorno de getMuseum com id válido")
  void testGetMuseum() {
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

    Optional<Museum> museumMock = Optional.of(dtoToModel(museumDto));

    Mockito
        .when(database.getMuseum(1L))
        .thenReturn(museumMock);

    Museum serviceResponse = museumService.getMuseum(1L);

    assertNotNull(serviceResponse);
    assertEquals(serviceResponse.getId(), museumDto.id());
    assertEquals(serviceResponse.getName(), museumDto.name());
    assertEquals(serviceResponse.getDescription(), museumDto.description());
    assertEquals(serviceResponse.getAddress(), museumDto.address());
    assertEquals(serviceResponse.getCollectionType(), museumDto
        .collectionType());
    assertEquals(serviceResponse.getSubject(), museumDto.subject());
    assertEquals(serviceResponse.getUrl(), museumDto.url());
    assertEquals(serviceResponse.getCoordinate(), museumDto.coordinate());
  }

  @Test
  @DisplayName("Testa o retorno de getMuseum com id inválido")
  void testGetMuseumNotFound() {
    double lat = -15.75063;
    double lgn = -47.9001824;

    Optional<Museum> museumMock = Optional.empty();

    Mockito
        .when(database.getMuseum(any()))
        .thenReturn(museumMock);

    assertThrows(
        MuseumNotFoundException.class,
        () -> museumService.getMuseum(1L)
    );
  }
}
