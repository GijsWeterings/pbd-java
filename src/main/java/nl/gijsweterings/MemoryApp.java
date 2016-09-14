package nl.gijsweterings;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by gijs on 13-9-16.
 */
public class MemoryApp extends Application{

    private static final int NUMBER_OF_PAIRS = 32;
    private static final int TILES_PER_ROW = 8;

    private static final int TILE_SIZE = 50;

    private Tile selectedTile;

    @Override
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My memory game");
        primaryStage.setScene(new Scene(createRootPane()));
        primaryStage.show();


    }

    /**
     * Generates a root Pane object that draws a grid of Tiles.
     * This pane will serve as the playing field of the game
     * @return a configured root pane
     */
    protected Parent createRootPane() {
        Pane root = new Pane();
        root.setPrefSize(400, 400);
        root.getChildren().addAll(createTiles());

        root.setOnMouseClicked(getMouseEventEventHandler());
        return root;
    }


    /**
     * Create a collection of Tile objects that can be used in a game of memory
     *
     * @return
     */
    protected ArrayList<Tile> createTiles() {
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        for (int i = 0; i < NUMBER_OF_PAIRS; i++) {
            tiles.add(new Tile(i));
            tiles.add(new Tile(i));
        }

        Collections.shuffle(tiles);

        for (int j = 0; j < tiles.size(); j++) {
            Tile tile = tiles.get(j);
            tile.setTranslateX(50 * (j % TILES_PER_ROW));
            tile.setTranslateY(50 * (j / TILES_PER_ROW));
        }

        return tiles;
    }

    /**
     * Create a MouseEventEventhandler, which controls the game's mechanics.
     * @return an Eventhandler that can interact with the board.
     */
    protected EventHandler<MouseEvent> getMouseEventEventHandler() {
        return event -> {
            if (event.getTarget() instanceof Tile) {
                Tile clickedTile = (Tile) event.getTarget();
                if (clickedTile.isLocked() || clickedTile.equals(this.selectedTile)) {
                    return;
                }
                clickedTile.open();

                if(this.selectedTile == null) {
                    this.selectedTile = clickedTile;
                }
                else {
                    if(this.selectedTile.isSameValue(clickedTile)){
                        this.selectedTile.lock();
                        clickedTile.lock();
                    }
                    else {
                        this.selectedTile.close();
                        clickedTile.close();
                    }
                    this.selectedTile = null;
                }

            }
        };
    }

}
