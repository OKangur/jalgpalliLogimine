import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates all field elements Buttons, Labels, Rectangles and Circles using for loops.
 * Records the events if buttons are clicked.
 * Created by ovekangur on 08/12/16.
 */

public class ValjakuElemendid {

    // Some code from http://www.dreamincode.net/forums/topic/138254-making-buttons-with-a-loop/
    // Creates buttons using for loop and records the events if buttons are clicked.
    public ArrayList<Button> myButtons(int pallX, int pallY, Stage stage, ObjectGame manguInfo)    {

        String[] buttonNames = {"Varav", "Vahetus", "Karistuslook", "Nurgalook", "Kuljeaut",
                "Suluseis", "Varavalook", "Varavavahi_pall", "Look_varavale",
                "Look_mooda", "Vigastus", "Kollane_kaart", "Punane_kaart"};

        ArrayList<Button> buttons = new ArrayList<Button>();

        // Creates home and away buttons with two loops
        for (int i = 0; buttonNames.length > i; i++ ) {
            buttons.add(new Button("Button_home :: " + buttonNames[i]));
        }
        for (int i = 0; buttonNames.length > i; i++ ) {
            buttons.add(new Button("Button_away :: " + buttonNames[i]));
        }

        // Loops over all the buttons, modifies their properties and ads click events
        for (int i = 0; i < buttons.size(); i++ ) {

            buttons.get(i).setFont(Font.font("Arial", 17));
            buttons.get(i).setPrefSize(180, 43);

            // If its a home team button
            if (i < buttons.size() / 2) {

                buttons.get(i).setLayoutX(10);
                buttons.get(i).setText(buttonNames[i]);
                buttons.get(i).setLayoutY(100 + i * 53);

                int iii = i;
                final String logItem = manguInfo.strAeg() + "_" + manguInfo.koduvoistkond + "_" +
                        buttonNames[i] + "_" + (pallX-200)/10 + ":" + (pallY-100)/10;

                buttons.get(i).setOnMouseClicked(e -> {
                    // Adds on to home goals if goal button is clicked
                    if (iii==0) {
                        manguInfo.koduVaravad++;
                    }
                    // Adds event record to records list and creates new scene
                    manguInfo.list.add(logItem);
                    ValjakuJoonistamine.newSceneWithBall(pallX, pallY, stage, logItem, 1, manguInfo);
                });
            }
            // Away team buttons
            else {
                buttons.get(i).setLayoutX(1260);
                buttons.get(i).setText(buttonNames[i - buttons.size() / 2]);
                buttons.get(i).setLayoutY(100 + (i - buttons.size() / 2) * 53);

                int iii = i;
                final String logItem = manguInfo.strAeg() + "_" + manguInfo.voorsilVoistkond + "_" +
                        buttonNames[i - buttons.size() / 2] + "_"+ (pallX/10-20) + ":" + (pallY/10-10);

                buttons.get(i).setOnMouseClicked(e -> {
                    // Adds on to away goals if goal button is clicked
                    if ((iii - buttons.size() / 2)==0) {
                        manguInfo.voorsilVaravad++;
                    }
                    // Adds event record to records list and creates new scene
                    manguInfo.list.add(logItem);
                    ValjakuJoonistamine.newSceneWithBall(pallX, pallY, stage, logItem, -1, manguInfo);
                });
            }

        }

        // Finish button
        buttons.add(new Button("Lõpeta"));
        buttons.get(buttons.size()-1).setText("Lõpeta");
        buttons.get(buttons.size()-1).setPrefSize(180, 43);
        buttons.get(buttons.size()-1).setLayoutX(20);
        buttons.get(buttons.size()-1).setLayoutY(20);
        buttons.get(buttons.size()-1).setFont(Font.font("Arial", 17));
        // When clicked writes the game log and closes the stage
        buttons.get(buttons.size()-1).setOnMouseClicked(e -> {
            try {
                manguInfo.writer();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            stage.close();
        });

        return(buttons);
    }
    // Creates labels using for loop
    public ArrayList<Label> myLables(int correctionX, String tekst, ObjectGame manguInfo)    {

        String[] lableNames = { manguInfo.koduvoistkond, manguInfo.voorsilVoistkond, manguInfo.seis(), tekst};

        ArrayList<Label> labels = new ArrayList<Label>();

        // Label[0] - home team name, Label[1] - away team name, Label[2] - score, Label[3] - logItem
        for (int i = 0; lableNames.length > i; i++ ) {
            labels.add(new Label(lableNames[i]));
        }

        for ( int i = 0; i < labels.size(); i++ ) {

            labels.get(i).setFont(Font.font("Arial", 30));
            labels.get(i).setLayoutY(0);
            labels.get(i).setAlignment(Pos.CENTER);

            if (i < 2) {
                labels.get(i).setPrefSize(450, 100);
            }
            if (i == 0) {
                labels.get(i).setTextFill(Color.BLUE);
                labels.get(i).setLayoutX(100 + correctionX);
            }
            if (i == 1) {
                labels.get(i).setTextFill(Color.RED);
                labels.get(i).setLayoutX(700 + correctionX);
            }
            if (i==2) {
                labels.get(i).setPrefSize(150, 100);
                labels.get(i).setLayoutX(550 + correctionX);
            }
            if (i==3) {
                labels.get(i).setPrefSize(750, 100);
                labels.get(i).setLayoutX(250 + correctionX);
                labels.get(i).setLayoutY(770);
            }

        }
        return(labels);
    }
    // Creates rectangles - lines and goals on the field
    public ArrayList<Rectangle> myRectangles(int correctionX)    {

        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        // Field
        rectangles.add(new Rectangle(100 + correctionX, 100, 1050, 680));
        // Penalty boxes
        rectangles.add(new Rectangle(623 + correctionX, 100, 4, 680));
        rectangles.add(new Rectangle(100 + correctionX, (440-160), 160, 4));
        rectangles.add(new Rectangle(100 + correctionX, (440+160), 160, 4));
        rectangles.add(new Rectangle(256 + correctionX, (440-160), 4, 320));
        rectangles.add(new Rectangle((1150 - 160 + correctionX), (440-160), 160, 4));
        rectangles.add(new Rectangle((1150 - 160 + correctionX), (440+160), 160, 4));
        rectangles.add(new Rectangle((1150 - 160 + correctionX), (440-160), 4, 320));
        // Goals
        rectangles.add(new Rectangle(96 + correctionX, (440-36), 4, 72));
        rectangles.add(new Rectangle(1150 + correctionX, (440-36), 4, 72));

        // Colors
        for ( int i = 0; i < rectangles.size(); i++ ) {
            rectangles.get(i).setFill(Color.WHITE);
            if (i==0) {
                rectangles.get(i).setFill(Color.GREEN);
            }
            if (i==8 || i==9) {
                rectangles.get(i).setFill(Color.BLACK);
            }
        }
        return(rectangles);

    }
    // Creates circles using for loop
    public ArrayList<Circle> myCircles(int correctionX, int count, int pallX, int pallY, int homeAway)    {

        ArrayList<Circle> circles = new ArrayList<Circle>();
        // Center circle
        circles.add(new Circle(625 + correctionX, 440,93));
        circles.add(new Circle(625 + correctionX, 440,89));

        // Where's the ball
        if (count > 0) {
            circles.add(new Circle(pallX, pallY,40));
            circles.add(new Circle(pallX, pallY,10));
        }
        // Colors Circles
        for (int i = 0; i < circles.size(); i++ ) {
            // Centre circle color
            circles.get(i).setFill(Color.WHITE);
            if (i==1) {
                circles.get(i).setFill(Color.GREEN);
            }
            // Away ball color
            if (homeAway==-1 && i > 1) {
                if (i==2) {
                    circles.get(i).setFill(Color.ORANGE);
                }
                else {
                    circles.get(i).setFill(Color.RED);
                }
            }
            // Home ball color
            if (homeAway==1 && i>1) {
                if (i==2) {
                    circles.get(i).setFill(Color.LIGHTBLUE);
                }
                else {
                    circles.get(i).setFill(Color.BLUE);
                }
            }
        }
        return(circles);

    }

}
