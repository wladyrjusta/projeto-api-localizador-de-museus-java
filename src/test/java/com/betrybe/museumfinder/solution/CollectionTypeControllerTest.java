package com.betrybe.museumfinder.solution;


import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testa os métodos da classe CollectionTypeController")
public class CollectionTypeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CollectionTypeService collectionTypeService;

  @Test
  @DisplayName("Testa o retorno de getCollectionTypesCount quando count > 0")
  void testGetCollectionTypesCount() throws Exception {
    String[] collectionTypes = {"hist", "imag"};
    long count = 246L;
    String typeList = "hist,imag";
    Mockito
        .when(
            collectionTypeService.countByCollectionTypes(typeList)
        )
        .thenReturn(
            new CollectionTypeCount(collectionTypes, count)
        );

    ResultActions controllerResponse = mockMvc.perform(
        get("/collections/count/hist,imag")
    );

    controllerResponse
        .andExpect(status().isOk())
        .andExpect(content()
            .json("{\"collectionTypes\": [\"hist\", \"imag\"], \"count\": 246}"));
  }

  @Test
  @DisplayName("Testa o retorno de getCollectionTypesCount quando count == 0")
  void testGetCollectionTypesCountNotFound() throws Exception {
    String[] collectionTypes = {"xde", "fzg"};
    long count = 0L;
    String typeList = "xde,fzg";
    Mockito
        .when(
            collectionTypeService.countByCollectionTypes(typeList)
        )
        .thenReturn(
            new CollectionTypeCount(collectionTypes, count)
        );

    ResultActions controllerResponse = mockMvc.perform(
        get("/collections/count/xde,fzg")
    );

    controllerResponse
        .andExpect(status().isNotFound());
  }
}
