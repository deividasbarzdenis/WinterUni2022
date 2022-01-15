package com.nortal.mega.rest;

import com.nortal.mega.service.Building;
import com.nortal.mega.service.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/mega/buildings")
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingDtoMapper buildingDtoMapper;

    @GetMapping
    public ResponseEntity<List<BuildingDto>> getAll() {
        return ResponseEntity.ok(buildingService.findAll().stream().map(buildingDtoMapper::map).collect(Collectors.toList()));
    }

    @GetMapping("/{buildingId}")
    public ResponseEntity<?> getBuildingById(@PathVariable Long buildingId) {
        return ResponseEntity.ok(buildingDtoMapper.map(buildingService.findBuildingById(buildingId)));
    }

    @PostMapping("/new")
    public ResponseEntity<BuildingDto> createBuilding(@RequestBody @Valid BuildingDto building) {
        Building saveBuilding = buildingService.saveBuilding(buildingDtoMapper.map(building));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(buildingDtoMapper.map(saveBuilding));
    }

    @PutMapping("/update")
    public ResponseEntity<BuildingDto> updateBuilding(@RequestBody @Valid BuildingDto building) {
        Building updateBuilding = buildingService.updateBuilding(buildingDtoMapper.map(building));
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(buildingDtoMapper.map(updateBuilding));
    }
    @PatchMapping("/patch")
    public ResponseEntity<BuildingDto> patchBuilding(@RequestBody @Valid BuildingDto building) {
        Building patchBuilding = buildingService.patchBuilding(buildingDtoMapper.map(building));
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(buildingDtoMapper.map(patchBuilding));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        buildingService.deleteEmployee(id);
    }
}
