package com.nortal.mega.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
public class BuildingDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;

    //Todo: All messages have to be move to message_application.properties
    @NotEmpty
    @Pattern(regexp = "(^NO)([A-Z]{2}|\\S)(\\d{2,3})",
            message = "Index number have to began with NO and can have 2 upper case letters and 2 or 3 numbers")
    private String index;

    @NotEmpty
    //@JsonProperty(access = Access.READ_ONLY)
    private String sectorCode;

    @NotNull
    private Integer energyUnits;

    @NotNull
    //@JsonProperty(access = Access.READ_ONLY)
    private Integer energyUnitMax;
}
