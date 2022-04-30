package com.nortal.platformer_good;

public interface Validator {

    boolean validate(Player player, Platform platform);

    void setPlatformsValidator(Integer platform);

    boolean validatePlatform(Integer platform);

}
