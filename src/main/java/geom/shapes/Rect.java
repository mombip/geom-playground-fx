package geom.shapes;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Rect extends Rectangle {

    public Rect() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Rect(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Rect(double x, double y, double width, double height, Paint fill, Paint stroke) {
        super(x, y, width, height);
        setStroke(stroke);
        getStrokeDashArray().addAll(4.0, 6.0);
        setFill(fill);
    }

    public Rect(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    public Rect(double width, double height) {
        super(width, height);
    }

}
