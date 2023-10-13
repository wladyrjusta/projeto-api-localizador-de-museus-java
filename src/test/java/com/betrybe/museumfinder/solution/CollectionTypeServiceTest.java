package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
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
public class CollectionTypeServiceTest {

  @Autowired
  private CollectionTypeService collectionTypeService;

  @MockBean
  private MuseumFakeDatabase database;

  @Test
  @DisplayName("Testa o retorno de countByCollectionTypes recebendo dois types")
  void testCountByCollectionTypesTwoTypes() {
    String[] collectionTypes = {"hist", "imag"};
    long count = 246L;
    String typeList = "hist,imag";
    Mockito
        .when(database.countByCollectionType(any()))
        .thenReturn(count);

    CollectionTypeCount serviceResponse = collectionTypeService
        .countByCollectionTypes(typeList);

    assertNotNull(serviceResponse);
    int index = 0;
    for (String collectionType : serviceResponse.collectionTypes()) {
      assertEquals(collectionType, collectionTypes[index]);
      index ++;
    }
    assertEquals(serviceResponse.count(), (count + count));

  }

  @Test
  @DisplayName("Testa o retorno de countByCollectionTypes recebendo 1 type")
  void testCountByCollectionTypesOneTypes() {
    String[] collectionTypes = {"hist"};
    long count = 246L;
    String typeList = "hist";
    Mockito
        .when(database.countByCollectionType(any()))
        .thenReturn(count);

    CollectionTypeCount serviceResponse = collectionTypeService
        .countByCollectionTypes(typeList);

    assertNotNull(serviceResponse);
    assertEquals(serviceResponse.collectionTypes()[0], collectionTypes[0]);
    assertEquals(serviceResponse.count(), count);

  }

  @Test
  @DisplayName("Testa o retorno de countByCollectionTypes retornando count 0")
  void testCountByCollectionTypesCountZero() {
    String[] collectionTypes = {"xze"};
    long count = 0L;
    String typeList = "xze";
    Mockito
        .when(database.countByCollectionType(any()))
        .thenReturn(count);

    CollectionTypeCount serviceResponse = collectionTypeService
        .countByCollectionTypes(typeList);

    assertNotNull(serviceResponse);
    assertEquals(serviceResponse.collectionTypes()[0], collectionTypes[0]);
    assertEquals(serviceResponse.count(), count);

  }

}
