package com.nortal.mega.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicUpdate
@Accessors(chain = true)
@Table(name = "building")
public class BuildingDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable =false, updatable=false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "index")
    private String index;
    @Column(name = "sector_code", updatable=false)
    private String sectorCode;
    @Column(name = "energy_units")
    private Integer energyUnits;
    @Column(name = "energy_unit_max", updatable=false)
    private Integer energyUnitMax;

}
