package Objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TDField extends Rectangle {

    public TDField(double x, double y, double xSize, double ySize, Color color) {
        super(xSize, ySize, color);
        setTranslateX(x);
        setTranslateY(y);

    }

    public void setColor(Color color){
        super.setFill(color);
    }
}
