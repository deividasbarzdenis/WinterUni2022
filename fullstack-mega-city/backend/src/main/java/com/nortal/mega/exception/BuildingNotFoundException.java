package com.nortal.mega.exception;

public class BuildingNotFoundException extends RuntimeException{

    public BuildingNotFoundException(Long id){
        super("Building with id number " + id + " not found");
    }
    public BuildingNotFoundException(Integer getEnergyUnits, Integer getEnergyUnitMax){
        super("Building energy unit " + getEnergyUnits
                + " cannot be greater than energy unit max " + getEnergyUnitMax);
    }
}
