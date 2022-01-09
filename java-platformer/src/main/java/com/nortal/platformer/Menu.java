package com.nortal.platformer;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Menu {

    /**
     * Main game menu.
     *  Print messages to console.
     *
     * @return void.
     */
    public static void getMenu() {
        System.out.println("\nCHOOSE OPTION:\n" +
                "[p] - Play game\n" +
                "[w] - Player statistics\n" +
                "[a] - Settings\n" +
                "[q] - quit\n");
    }

    /**
     *  Game rules/settings menu.
     *  Print messages to console.
     *
     * @return void.
     */
    public static void getGameRules() {
        System.out.println("GAME RULES:\n" +
                "• You start the game from platform ‘0’ with 500 points.\n" +
                "• You jump to platform ‘1’. It is the first jump to that platform, so you’re going to have to pay the cost.\n" +
                "Points: 500 - 200 = 300\n" +
                "• You cannot jump to platform ‘2’ because it costs 400 points, but you only have 300. It is time to go\n" +
                "back. You jump to platform ‘0’. Points: 300 + 100 = 400.\n" +
                "• You jump to platform ‘1’. Points: 400 + 200 = 600\n" +
                "• After taking a detour, you finally have enough points to jump to platform '2', so you jump there.\n" +
                "Points: 600 - 400 = 200\n" +
                "• You jump to platform ‘3’. Points: 200 - 100 = 100");
    }

    /**
     *  Game login/register menu.
     *  Print messages to console.
     *
     * @return void.
     */
    public static void getGameEnterMenu() {
        System.out.println("CHOOSE OPTION:\n" +
                "[l] - Login to game\n" +
                "[r] - Register new player\n" +
                "[e] - Exit from the game");
    }

    /**
     *  Game platforms information than playing the game.
     *  Print messages to console.
     *
     * @return void.
     */
    public static void getPlatformInfo(List<Platform> platforms) {
        System.out.println("\nPLATFORMS INFORMATION:");
        platforms.forEach(System.out::println);
        System.out.println("  [q] - To quit press q\n");
    }
}
