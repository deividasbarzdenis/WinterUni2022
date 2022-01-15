package com.nortal.mega.persistence;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.nortal.mega.persistence.entity.BuildingDbo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

@ExtendWith(DBUnitExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class BuildingRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BuildingRepository repository;

    public ConnectionHolder getConnectionHolder() {
        return () -> dataSource.getConnection();
    }

    @Test
    @DataSet("buildings.yml")
    void testFindAll() {
        List<BuildingDbo> buildings = Lists.newArrayList(repository.findAll());
        Assertions.assertEquals(2, buildings.size(),
                "Expected 2 buildings in the database");
    }

    @Test
    @DataSet("buildings.yml")
    void testFindByIdSuccess() {
        Optional<BuildingDbo> building = repository.findById(3L);
        Assertions.assertTrue(building.isPresent(),
                "We should find a building with ID 3");

        BuildingDbo buildingDbo = building.get();
        Assertions.assertEquals(3, buildingDbo.getId(),
                "The building ID should be 3");
        Assertions.assertEquals("Building 1", buildingDbo.getName(),
                "Incorrect building name");
        Assertions.assertEquals("Address 1", buildingDbo.getAddress(),
                "Incorrect building address");
        Assertions.assertEquals("NO123", buildingDbo.getIndex(),
                "Incorrect building index");
        Assertions.assertEquals("PX12", buildingDbo.getSectorCode(),
                "Incorrect building sector code");
        Assertions.assertEquals(100, buildingDbo.getEnergyUnits(),
                "Incorrect building energy units");
        Assertions.assertEquals(200, buildingDbo.getEnergyUnitMax(),
                "Incorrect building energy unit max");
    }

    @Test
    @DataSet("buildings.yml")
    void testFindByIdNotFound() {
        Optional<BuildingDbo> buildingDbo = repository.findById(5L);
        Assertions.assertFalse(buildingDbo.isPresent(),
                "A building with ID 5 should not be found");
    }

    @Test
    @DataSet("buildings.yml")
    void testFindBuildingsWithNameLike() {
        List<BuildingDbo> list = repository.findBuildingsWithNameLike("Building%");
        Assertions.assertEquals(2, list.size());
    }
}
