package com.nortal.platformer_good;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CsvFileReader implements FileReader<Platform>{

    // Constant for absolute file path on each machine.
    private static final Path PATH = (Paths.get("./java-platformer/src/main/resources")).toAbsolutePath();

    private final String file;

    /**
     * Reads platforms from csv file and returns the as list.
     *
     * @return platforms - Platforms as list
     */
    @Override
    public List<Platform> read() throws IOException {
        Path pathPlatform = Path.of(PATH + "/" + this.file);
        return Files.lines(pathPlatform)
                .skip(1)
                .map(line -> line.split(", "))
                .map(this::getPlatform)
                .collect(Collectors.toList());
    }

    /**
     * Creates Platform object from csv file taken values and returns new Platform Object.
     *
     * @return Platform - Platform as Object
     */
    private Platform getPlatform(String[] arr) {
        return new Platform(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
    }
}
