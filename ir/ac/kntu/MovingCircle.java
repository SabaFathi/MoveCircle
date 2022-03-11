package ir.ac.kntu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author Saba
 */
public class MovingCircle extends Application {
    KeyCode lastPressedKey;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Circle circle = new Circle(100, 100, 40);
        pane.getChildren().add(circle);

        Thread thread = new Thread(
                ()->{
                    final int step = 5;
                    while ( primaryStage.isShowing() ) {
                        try {
                            Thread.sleep(30);
                            if( lastPressedKey==KeyCode.UP ){
                                Platform.runLater(() -> {
                                    circle.setCenterY(circle.getCenterY() - step);
                                });
                            }else if( lastPressedKey==KeyCode.DOWN ){
                                Platform.runLater(() -> {
                                    circle.setCenterY(circle.getCenterY() + step);
                                });
                            }else if( lastPressedKey==KeyCode.LEFT ){
                                Platform.runLater(() -> {
                                    circle.setCenterX(circle.getCenterX() - step);
                                });
                            }else if( lastPressedKey==KeyCode.RIGHT ){
                                Platform.runLater(() -> {
                                    circle.setCenterX(circle.getCenterX() + step);
                                });
                            }

                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        Scene scene = new Scene(pane, 600, 600);
        scene.setOnKeyPressed(event -> {
            System.out.println("event = " + event);
            lastPressedKey = event.getCode();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        
        thread.start();
    }
}
