package com.nortal.mega.service;

import com.nortal.mega.exception.BuildingNotFoundException;
import com.nortal.mega.persistence.BuildingDboMapper;
import com.nortal.mega.persistence.BuildingRepository;
import com.nortal.mega.persistence.entity.BuildingDbo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class BuildingServiceTest {

    @Autowired
    private BuildingService service;
    @Autowired
    private BuildingDboMapper buildingDboMapper;
    @MockBean
    private BuildingRepository repository;

    @Test
    @DisplayName("Test findBuildingById success")
    public void findById() {
        // Setup mock repo
        BuildingDbo building = new BuildingDbo()
                .setId(1L)
                .setName("name")
                .setAddress("address")
                .setIndex("index")
                .setSectorCode("sector code")
                .setEnergyUnits(200)
                .setEnergyUnitMax(300);

        doReturn(Optional.of(building)).when(repository).findById(1L);

        //Execute the service call
        Building returnBuilding = service.findBuildingById(1L);

        // Assert the response
        Assertions.assertSame(returnBuilding.getId(), buildingDboMapper.map(building).getId(),
                "The building returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findBuildingById Not Found")
    void testFindByIdNotFound() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(repository).findById(1L);
        BuildingNotFoundException exception = assertThrows(BuildingNotFoundException.class,
                () -> service.findBuildingById(1L));

        // Assert the response
        Assertions.assertThrows(BuildingNotFoundException.class, () -> service.findBuildingById(1L),
                "Building with id number 1 not found");
        Assertions.assertEquals("Building with id number 1 not found", exception.getMessage());
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock repository
        BuildingDbo building1 = new BuildingDbo()
                .setId(1L)
                .setName("name")
                .setAddress("address")
                .setIndex("index")
                .setSectorCode("sector code")
                .setEnergyUnits(200)
                .setEnergyUnitMax(300);
        BuildingDbo building2 = new BuildingDbo()
                .setId(2L)
                .setName("name2")
                .setAddress("address2")
                .setIndex("index2")
                .setSectorCode("sector code 2")
                .setEnergyUnits(100)
                .setEnergyUnitMax(200);

        doReturn(Arrays.asList(building1, building2)).when(repository).findAll();

        // Execute the service call
        List<Building> buildings = service.findAll();

        // Assert the response
        Assertions.assertEquals(2, buildings.size(), "findAll should return 2 widgets");
    }

    @Test
    @DisplayName("Test save building")
    void testSave() {
        // Setup our mock repository
        BuildingDbo building = new BuildingDbo()
                .setId(1L)
                .setName("name")
                .setAddress("address")
                .setIndex("index")
                .setSectorCode("sector code")
                .setEnergyUnits(200)
                .setEnergyUnitMax(300);
        doReturn(building).when(repository).save(any());
        Building buildingMap = buildingDboMapper.map(building);

        // Execute the service call
        Building returnedBuilding = service.saveBuilding(buildingMap);

        // Assert the response
        Assertions.assertEquals(200, returnedBuilding.getEnergyUnits(),
                "The energy units should be same");
    }
}
