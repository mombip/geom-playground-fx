package geom.shapes;


import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;


public class Anchor extends Circle {
    public Anchor(Color color, DoubleProperty x, DoubleProperty y, double size) {
        super(x.get(), y.get(), size);
        setupColor(color);
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.INSIDE);

        x.bindBidirectional(centerXProperty());
        y.bindBidirectional(centerYProperty());
        enableDrag();
    }

    public Anchor(Color color, double x, double y, double size) {
        super(x, y, size);
        setupColor(color);
    }

    private void setupColor(Color color) {
        setFill(color.deriveColor(1, 1, 1, 0.8));
    }

    // make a node movable by dragging it around with the mouse.
    private void enableDrag() {
        final Delta dragDelta = new Delta();
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = getCenterX() - mouseEvent.getX();
                dragDelta.y = getCenterY() - mouseEvent.getY();
                getScene().setCursor(Cursor.MOVE);
                mouseEvent.consume();
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getScene().setCursor(Cursor.HAND);
                mouseEvent.consume();
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double newX = mouseEvent.getX() + dragDelta.x;
                if (newX > 0 && newX < getScene().getWidth()) {
                    setCenterX(newX);
                }
                double newY = mouseEvent.getY() + dragDelta.y;
                if (newY > 0 && newY < getScene().getHeight()) {
                    setCenterY(newY);
                }

                mouseEvent.consume();
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.HAND);
                }
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.DEFAULT);
                }
            }
        });
    }

    public Point2D getCenter() {
        return new Point2D(centerXProperty().get(), centerYProperty().get()) ;
    }

    // records relative x and y co-ordinates.
    private class Delta {
        double x, y;
    }
}
