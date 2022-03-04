package controaller;

import afx1pro.AFX1pro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class studentUIforChosingExam implements Initializable {

    @FXML
    private ComboBox<?> combBox;

    @FXML
    private Label welcomLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList options = FXCollections.observableArrayList();
        ResultSet rs = AFX1pro.link.getStudentExams(generalPurposDataCollections.currentDealID);
        try {
            while(rs.next()){
                options.add(rs.getString(1));
            }
        }catch (Exception ex){
            System.out.println("exception in part of geeting exams" +ex);
        }
        combBox.setItems(options);
    }

    @FXML
    void onClickOK(ActionEvent event) {

        try {
            generalPurposDataCollections.selectedItem = combBox.getSelectionModel().getSelectedItem().toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println(generalPurposDataCollections.selectedItem);
        if(generalPurposDataCollections.selectedItem == null)
        {
            AlertBox.display("selection error","please select your exam");
            return;
        }
        AFX1pro.link.deleteStudentExams(generalPurposDataCollections.currentDealID,generalPurposDataCollections.selectedItem);
        //System.out.println("i am here");
        Scene scene;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/frontend/exam.fxml"));
            scene= new Scene(root);
            generalPurposDataCollections.getScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
