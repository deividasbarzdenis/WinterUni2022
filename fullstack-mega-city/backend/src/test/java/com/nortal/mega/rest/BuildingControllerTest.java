package com.nortal.mega.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nortal.mega.exception.BuildingNotFoundException;
import com.nortal.mega.service.Building;
import com.nortal.mega.service.BuildingService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class BuildingControllerTest {

    @MockBean
    private BuildingService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuildingDtoMapper buildingDtoMapper;

    @Test
    @DisplayName("GET /mega/buildings success")
    void testGetBuildingsSuccess() throws Exception {
        // Setup our mock service
        Building building1 = new Building()
                .setId(1L)
                .setName("name")
                .setAddress("address")
                .setIndex("index")
                .setSectorCode("sector code")
                .setEnergyUnits(200)
                .setEnergyUnitMax(300);
        Building building2 = new Building()
                .setId(2L)
                .setName("name2")
                .setAddress("address2")
                .setIndex("index2")
                .setSectorCode("sector code 2")
                .setEnergyUnits(100)
                .setEnergyUnitMax(200);

        doReturn(Lists.newArrayList(building1, building2)).when(service).findAll();

        // Execute the GET request
        mockMvc.perform(get("/mega/buildings"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].address", is("address")))
                .andExpect(jsonPath("$[0].index", is("index")))
                .andExpect(jsonPath("$[0].sectorCode", is("sector code")))
                .andExpect(jsonPath("$[0].energyUnits", is(200)))
                .andExpect(jsonPath("$[0].energyUnitMax", is(300)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("name2")))
                .andExpect(jsonPath("$[1].address", is("address2")))
                .andExpect(jsonPath("$[1].index", is("index2")))
                .andExpect(jsonPath("$[1].sectorCode", is("sector code 2")))
                .andExpect(jsonPath("$[1].energyUnits", is(100)))
                .andExpect(jsonPath("$[1].energyUnitMax", is(200)));
    }

    @Test
    @DisplayName("GET /mega/buildings/1")
    void testGetBuildingById() throws Exception {
        // Setup our mocked service
        Building building = new Building()
                .setId(1L)
                .setName("name")
                .setAddress("address")
                .setIndex("index")
                .setSectorCode("sector code")
                .setEnergyUnits(200)
                .setEnergyUnitMax(300);
        doReturn(building).when(service).findBuildingById(1L);

        // Execute the GET request
        mockMvc.perform(get("/mega/buildings/{buildingId}", 1L))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.address", is("address")))
                .andExpect(jsonPath("$.index", is("index")))
                .andExpect(jsonPath("$.sectorCode", is("sector code")))
                .andExpect(jsonPath("$.energyUnits", is(200)))
                .andExpect(jsonPath("$.energyUnitMax", is(300)));
    }

    @Test
    @DisplayName("POST /mega/buildings/new")
    void testCreateBuilding() throws Exception {
        // Setup our mocked service
        BuildingDto buildingToPost = new BuildingDto()
                .setId(1L)
                .setName("name")
                .setAddress("address")
                .setIndex("NO123")
                .setSectorCode("PX23")
                .setEnergyUnits(200)
                .setEnergyUnitMax(300);

        Building buildingToReturn = new Building()
                .setId(1L)
                .setName("name")
                .setAddress("address")
                .setIndex("NO123")
                .setSectorCode("PX23")
                .setEnergyUnits(200)
                .setEnergyUnitMax(300);

        doReturn(buildingToReturn).when(service).saveBuilding(any());

        // Execute the POST request
        mockMvc.perform(post("/mega/buildings/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(buildingToPost)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.address", is("address")))
                .andExpect(jsonPath("$.index", is("NO123")))
                .andExpect(jsonPath("$.sectorCode", is("PX23")))
                .andExpect(jsonPath("$.energyUnits", is(200)))
                .andExpect(jsonPath("$.energyUnitMax", is(300)));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
