import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Creates first scene where one can enter team names, score and
 * time of the match using Labels and Textfileds. After pushing the Sisesta! button
 * object manguInfo is creaated that holds all of the information about game and user is
 * directed to next scene.
 * Created by ovekangur on 08/12/16.
 */
public class KysiInfot {

    public static void info(Stage stage) {

        VBox asetus = new VBox(10);
        asetus.setAlignment(Pos.CENTER);

        String[] abiTekstid = {"Sisesta koduvõistkonna nimi: ",
                "Sisesta võõrsilvõistkonna nimi: ",
                "Sisesta seis kujul kodu_väravad:võõrsil_väravad ",
                "Sisesta kulunud aeg kujul mm:ss "
        };

        ArrayList<Label> labels = new ArrayList<Label>();
        ArrayList<TextField> textFields = new ArrayList<TextField>();

        // Creates Lables and Text Fields
        for (int i = 0; abiTekstid.length > i; i++ ) {

            labels.add(new Label(abiTekstid[i]));
            labels.get(i).setText(abiTekstid[i]);

            textFields.add(new TextField(abiTekstid[i]));
            textFields.get(i).setPromptText(abiTekstid[i]);
            textFields.get(i).setPrefSize(300, 28);
        }

        // Creates Enter button
        Button button = new Button("Sisesta!");

        button.setOnAction((event) -> {
            String koduVoistkond = textFields.get(0).getText();
            String voorsilVoistkond = textFields.get(1).getText();
            String skoor = textFields.get(2).getText();
            String time = textFields.get(3).getText();
            ObjectGame manguInfo = new ObjectGame(koduVoistkond,voorsilVoistkond,skoor,time);
            ValjakuJoonistamine.newScene(stage, manguInfo);
        });

        // Puts everything on a Pane
        for (int i = 0; abiTekstid.length > i; i++ ) {
            asetus.getChildren().add(labels.get(i));
            asetus.getChildren().add(textFields.get(i));
        }
        asetus.getChildren().addAll(button);

        // Shows scene
        Scene scene = new Scene(asetus, 400, 300);
        stage.setScene(scene);
        stage.show();




    }

}
