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
public class Tile extends StackPane{
    private Integer value;
    private Text label;
    private boolean locked;

    public Tile(int value) {
        this.value = value;
        this.locked = false;

        Rectangle border = new Rectangle(50, 50);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        label = new Text(this.value.toString());
        label.setFont(Font.font(25));
        label.setOpacity(0.0);

        setAlignment(Pos.CENTER);

        getChildren().addAll(label, border);
    }

    public void open() {
        if (locked)
            return;

        FadeTransition ft = new FadeTransition(Duration.millis(500), label);
        ft.setToValue(1.0);
        ft.play();
    }

    public void close() {
        if (locked)
            return;

        FadeTransition ft = new FadeTransition(Duration.millis(500), label);
        ft.setDelay(Duration.millis(500));
        ft.setToValue(0.0);
        ft.playFromStart();
    }

    public void lock() {
        locked = true;
    }

    public boolean isLocked() {
        return locked;
    }

    public Integer getValue() {
        return this.value;
    }

    public boolean isSameValue(Tile other) {
        return this.getValue() == other.getValue();
    }
}
