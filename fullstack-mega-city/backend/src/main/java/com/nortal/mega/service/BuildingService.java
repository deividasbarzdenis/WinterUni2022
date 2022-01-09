package com.nortal.mega.service;

import com.nortal.mega.exception.BuildingNotFoundException;
import com.nortal.mega.persistence.BuildingDboMapper;
import com.nortal.mega.persistence.BuildingRepository;
import com.nortal.mega.persistence.entity.BuildingDbo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingDboMapper buildingDboMapper;

    public List<Building> findAll() {
        return buildingRepository.findAll().stream()
                .map(buildingDboMapper::map)
                .collect(Collectors.toList());
    }

    public Building findBuildingById(Long id) {
        return buildingDboMapper
                .map(buildingRepository
                        .findById(id)
                        .orElseThrow(() -> new BuildingNotFoundException(id)));
    }

    public Building saveBuilding(Building building) {
        BuildingDbo savedBuilding = buildingRepository.save(buildingDboMapper.map(building));
        return buildingDboMapper.map(savedBuilding);
    }

    public Building updateBuilding(Building building) {
        Long id = building.getId();
        if (id == null) {
            throw new BuildingNotFoundException(id);
        }
        BuildingDbo buildingDbo = getById(id);
        BuildingDbo updateBuilding = buildingDboMapper.mapForUpdate(building, buildingDbo);
        buildingRepository.save(updateBuilding);
        return buildingDboMapper.map(updateBuilding);
    }

    public Building patchBuilding(Building building) {
        Long id = building.getId();
        if (id == null) {
            throw new BuildingNotFoundException(id);
        }
        BuildingDbo buildingDbo = getById(id);
        BuildingDbo patchBuilding = buildingDboMapper.mapForPatch(building, buildingDbo);
        buildingRepository.save(patchBuilding);
        return buildingDboMapper.map(patchBuilding);
    }

    private BuildingDbo getById(long id) {
        return buildingRepository
                .findById(id)
                .orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @Transactional
    public void deleteEmployee(long id){
        if (!buildingRepository.existsById(id)) {
            throw new BuildingNotFoundException(id);
        }
        buildingRepository.deleteEmployeeById(id);
    }

}
