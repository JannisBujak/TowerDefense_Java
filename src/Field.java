import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Field extends Rectangle {

    Field(double x, double y, double width, double height, Color color){
        super(width, height, color);
        setTranslateX(x);
        setTranslateY(y);
    }
    public void update(){

    }
}
