package com.nortal.platformer_good;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.nortal.platformer_good.Game.Action.ADD;
import static com.nortal.platformer_good.Game.Action.SUBSTRACT;

public class Game {

    public static void main(String[] args) throws IOException {
        final FileReader<Platform> reader = new CsvFileReader("platforms.csv");
        final Validator validator = new PlatformValidator();

        Game game = new Game(reader, validator);
        game.run();
    }

    private final FileReader<Platform> reader;
    private final Validator validator;

    public Game(final FileReader<Platform> reader, Validator validator) {
        this.reader = reader;
        this.validator = validator;
    }

    /**
     * Runs game app
     * @return void - prints game results to the console.
     */
    public void run() throws IOException {
        getResults().forEach(System.out::println);
    }

    /**
     * Set Player list.
     * @return List<Player> - List of Player objects.
     */
    private List<Player> getResults() throws IOException {
        final List<Player> resultSteps = new ArrayList<>();
        final List<Platform> platforms = reader.read();
        Player player = new Player();
        int step = 0;

        for (int i = 0; i < platforms.size(); i++) {
            step += 1;

            if (step > 4) {
                break;
            }

            if (validator.validate(player, platforms.get(step))) {
                if (validator.validatePlatform(platforms.get(step).getIndex())) {
                    player = setPlayer(
                            setPoints(player.getPoints(), platforms.get(step).getCost(), ADD),
                            platforms.get(step)
                    );
                    resultSteps.add(player);
                    i--;
                } else {
                    player = setPlayer(
                            setPoints(player.getPoints(), platforms.get(step).getCost(), SUBSTRACT),
                            platforms.get(step)
                    );
                    resultSteps.add(player);
                    validator.setPlatformsValidator(platforms.get(step).getIndex());
                }
            } else {
                step -= 2;
                player = setPlayer(
                        setPoints(player.getPoints(), platforms.get(step).getCost(), ADD),
                        platforms.get(step)
                );
                resultSteps.add(player);
                i--;
            }
        }
        return resultSteps;
    }

    /**
     * Set Player object values.
     * @return Player - Player as object.
     */
    private Player setPlayer(int points, Platform platform) {
        return Player.builder()
                .points(points)
                .platform(platform)
                .build();
    }

    /**
     * Set points according to action.
     * @return int - calculated value according to action.
     */
    private int setPoints(int points, int cost, Action action) {
        return switch (action) {
            case ADD -> points + cost;
            case SUBSTRACT -> points - cost;
        };
    }

    enum Action {
        ADD,
        SUBSTRACT
    }
}
