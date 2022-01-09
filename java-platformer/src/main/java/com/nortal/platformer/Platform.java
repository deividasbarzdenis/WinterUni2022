package com.nortal.platformer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Platform {

    private Integer index;
    private Integer cost;

    @Override
    public String toString() {
        return "  " + (index + 1) +") Platform " + index + ", cost = " + cost;
    }
}
