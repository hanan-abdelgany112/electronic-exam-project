package controaller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AlertBox {
    public static void display(String titel, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(titel);
        window.setMinWidth(250);

        Label label= new Label(message);
        Button closeButton = new Button("close the window");
        closeButton.setOnAction(e->{
           window.close();
        });

        VBox layout1 = new VBox();
        layout1.getChildren().addAll(label,closeButton);
        layout1.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.show();

    }
}












