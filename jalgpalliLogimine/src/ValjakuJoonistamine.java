import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Draws the soccer filed on the screen and tracks ball movement.
 * Created by ovekangur on 08/12/16.
 */
public class ValjakuJoonistamine {

    public static void newScene(Stage stage, ObjectGame manguInfo) {

        int correctionX = 100;

        ValjakuElemendid elemendid = new ValjakuElemendid();

        // Creates all the elements on the soccer filed
        ArrayList<Label> labels = new ArrayList<Label>();
        labels = elemendid.myLables(correctionX, "Mäng algas", manguInfo);

        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons = elemendid.myButtons(0,0, stage, manguInfo);

        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        rectangles = elemendid.myRectangles(correctionX);

        ArrayList<Circle> circles = new ArrayList<Circle>();
        circles = elemendid.myCircles(correctionX, 0, 0,0, 1);

        // Adds elements to Pane
        Pane paigutus = new Pane();

        paigutus.getChildren().addAll(buttons);
        paigutus.getChildren().addAll(labels);
        paigutus.getChildren().addAll(rectangles);
        paigutus.getChildren().addAll(circles);

        Scene scene = new Scene(paigutus, 1450, 880);
        stage.setScene(scene);
        stage.show();

        // Some code from http://java-buddy.blogspot.com.ee/2015/02/javafx-detect-right-click-on-mouse.html
        // Tracks ball movement on the filed, logs events and refreshes the screen
        paigutus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();

                // Checks if the click is on the field and adjusts if necessary
                int pallX = Funktsioonid.kontrolliX((int) event.getSceneX());
                int pallY = Funktsioonid.kontrolliY((int) event.getSceneY());

                if(button==MouseButton.PRIMARY){
                    // Creates event log and adds it log list refreshes the screen
                    String tekst2 = manguInfo.strAeg() + "_" + manguInfo.koduvoistkond + "_pall kohal_" + (pallX-200)/10 + ":" + (pallY-100)/10;
                    manguInfo.list.add(tekst2);
                    newSceneWithBall(pallX, pallY, stage, tekst2, 1, manguInfo);
                }
                else if(button==MouseButton.SECONDARY) {
                    // Creates event log and adds it log list refreshes the screen
                    String tekst2 = manguInfo.strAeg() + "_" + manguInfo.koduvoistkond + "_pall kohal_" + (pallX-200)/10 + ":" + (pallY-100)/10;
                    manguInfo.list.add(tekst2);
                    newSceneWithBall(pallX, pallY, stage, tekst2, -1, manguInfo);
                }
            }
        });
    }

    public static void newSceneWithBall(int pallX, int pallY,  Stage stage, String tekst, int homeAway, ObjectGame manguInfo) {

        int correctionX = 100;

        ValjakuElemendid elemendid = new ValjakuElemendid();

        // Creates all the elements on the soccer filed
        ArrayList<Label> labels = new ArrayList<Label>();
        labels = elemendid.myLables(correctionX, tekst, manguInfo);

        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons = elemendid.myButtons(pallX, pallY, stage, manguInfo);

        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        rectangles = elemendid.myRectangles(correctionX);

        ArrayList<Circle> circles = new ArrayList<Circle>();
        circles = elemendid.myCircles(correctionX, 1, pallX, pallY, homeAway);

        // Adds elements to Pane
        Pane paigutus2 = new Pane();

        paigutus2.getChildren().addAll(buttons);
        paigutus2.getChildren().addAll(labels);
        paigutus2.getChildren().addAll(rectangles);
        paigutus2.getChildren().addAll(circles);

        Scene stseen2 = new Scene(paigutus2, 1450, 880);

        stage.setScene(stseen2);
        stage.show();

        // Some code from http://java-buddy.blogspot.com.ee/2015/02/javafx-detect-right-click-on-mouse.html
        // Tracks ball movement on the filed, logs events and refreshes the screen
        paigutus2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                MouseButton button = event.getButton();
                // Checks if the click is on the field and adjusts if necessary
                int uusPallX = Funktsioonid.kontrolliX((int) event.getSceneX());
                int uusPallY = Funktsioonid.kontrolliY((int) event.getSceneY());

                if(button==MouseButton.PRIMARY){
                    String tekst1 = manguInfo.strAeg() + "_" + manguInfo.koduvoistkond + "_pall kohal_" + (uusPallX-200)/10 + ":" + (uusPallY-100)/10;
                    manguInfo.list.add(tekst1);
                    newSceneWithBall(uusPallX, uusPallY, stage, tekst1, 1, manguInfo);
                }
                else if(button==MouseButton.SECONDARY) {
                    String tekst1 = manguInfo.strAeg() + "_" + manguInfo.voorsilVoistkond + "_pall kohal_" + (uusPallX-200)/10 + ":" + (uusPallY-100)/10;
                    manguInfo.list.add(tekst1);
                    newSceneWithBall(uusPallX, uusPallY, stage, tekst1, -1, manguInfo);
                }
            }
        });

    }
}
