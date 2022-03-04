package controaller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class profController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void OnClickDleteExam(ActionEvent event) {
        Scene scene;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/frontend/delete_exam.fxml"));
            scene = new Scene(root);
            generalPurposDataCollections.getScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClickAddExam(ActionEvent event) {

        Scene scene;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/frontend/add_exam.fxml"));
            scene = new Scene(root);
            generalPurposDataCollections.getScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void onClickShowGrades(ActionEvent event) {
        Scene scene;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/frontend/ShowGrade1.fxml"));
            scene = new Scene(root);
            generalPurposDataCollections.getScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void onclickExit(ActionEvent event) {
        generalPurposDataCollections.closeStage();
    }


}
