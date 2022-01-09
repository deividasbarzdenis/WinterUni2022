package com.nortal.platformer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GameService {

    private final Path path = (Paths.get("./java-platformer/src/main/resources")).toAbsolutePath();
    private boolean[] steps;
    private Platform activePlatform;
    private Integer points = 500;

    /**
     * Main game menu.
     *  Take platforms list, players statistics file, players file.
     *  Prints menu text to console according to players choice.
     *  Writes player statistics and players to .csv file.
     *  Print messages to console if wrong choice was made.
     *
     * @return void.
     */
    void loginOrRegisterAndPlay(List<Platform> platforms, String playersFile, String statisticsFile) throws IOException {
        stage1:
        while (true) {
            Menu.getGameEnterMenu();
            System.out.println("Enter your choose:");
            Scanner input = new Scanner(System.in);
            switch (input.next()) {
                case "l":
                    System.out.println("ENTER NAME:");
                    String name = input.next();
                    Player playerName = getPlayerByName(name, playersFile);
                    if (playerName != null) {
                        stage2:
                        while (true) {
                            Menu.getMenu();
                            System.out.println("Enter your choose:");
                            switch (input.next()) {
                                case "p":
                                    //play game
                                    activePlatform = platforms.get(0);
                                    steps = new boolean[5];
                                    stage3:
                                    while (true) {
                                        Menu.getPlatformInfo(platforms);
                                        steps[0] = true;
                                        System.out.println("You are at platform " + activePlatform.getIndex());
                                        System.out.println("You have " + points + " points");
                                        System.out.println("Do yuo want to jump to another platform?\n" +
                                                "Enter platform index:");
                                        String choice = input.next();
                                        if (playGame(platforms, choice, playerName, statisticsFile)){
                                            break stage3;
                                        }
                                    }
                                    break;
                                case "w":
                                    //player statistics
                                    System.out.println("name, points, stage");
                                    getPlayerStatistics(playerName.getName(), statisticsFile)
                                            .forEach(System.out::print);
                                    break;
                                case "a":
                                    //game settings
                                    Menu.getGameRules();
                                    break;
                                case "q":
                                    break stage2;
                                default:
                                    System.out.println("Wrong choose, please enter right one");
                            }
                        }
                    } else {
                        System.out.println("Player " + name + " do not exists, please register");
                    }
                    break;
                case "r":
                    registerPlayer(input, playersFile);
                    break;
                case "e":
                    break stage1;
                default:
                    System.out.println("Wrong choose, please enter right one");

            }
        }
    }

    /**
     *  Main game platform.
     *  Take platforms list, player choice, player name, players statistics file name
     *  Prints messages to console according to players choice.
     *  Writes player statistics to .csv file.
     *  If game over or player choice to leave the game returns boolean.
     *
     * @return boolean - false.
     */
    private boolean playGame(List<Platform> platforms, String choice, Player playerName, String statisticsFile) {
        switch (choice) {
            case "0":
                if (activePlatform.getIndex() > 1) {
                    System.out.println("You cannot to jump to stage " + choice);
                    break;
                }
                if (points >= 100 && activePlatform.getIndex() == 1) {
                    points += platforms.get(0).getCost();
                    activePlatform = platforms.get(0);
                } else {
                    if (points < 100) {
                        System.out.println("You do not have enough points");
                        System.out.println("Game is over");
                        return true;
                    } else if (activePlatform.getIndex() == 0 ||
                            activePlatform.getIndex() >= 2) {
                        System.out.println("You cannot to jump to stage " + 0);
                    }
                }
                break;
            case "1":
                if (activePlatform.getIndex() == 2 || steps[1]) {
                    points += platforms.get(1).getCost();
                    activePlatform = platforms.get(1);
                    System.out.println("You jumped to platform " + choice);
                }
                if (points >= 200 && activePlatform.getIndex() == 0) {
                    points -= platforms.get(1).getCost();
                    activePlatform = platforms.get(1);
                    steps[1] = true;
                    System.out.println("You jumped to platform " + choice);
                } else {
                    if (points < 200) {
                        System.out.println("You do not have enough points");
                        System.out.println("Game is over");
                        break;
                    } else if (activePlatform.getIndex() == 1 ||
                            activePlatform.getIndex() >= 3) {
                        System.out.println("You cannot to jump to stage " + 1);
                    }
                }
                break;
            case "2":
                if (activePlatform.getIndex() == 3 || steps[2] || steps[3]) {
                    points += platforms.get(2).getCost();
                    activePlatform = platforms.get(2);
                    System.out.println("You jumped to platform " + choice);
                }
                if (points >= 400 && activePlatform.getIndex() == 1
                        && !steps[2]) {
                    points -= platforms.get(2).getCost();
                    activePlatform = platforms.get(2);
                    steps[2] = true;
                    System.out.println("You jumped to platform " + choice);
                } else {
                    if (points < 400) {
                        System.out.println("You do not have enough points");
                    } else if (activePlatform.getIndex() == 2 ||
                            activePlatform.getIndex() >= 4 ||
                            activePlatform.getIndex() == 0) {
                        System.out.println("You cannot to jump to stage " + 2);
                    }
                }
                break;
            case "3":
                if (activePlatform.getIndex() == 4 || steps[3] || steps[4]) {
                    points += platforms.get(3).getCost();
                    activePlatform = platforms.get(3);
                    System.out.println("You jumped to platform " + choice);
                }
                if (points >= 900 && activePlatform.getIndex() == 2
                        && !steps[3]) {
                    points -= platforms.get(3).getCost();
                    activePlatform = platforms.get(3);
                    steps[3] = true;
                    System.out.println("You jumped to platform " + choice);
                }
                else {
                    if (points < 900) {
                        System.out.println("You do not have enough points");
                    } else if (activePlatform.getIndex() <= 1) {
                        System.out.println("You cannot to jump to stage " + 3);
                    }
                }
                break;
            case "4":
                if (points >= 100 && activePlatform.getIndex() == 3) {
                    points -= platforms.get(4).getCost();
                    steps[4] = true;
                    System.out.println("You jumped to platform " + choice);
                    System.out.println("You have " + points + " points");
                    System.out.println("YOU WON!!!!");
                    Player player = new Player(playerName.getName(), points, 4);
                    writePlayerStatistics(player, statisticsFile);
                    return true;
                } else {
                    if (points < 100) {
                        System.out.println("You do not have enough points");
                    } else if (activePlatform.getIndex() <= 2) {
                        System.out.println("You cannot to jump to stage " + 4);
                    }
                }
                break;
            case "q":
                Player player = new Player(playerName.getName(), points, activePlatform.getIndex());
                writePlayerStatistics(player, statisticsFile);
                points = 500;
                return true;
            default:
                System.out.println("Wrong choice, please enter right one");
        }
        return false;
    }



    /**
     * Reads platforms from csv file and returns the as list.
     *
     * @return platforms - Platforms as list
     */
    public List<Platform> readPlatforms(String gameFile) throws IOException {
        Path pathPlatform = Path.of(path + "/" + gameFile);
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

    /**
     *  Takes user entered name, file name and prints messages to console if name exist in file.
     *
     * @return void.
     */
    private void registerPlayer(Scanner input, String playersFile) throws IOException {
        System.out.println("Enter the name");
        String registerUser = input.next();
        Player checkIfExist = getPlayerByName(registerUser, playersFile);
        if (checkIfExist == null){
            registerPlayer(registerUser + "\n", playersFile);
        }else{
            System.out.println("Player " + registerUser + " exists, please choose other name");
        }
    }

    /**
     * Reads players statistics from csv file and returns the as list.
     *
     * @return players - Players as list
     */
    private List<Player> getPlayersStatistics(String statisticsFile) throws IOException {
        Path pathPlayers = Path.of(path + "/" + statisticsFile);
        return Files.lines(pathPlayers)
                .skip(1)
                .map(line -> line.split(", "))
                .map(this::getPlayer)
                .collect(Collectors.toList());
    }

    /**
     * Reads players from csv file and returns the as list.
     *
     * @return players - Players as list
     */
    private List<Player> getPlayers(String playersFile) throws IOException {
        Path pathPlayers = Path.of(path + "/" + playersFile);
        return Files.lines(pathPlayers)
                .skip(1)
                .map(Player::new)
                .collect(Collectors.toList());
    }

    /**
     * Takes arguments String[] and returns new Player object.
     *
     * @return player - new Player object.
     */
    private Player getPlayer(String[] arr) {
        return new Player(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
    }

    /**
     * Reads players statistics from csv file according to given name and returns the as list.
     *
     * @return players - Players as list
     */
    public List<Player> getPlayerStatistics(String name, String statisticsFile) throws IOException {
        return getPlayersStatistics(statisticsFile).stream()
                .filter(Objects::nonNull)
                .filter(p -> p.getName().equals(name))
                .collect(Collectors.toList());
    }

    /**
     * Take player statistics and file name and writes to .csv file.
     *
     * @return void
     */
    private void writePlayerStatistics(Player player, String statisticsFile){
        byte[] data = player.toString().getBytes();
        Path writePlayers = Path.of(path + "/" + statisticsFile);
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(writePlayers, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException e){
            System.err.println(e);
        }
    }

    /**
     * Take new player and file name and writes to .csv file.
     *
     * @return void
     */
    private void registerPlayer(String player, String playersFile){
        byte[] data = player.getBytes();
        Path writePlayers = Path.of(path + "/" + playersFile);
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(writePlayers, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException e){
            System.err.println(e);
        }
    }

    /**
     * Reads players from csv file according to given name and returns the Player or null.
     *
     * @return player - Player or null.
     */
    public Player getPlayerByName(String name, String playersFile) throws IOException {
        return getPlayers(playersFile).stream()
                .filter(Objects::nonNull)
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
