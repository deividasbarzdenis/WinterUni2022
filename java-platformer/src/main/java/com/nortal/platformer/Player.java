package com.nortal.platformer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor()
@Data
@Builder
public class Player {
    private String name;
    private Integer points;
    private Integer stage;

    public Player(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name + ", " + points + ", " + stage + "\n";
    }
}
