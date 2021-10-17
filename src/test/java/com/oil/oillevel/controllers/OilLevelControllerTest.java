package com.oil.oillevel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oil.oillevel.models.OilLevel;
import com.oil.oillevel.service.OilLevelService;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(OilLevelController.class)
public class OilLevelControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private OilLevelService oilLevelService;

  @Test
  public void getListAllKilometersTest() throws Exception {

    List<OilLevel> listOilLevel = new ArrayList<>();
    listOilLevel.add(new OilLevel(1, "Mazda", 180000));
    listOilLevel.add(new OilLevel(2, "Neon", 400000));
    listOilLevel.add(new OilLevel(3, "Fiesta", 390000));

    Mockito.when(oilLevelService.listAllKilometers()).thenReturn(listOilLevel);

    String url = "/kilometers/list";

    mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().isOk());

  }

  @Test
  public void getOneTest() throws Exception {

    OilLevel oilLevel = new OilLevel(1, "Mazda", 180000);

    Mockito.when(oilLevelService.getOilLevelEntity(1)).thenReturn(oilLevel);

    mockMvc.perform(MockMvcRequestBuilders.get("/kilometers/one/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carName",Matchers.is("Mazda")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.kilometers",Matchers.is(180000)));
    Mockito.verify(oilLevelService).getOilLevelEntity(1);

  }

  @Test
  public void newOilLevelTest() throws Exception {

    String jsonString = "{\n" +
        "\"id\":1,\n" +
        "\"carName\":\"Mazda\",\n" +
        "\"kilometers\":180000\n" +
        "}";

    OilLevel oilLevel = new OilLevel(1, "Mazda", 180000);

    mockMvc.perform(MockMvcRequestBuilders.post("/kilometers/new")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonString))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carName",Matchers.is("Mazda")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.kilometers",Matchers.is(180000)));

  }

  @Test
  public void updateKilometersTest() throws Exception {

    String jsonString = "{\n" +
        "\"id\":1,\n" +
        "\"carName\":\"Mazda\",\n" +
        "\"kilometers\":180000\n" +
        "}";

    OilLevel oilLevel = new OilLevel(1, "Mazda", 180000);

    mockMvc.perform(MockMvcRequestBuilders.put("/kilometers/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonString))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.carName",Matchers.is("Mazda")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.kilometers",Matchers.is(180000)));

  }

  @Test
  public void deleteOilLevelTest() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.delete("/kilometers/delete/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isAccepted());

  }

}
