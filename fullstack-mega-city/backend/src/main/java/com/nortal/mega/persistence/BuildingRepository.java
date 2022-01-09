package com.nortal.mega.persistence;

import com.nortal.mega.persistence.entity.BuildingDbo;
import com.nortal.mega.service.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends CrudRepository<BuildingDbo, Long> {

    List<BuildingDbo> findAll();
    void deleteEmployeeById(long id);
    //Todo: implement findAll(), findAllByAddress(), findAllByAddressOrNameOrIndex(), findAllByEnergyUnitsBetween()...:
    Page<BuildingDbo> findAll(Pageable pageable);
    Page<BuildingDbo> findAllByAddress(String address, Pageable page);
    List<BuildingDbo> findAllByAddressOrNameOrIndex(String address, String name, String index);
    List<BuildingDbo> findAllByEnergyUnitsBetween(Integer start, Integer end);
    List<BuildingDbo> findAllBySectorCode(String sectorCode);
    List<BuildingDbo> findAllByEnergyUnitMax(Integer energyUnits);

}
