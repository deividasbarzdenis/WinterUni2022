package com.nortal.platformer_good;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Player {
    private Integer points = 500;
    private Platform platform;

    @Override
    public String toString() {
        return "Jumped to platform " + platform.getIndex() +
                " and you have " + points + " points.";
    }
}
