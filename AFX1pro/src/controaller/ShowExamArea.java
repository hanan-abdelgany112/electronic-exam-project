package controaller;

import afx1pro.AFX1pro;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ShowExamArea implements Initializable {

    @FXML
    private TextArea textAreaForGrades;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textAreaForGrades.setText("Student ID       Student Grade          examName" );
        ResultSet rs = AFX1pro.link.getSpecificExamResult(generalPurposDataCollections.selectedItem);
        try {
            while(rs.next()){
                textAreaForGrades.setText(textAreaForGrades.getText()
                +"\n"+ rs.getInt("id")+"                      "+rs.getInt("mark")
                        +"                                "+generalPurposDataCollections.selectedItem);
            }
        }catch (Exception ex){
            System.out.println("exception in part of geeting exams" +ex);
        }
    }

    @FXML
    void onClickBack(ActionEvent event) {
        textAreaForGrades.setText("");
        generalPurposDataCollections.selectedItem=null;
        Scene scene;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/frontend/prof1.fxml"));
            scene = new Scene(root);
            generalPurposDataCollections.getScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
