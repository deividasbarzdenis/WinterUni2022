package com.nortal.platformer_good;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Platform {

    private Integer index;
    private Integer cost;

    @Override
    public String toString() {
        return "Platform " + index + " - cost " + cost;
    }
}
