package com.nortal.platformer;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Game {

    public static void main(String[] args) throws IOException {
        Game game = new Game("platforms.csv", "statistics.csv", "playersFile.csv");
        game.run();
    }

    private final String gameFile;
    private final String statisticsFile;
    private final String playersFile;
    private final GameService gameService = new GameService();


    public Game(String gameFile, String statisticsFile, String playersFile) {
        this.gameFile = gameFile;
        this.statisticsFile = statisticsFile;
        this.playersFile = playersFile;
    }

    public void run() throws IOException {
        List<Platform> platforms = gameService.readPlatforms(gameFile);
        gameService.loginOrRegisterAndPlay(platforms, playersFile, statisticsFile);
    }

}
