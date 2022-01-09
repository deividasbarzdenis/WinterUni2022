package com.nortal.mega.persistence;

import com.nortal.mega.persistence.entity.BuildingDbo;
import com.nortal.mega.service.Building;
import org.mapstruct.*;

import java.awt.print.Book;

@Mapper(componentModel = "spring")
public interface BuildingDboMapper {

    Building map(BuildingDbo dbo);

    BuildingDbo map(Building building);

    @Mapping(target = "energyUnitMax", ignore = true)
    @Mapping(target = "sectorCode", ignore = true)
    BuildingDbo mapForUpdate(Building building, @MappingTarget BuildingDbo buildingDbo);

    @Mapping(target = "energyUnitMax", ignore = true)
    @Mapping(target = "sectorCode", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BuildingDbo mapForPatch(Building building, @MappingTarget BuildingDbo buildingDbo);

}
