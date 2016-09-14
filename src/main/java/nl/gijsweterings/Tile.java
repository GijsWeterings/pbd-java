package nl.gijsweterings;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


/**
 * Created by gijs on 13-9-16.
 */
public class Tile extends StackPane {

    /**
     * The value in the Tile.
     */
    private Integer value;

    /**
     * The javafx label to display the value.
     */
    private Text label;

    /**
     * if true, the Tile can no longer be closed.
     */
    private boolean locked;

    /**
     * The tile dimension in px.
     */
    private static final int TILE_SIZE = 50;

    /**
     * The font size of the label.
     */
    private static final int FONT_SIZE = 25;

    /**
     * The time it takes for a value to fade in/out, in ms.
     */
    private static final int FADE_TIME = 500;

    /**
     * Create a Tile instance, with a given value.
     * @param givenvalue the value given to this Tile.
     */
    public Tile(final int givenvalue) {
        this.value = givenvalue;
        this.locked = false;

        Rectangle border = new Rectangle(TILE_SIZE, TILE_SIZE);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        label = new Text(this.value.toString());
        label.setFont(Font.font(FONT_SIZE));
        label.setOpacity(0.0);

        setAlignment(Pos.CENTER);

        getChildren().addAll(label, border);
    }

    /**
     * If the Tile is not locked, it's "opened" to show the value.
     */
    public final void open() {
        if (locked) {
            return;
        }

        FadeTransition ft =
                new FadeTransition(Duration.millis(FADE_TIME), label);
        ft.setToValue(1.0);
        ft.play();
    }

    /**
     * If th TIle is not locked, it's closed, hiding the value.
     */
    public final void close() {
        if (locked) {
            return;
        }

        FadeTransition ft =
                new FadeTransition(Duration.millis(FADE_TIME), label);
        ft.setDelay(Duration.millis(FADE_TIME));
        ft.setToValue(0.0);
        ft.playFromStart();
    }

    /**
     * Locks the Tile, preventing opening/closing actions.
     */
    public final void lock() {
        locked = true;
    }

    /**
     * Check if the Tile is locked.
     * @return true iff the Tile is locked
     */
    public final boolean isLocked() {
        return locked;
    }

    /**
     * Returns the value set for this Tile.
     * @return the value in this Tile.
     */
    public final Integer getValue() {
        return this.value;
    }

    /**
     * Returns true iff this Tile and other both have the same value.
     * @param other The other tile clicked by the user.
     * @return true iff the values of this and other are the same.
     */
    public final boolean isSameValue(final Tile other) {
        return this.getValue().equals(other.getValue());
    }
}
