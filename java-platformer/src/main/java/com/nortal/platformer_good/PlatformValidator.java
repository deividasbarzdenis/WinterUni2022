package com.nortal.platformer_good;

import java.util.HashMap;
import java.util.Map;

public class PlatformValidator implements Validator{

    // Immutable Map with values of Player presence on Platform.
    private final Map<Integer, Boolean> values =
            Map.of(0, true, 1, false, 2, false, 3, false, 4, false);

    // Map to set and track Player presence on Platform.
    private Map<Integer, Boolean> platformsValidator = new HashMap<>(values);

    /**
     * Validate player points.
     * @return true - player points value greater than a platform cost value.
     */
    @Override
    public boolean validate(final Player player, final Platform platform){
        return player.getPoints() > platform.getCost();
    }

    /**
     * Sets player presence on a platform.
     * @return void - set platformsValidator value true according to kye.
     */
    @Override
    public void setPlatformsValidator(Integer platform){
        if(!platformsValidator.get(platform)){
            platformsValidator.replace(platform, false, true);
        }
    }

    /**
     * validate player presence on a platform.
     * @return true - value according to platformsValidator kye.
     */
    @Override
    public boolean validatePlatform(Integer platform){
        return platformsValidator.get(platform);
    }


}
