package geom.tranform;

import java.io.FileNotFoundException;

import geom.shapes.Anchor;
import geom.shapes.Rect;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Transform_app extends Application {

    Rect rect;

    @Override 
    public void start(Stage primaryStage) throws FileNotFoundException {
        Pane root = new Pane();
        //        Group root = new Group(pane);

        root.setStyle("-fx-background-color: #" + String.format("%02x%02x%02x", 80, 80, 80));
        //        pane.setStyle("-fx-background-color: transparent;");
       
        rect = new Rect(40, 40, 200, 200, Color.TRANSPARENT, Color.LIGHTGREEN);
        root.setOnMousePressed(e -> {
            root.requestFocus();
            root.getChildren().add(new Anchor(Color.PALEGREEN, e.getSceneX(), e.getSceneY(), 5.));
            root.getChildren().add(rect);
            root.getChildren()
                    .add(new Rect(e.getSceneX(), e.getSceneY(), 200, 200, Color.TRANSPARENT, Color.LIGHTGREEN));

        });
        root.setOnMouseDragged(e -> {

            //creating the rotation transformation 
            Rotate rotate = new Rotate();

            //Setting the angle for the rotation 
            rotate.setAngle(20);

            //Setting pivot points for the rotation 
            rotate.setPivotX(e.getX());
            rotate.setPivotY(e.getY());

            //            //Creating the scale transformation 
            //            Scale scale = new Scale();
            //
            //            //Setting the dimensions for the transformation 
            //            scale.setX(1.5);
            //            scale.setY(1.5);
            //
            //            //Setting the pivot point for the transformation 
            //            scale.setPivotX(300);
            //            scale.setPivotY(135);

            //            //Creating the translation transformation 
            //            Translate translate = new Translate();
            //
            //            //Setting the X,Y,Z coordinates to apply the translation 
            //            translate.setX(250);
            //            translate.setY(0);
            //            translate.setZ(0);

            //Adding all the transformations to the rectangle 
            rect.getTransforms().addAll(rotate);
        });

        root.setOnMouseReleased(e -> {

        });
        
        root.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE) {
                root.getChildren().clear();
            }
        });

        Scene scene = new Scene(root,1600, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	
    public static void run(String[] args) {
        launch(args);
    }

}