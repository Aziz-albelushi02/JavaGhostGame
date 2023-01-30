package uk.ac.bradford.ghostgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * The GameEngine class is responsible for managing information about the game,
 * creating levels, the player and ghosts, as well as updating information when
 * a key is pressed while the game is running.
 *
 * @author prtrundl
 */
public class GameEngine {

    /**
     * An enumeration type to represent different types of level that make up a
     * level. Each type has a corresponding image file that is used to draw the
     * right tile to the screen for each tile in a level. Floors and doors are
     * open for ghosts and the player to move into, walls should be impassable
     * for the player, the bank is where the player must deposit captured
     * ghosts, a breach is a point where new ghosts can be added to the level to
     * replace captured ghosts.
     */
    public enum TileType {
        WALL, FLOOR1, FLOOR2, BANK, BREACH, DOOR;
    }

    /**
     * The width of the level, measured in tiles. Changing this may cause the
     * display to draw incorrectly, and as a minimum the size of the GUI would
     * need to be adjusted.
     */
    public static final int LEVEL_WIDTH = 35;

    /**
     * The height of the level, measured in tiles. Changing this may cause the
     * display to draw incorrectly, and as a minimum the size of the GUI would
     * need to be adjusted.
     */
    public static final int LEVEL_HEIGHT = 18;

    /**
     * A random number generator that can be used to include randomised choices
     * in the creation of levels, in choosing places to spawn the player and
     * ghosts, and to randomise movement and damage. Passing an integer (e.g.
     * 123) to the constructor called here will give fixed results - the same
     * numbers will be generated every time - WHICH CAN BE VERY USEFUL FOR
     * TESTING AND BUGFIXING!
     */
    private Random rng = new Random();

    /**
     * The current level number for the game. As the player completes levels the
     * level number should be increased and can be used to increase the
     * difficulty e.g. by creating additional breaches and ghosts with more
     * health etc.
     */
    private int levelNumber = 1;  //current level

    /**
     * The current turn number. Increased by one every turn. Used to control
     * effects that only occur on certain turn numbers.
     */
    private int turnNumber = 1;

    /**
     * The GUI associated with a GameEngine object. This link allows the engine
     * to pass level (level) and entity information to the GUI to be drawn.
     */
    private GameGUI gui;

    /**
     * The 2 dimensional array of level the represent the current level. The
     * size of this array should use the LEVEL_HEIGHT and LEVEL_WIDTH attributes
     * when it is created.
     */
    private TileType[][] level;

    /**
     * An ArrayList of Point objects used to create and track possible locations
     * to place the player and ghosts when a new level is created.
     */
    private ArrayList<Point> spawnLocations;

    /**
     * A Player object that is the current player. This object stores the state
     * information for the player, including energy and the current position
     * (which is a pair of co-ordinates that corresponds to a tile in the
     * current level - see the Entity class for more information on the
     * co-ordinate system used as well as the coursework specification
     * document).
     */
    private Player player;

    /**
     * An array of Ghost objects that represents the ghosts in the current level
     * of the game. Elements in this array should be an object of the type Ghost
     * (meaning that a ghost is active (not defeated) and needs to be drawn or
     * moved) or should be null (which means nothing is drawn or processed for
     * movement). null values in this array are skipped during drawing and
     * movement processing. Ghosts (Ghost objects) that the player defeats can
     * be replaced with the value null in this array which removes them from the
     * game, using syntax such as ghosts[i] = null
     */
    private Ghost[] ghosts;

    /**
     * Constructor that creates a GameEngine object and connects it with a
     * GameGUI object.
     *
     * @param gui The GameGUI object that this engine will pass information to
     * in order to draw levels and entities to the screen.
     */
    public GameEngine(GameGUI gui) {
        this.gui = gui;
    }

    /**
     * Generates a new level. The method builds a 2D array of TileType values
     * that will be used to draw the level to the screen and to add a variety of
     * tiles into each level. Tiles can be floors, walls, banks (to deposit
     * ghosts), doors or breaches (to add new ghosts).
     *
     * @return A 2D array of TileType values representing the tiles in the
     * current level of the game. The size of this array should use the width
     * and height of the game level using the LEVEL_WIDTH and LEVEL_HEIGHT
     * attributes.
     */
    @SuppressWarnings("empty-statement")
    private TileType[][] generateLevel() {
        //YOUR CODE HERE
        TileType[][] Tile;
        Tile = new TileType[LEVEL_WIDTH][LEVEL_HEIGHT];
        for (TileType[] Tilep : Tile) {
            for (int j = 0; j < Tilep.length; j++) {
                Tilep[j] = TileType.FLOOR1;
            }
            for (int i = 0; i < LEVEL_WIDTH; i++) {
                Tile[i][0] = TileType.WALL;
            }

            for (int i = 0; i < LEVEL_WIDTH; i++) {
                Tile[i][LEVEL_HEIGHT - 1] = TileType.WALL;
            }

            for (int i = 0; i < LEVEL_HEIGHT; i++) {
                Tile[LEVEL_WIDTH - 1][i] = TileType.WALL;
            }

            for (int i = 0; i < LEVEL_HEIGHT; i++) {
                Tile[0][i] = TileType.WALL;

            }

            //1st wall  
            for (int i = 0; i < 0; i++) {
                Tile[i][8] = TileType.WALL;
            }

            for (int i = 0; i < 6; i++) {
                Tile[i][8] = TileType.WALL;
            }

            Tile[6][8] = TileType.DOOR;

            for (int i = 7; i < 9; i++) {
                Tile[i][8] = TileType.WALL;
            }

            for (int i = 8; i < 16; i++) {
                Tile[9][i] = TileType.WALL;
            }

            Tile[9][15] = TileType.WALL;

            for (int i = 0; i < 3; i++) {
                Tile[i][15] = TileType.WALL;
            }

            Tile[3][15] = TileType.DOOR;

            for (int i = 4; i < 10; i++) {
                Tile[i][15] = TileType.WALL;
            }

            Tile[4][12] = TileType.BANK;

            //2nd wall
            for (int i = 0; i < 9; i++) {
                Tile[18][i] = TileType.WALL;
            }

            Tile[17][8] = TileType.WALL;
            Tile[16][8] = TileType.WALL;
            Tile[15][8] = TileType.WALL;
            Tile[14][8] = TileType.WALL;
            Tile[13][8] = TileType.WALL;

            //3rd wall
            Tile[30][12] = TileType.WALL;
            Tile[30][13] = TileType.WALL;
            Tile[30][14] = TileType.WALL;
            Tile[30][15] = TileType.WALL;
            Tile[30][16] = TileType.WALL;

            Tile[19][13] = TileType.WALL;

            Tile[19][14] = TileType.DOOR;

            Tile[19][15] = TileType.WALL;
            Tile[19][16] = TileType.WALL;
            Tile[19][12] = TileType.WALL;
            Tile[19][12] = TileType.WALL;
            Tile[20][12] = TileType.WALL;
            Tile[21][12] = TileType.WALL;
            Tile[22][12] = TileType.WALL;
            Tile[23][12] = TileType.WALL;
            Tile[24][12] = TileType.WALL;

            Tile[25][12] = TileType.DOOR;
            Tile[26][12] = TileType.WALL;
            Tile[27][12] = TileType.WALL;
            Tile[28][12] = TileType.WALL;
            Tile[29][12] = TileType.WALL;

        }

        return Tile;        //change this to return the 2D arrayof TileType values that you create above
    }

    /**
     * Generates spawn points for the player and ghosts. The method processes
     * the level 2D array and finds positions that are suitable for spawning,
     * i.e. empty tiles such as floors. Suitable positions should then be added
     * to the ArrayList as Point objects - Points are a simple kind of object
     * that contain an X and a Y co-ordinate stored using the int primitive
     * type.
     *
     * @return An ArrayList containing Point objects representing suitable X and
     * Y co-ordinates in the current level where the player or ghosts can be
     * added into the level.
     */
    private ArrayList<Point> getSpawns() {
        ArrayList<Point> s = new ArrayList<Point>();
        // YOUR CODE HERE
        for (int x = 0; x < 34; x++) {
            for (int y = 0; y < 18; y++) {
                if (level[x][y] != TileType.BANK
                        && level[x][y] != TileType.WALL
                        && level[x][y] != TileType.BREACH) {
                    Point p = new Point(x, y);
                    s.add(p);
                }
            }
        }
        return s;
    }

    /**
     * Adds ghosts in suitable locations in the current level. The first version
     * of this method should picked fixed positions for ghosts by calling the
     * constructor for the Ghost class and using fixed values for the X and Y
     * position of the Ghost to be added. Ghost objects created this way should
     * then be added into an array of Ghost objects that will be returned by
     * this method. Ghost objects added to this array will then be drawn to the
     * screen using the existing code in the GameGUI class.
     *
     * The second version of this method described in a later task should use
     * the spawnLocations ArrayList to pick suitable positions to add ghosts,
     * removing these positions from the spawns ArrayList as they are used
     * (using the remove() method) to avoid multiple ghosts spawning in the same
     * location and to prevent them from being added on top of banks, breaches
     * etc. The method then creates ghosts by instantiating the Ghost class,
     * setting health and the X and Y position for the ghost using the Point
     * object removed from the spawns ArrayList.
     *
     * @return An array of Ghost objects representing the ghosts for the current
     * level of the game
     */
    @SuppressWarnings("empty-statement")
    private Ghost[] addGhosts() {
        //YOUR CODE HERE
        Ghost ghosts[] = new Ghost[4];
        ghosts[0] = new Ghost(100, 10, 10);
        //ghosts[0] = null;
        level[10][10] = TileType.BREACH;
        ghosts[1] = new Ghost(100, 23, 4);
        //ghosts[1] = null;
        level[23][4] = TileType.BREACH;
        ghosts[2] = new Ghost(100, 25, 14);
        //ghosts[2] = null;
        level[11][15] = TileType.BREACH;
        ghosts[3] = new Ghost(100, 26, 6);
        //ghosts[3] = null;
        level[5][3] = TileType.BREACH;
        return ghosts;        //change this to return an array of ghost objects
    }

    /**
     * Creates a Player object in the game. The method instantiates the Player
     * class and assigns values for the energy and position.
     *
     * The first version of this method should use fixed a fixed position for
     * the player to start, by setting fixed X and Y values when calling the
     * constructor in the Player class.
     *
     * The second version of this method should use the spawns ArrayList to
     * select a suitable location to spawn the player and removes the Point from
     * the spawns ArrayList. This will prevent the Player from being added to
     * the game inside a wall, bank or breach for example.
     *
     * @return A Player object representing the player in the game
     */
    private Player createPlayer() {

        //YOUR CODE HERE
        int y, z;

        y = 23;
        z = 16;
        player = new Player(100, y, z);

        return player;        //change this to return a Player object

    }

    /**
     * Handles the movement of the player when attempting to move left in the
     * game. This method is already called by the GameInputHandler class when
     * the user has pressed the left arrow key on the keyboard. The method
     * should check whether the tile to the left of the player is clear for
     * movement and, if it is, update the player object's X and Y attribute
     * values with the new position. If the tile to the left of the player is
     * not empty the method will not update the player position, but may make
     * other changes to the game, such as damaging a ghost in the tile to the
     * left, depositing a ghost, refilling energy etc.
     */
    public void movePlayerLeft() {
        // player.setPosition(player.getX() - 1, player.getY());
        if ((level[player.getX() - 1][player.getY()] != TileType.WALL)
                && (level[player.getX() - 1][player.getY()] != TileType.BREACH)) {
            player.setPosition(player.getX() - 1, player.getY());

            if (level[player.getX()][player.getY()] == TileType.BANK) {
                player.changeEnergy(player.getMaxEnergy());
                player.setPosition(player.getX() - 1, player.getY());
                player.depositGhost();
            }
            //seal the breach by carring capture ghost to breach
            if (level[player.getX() - 1][player.getY()] == TileType.BREACH
                    && player.getCarryingGhost()) {
                player.depositGhost();
                level[player.getX() - 1][player.getY()] = TileType.FLOOR2;
            }

            for (int i = 0; i < ghosts.length; i++) {

                if (ghosts[i] != null && player.getX() == ghosts[i].getX()
                        && player.getY() == ghosts[i].getY()) {
                    hitGhost(ghosts[i]);
                }
            }
        }
    }

    /**
     * Handles the movement of the player when attempting to move right in the
     * game. This method is already called by the GameInputHandler class when
     * the user has pressed the right arrow key on the keyboard. The method
     * should check whether the tile to the right of the player is clear for
     * movement and, if it is, update the player object's X and Y attribute
     * values with the new position. If the tile to the right of the player is
     * not empty the method will not update the player position, but may make
     * other changes to the game, such as damaging a ghost in the tile to the
     * right, depositing a ghost, refilling energy etc.
     */
    public void movePlayerRight() {
        //player.setPosition(player.getX() + 1, player.getY());
        if ((level[player.getX() + 1][player.getY()] != TileType.WALL
                && level[player.getX() + 1][player.getY()] != TileType.BREACH)) {
            player.setPosition(player.getX() + 1, player.getY());

            if (level[player.getX()][player.getY()] == TileType.BANK) {
                player.changeEnergy(player.getMaxEnergy());
                player.setPosition(player.getX() + 1, player.getY());
                player.depositGhost();
            }

            //seal the breach by carring capture ghost to breach
            if (level[player.getX() + 1][player.getY()] == TileType.BREACH
                    && player.getCarryingGhost()) {
                player.depositGhost();
                level[player.getX() + 1][player.getY()] = TileType.FLOOR2;
            }

            for (int i = 0; i < ghosts.length; i++) {

                if (ghosts[i] != null && player.getX() == ghosts[i].getX()
                        && player.getY() == ghosts[i].
                        getY()) {
                    hitGhost(ghosts[i]);
                }
            }
        }
    }

    /**
     * Handles the movement of the player when attempting to move up in the
     * game. This method is already called by the GameInputHandler class when
     * the user has pressed the up arrow key on the keyboard. The method should
     * check whether the tile above the player is clear for movement and, if it
     * is, update the player object's X and Y attribute values with the new
     * position. If the tile above the player is not empty the method will not
     * update the player position, but may make other changes to the game, such
     * as damaging a ghost in the tile above, depositing a ghost, refilling
     * energy etc.
     */
    public void movePlayerUp() {

        if ((level[player.getX()][player.getY() - 1] != TileType.WALL
                && level[player.getX()][player.getY() - 1] != TileType.BREACH)) {
            player.setPosition(player.getX(), player.getY() - 1);

            if (level[player.getX()][player.getY()] == TileType.BANK) {
                player.changeEnergy(player.getMaxEnergy());
                player.setPosition(player.getX(), player.getY() - 1);
                player.depositGhost();
            }

            if (level[player.getX()][player.getY() - 1] == TileType.BREACH
                    && player.getCarryingGhost()) {
                player.depositGhost();
                level[player.getX()][player.getY() - 1] = TileType.FLOOR2;
            }

            for (int i = 0; i < ghosts.length; i++) {
                if (ghosts[i] != null && player.getX() == ghosts[i].getX()
                        && player.getY() == ghosts[i].
                        getY()) {
                    hitGhost(ghosts[i]);
                }
            }
        }
    }

    /**
     * Handles the movement of the player when attempting to move down in the
     * game. This method is already called by the GameInputHandler class when
     * the user has pressed the down arrow key on the keyboard. The method
     * should check whether the tile below the player is clear for movement and,
     * if it is, update the player object's X and Y attribute values with the
     * new position. If the tile below the player is not empty the method will
     * not update the player position, but may make other changes to the game,
     * such as damaging a ghost in the tile below, depositing a ghost, refilling
     * energy etc.
     */
    public void movePlayerDown() {
        if ((level[player.getX()][player.getY() + 1] != TileType.WALL
                && level[player.getX()][player.getY() + 1] != TileType.BREACH)) {
            player.setPosition(player.getX(), player.getY() + 1);

            if (level[player.getX()][player.getY()] == TileType.BANK) {
                player.changeEnergy(player.getMaxEnergy());
                player.setPosition(player.getX(), player.getY() + 1);
                player.depositGhost();
            }

            if (level[player.getX()][player.getY() + 1] == TileType.BREACH
                    && player.getCarryingGhost()) {
                player.depositGhost();
                level[player.getX()][player.getY() + 1] = TileType.FLOOR2;
            }

            for (int i = 0; i < ghosts.length; i++) {
                if (ghosts[i] != null && player.getX() == ghosts[i].getX()
                        && player.getY() == ghosts[i].
                        getY()) {
                    hitGhost(ghosts[i]);

                }
            }
        }
    }

    /**
     * Reduces a ghost's health in response to the player attempting to move
     * into the same square as the ghost (attacking the ghost).
     *
     * @param g The Ghost object corresponding to the ghost in the game that the
     * player just attempted to move into the same tile as.
     */
    private void hitGhost(Ghost g) {
        if (player.getEnergy() > 0) {
            player.changeEnergy(-10);
            g.changeHealth(-20);
            System.out.println("hit");
        }
        if (g.getHealth() <= 0) {
            player.captureGhost();
        }
        cleanDefeatedGhosts();
    }

    /**
     * Moves all ghosts on the current level. The method iterates over all
     * elements of the ghosts array (using a for loop) and checks if each one is
     * null (using an if statement inside that for loop). For every element of
     * the array that is NOT null, this method calls the moveGhost method and
     * passes it the current array element.
     */
    private void moveGhosts() {
        for (Ghost ghost : ghosts) {
            moveGhost(ghost);
        }
    }

    /**
     * Moves a specific ghost in the game. The method updates the X and Y
     * attributes of the Ghost object passed to the method to set its new
     * position.
     *
     * @param g The Ghost that needs to be moved
     */
    private void moveGhost(Ghost g) {
        //decide if the ghost is near the player in less than 3 steps in x and y 
        boolean is_player_near = false;
        if (g != null && Math.abs(player.getX() - g.getX()) < 3
                && Math.abs(player.getY() - g.getY()) < 3) {
            is_player_near = true;
        }

        if (Math.random() < 0.4) {
            if ((g != null) && (g.getY() + 1 < 16)
                    && (level[g.getX()][g.getY() + 1] != TileType.WALL)) {
                if ((!is_player_near) || (is_player_near && level[g.getX()][g.getY() + 1] != TileType.DOOR)) {
                    g.yPos++;
                } else if ((g.getY() > 0) && (level[g.getX()][g.getY() - 1] != TileType.WALL)) {
                    if ((!is_player_near) || (is_player_near && level[g.getX()][g.getY() - 1] != TileType.DOOR)) {
                        g.yPos--;
                    }
                }
            }
        }
        if (Math.random() < 0.4) {
            if ((g != null) && (g.getX() + 1 < 32) && (level[g.getX() + 1][g.getY()] != TileType.WALL)) {
                if ((!is_player_near) || (is_player_near && level[g.getX() + 1][g.getY()] != TileType.DOOR)) {
                    g.xPos++;
                }
            }
        } else if (g != null && (g.getX() > 0) && (level[g.getX() - 1][g.getY()] != TileType.WALL)) {
            if ((!is_player_near) || (is_player_near && level[g.getX() - 1][g.getY()] != TileType.DOOR)) {
                g.xPos--;
            }
        }

    }

    /**
     * Processes the ghosts array to find any Ghost in the array with 0 or less
     * health. Any Ghost in the array with 0 or less health should be set to
     * null; when drawing or moving ghosts the null elements in the ghosts array
     * are skipped.
     */
    private void cleanDefeatedGhosts() {

        for (int i = 0; i < ghosts.length; i++) {
            if (ghosts[i] != null && ghosts[i].getHealth() <= 0) {
                ghosts[i] = null;
            }
        }

        boolean empty = true;
        for (Ghost ghost : ghosts) {
            if (ghost != null) {
                empty = false;
            }
        }

        int add_ghost = 0;
        if (empty && player.getCarryingGhost()) {
            for (int i = 0; i < 34; i++) {
                for (int j = 0; j < 18; j++) {
                    if (level[i][j] == TileType.BREACH) {
                        add_ghost++;
                        ghosts[add_ghost] = new Ghost(100, i, j);
                        level[i][j] = TileType.FLOOR1;
                    }
                }
            }

        }

    }

    /**
     * This method is called when the number of ghosts in the level is zero and
     * the player is also not currently carrying a captured ghost, meaning that
     * the player has captured all ghosts and deposited them all, "completing"
     * the level. This method is similar to the startGame method and uses SOME
     * identical code.
     *
     * This method should increase the current level number, create a new level
     * by calling the generateLevel method and setting the level attribute using
     * the returned 2D array, add new Ghosts, and finally place the player in
     * the level.
     *
     * A second version of this method in a later task should also find suitable
     * positions to add ghosts and the player using the spawnLocations ArrayList
     * and code in the getSpawns method.
     */
    private void nextLevel() {
        levelNumber++;
        System.out.println(levelNumber);
        level = generateLevel();
        placePlayer();
        ghosts = addGhosts();
        spawnLocations = getSpawns();

    }

    /**
     * The first version of this method should place the player in the game
     * level by setting new, fixed X and Y values for the player object in this
     * class.
     *
     * The second version of this method in a later task should place the player
     * in a game level by choosing a position from the spawnLocations ArrayList,
     * removing the spawn position as it is used. The method sets the players
     * position in the level by calling its setPosition method with the X and Y
     * values of the Point taken from the spawnLocations ArrayList.
     */
    private void placePlayer() {
        player.changeEnergy(player.getEnergy());
        player.setPosition(10, 10);
    }

    /**
     * Performs a single turn of the game when the user presses a key on the
     * keyboard. The method cleans defeated ghosts and moves any undefeated
     * ghosts. Finally it requests the GUI to redraw the game level by passing
     * it the level, player and ghosts objects for the current level.
     *
     * A second version of this method in a later task will also check if all
     * ghosts in the current level have been captured and deposited in a bank,
     * and if they have, will call the nextLevel() method to generate a new,
     * harder level.
     */
    public void doTurn() {
        cleanDefeatedGhosts();
        moveGhosts();
        gui.updateDisplay(level, player, ghosts);

        Boolean next = true;
        for (Ghost g : ghosts) {
            if (g != null) {
                next = false;
            }
        }
        if (next) {
            nextLevel();
        }
    }

    /**
     * Starts a game. This method generates a level, finds spawn positions in
     * the level, adds ghosts and the player and then requests the GUI to update
     * the level on screen using the information on level, player and ghosts.
     */
    public void startGame() {
        level = generateLevel();
        spawnLocations = getSpawns();
        ghosts = addGhosts();
        player = createPlayer();
        gui.updateDisplay(level, player, ghosts);
    }
}
