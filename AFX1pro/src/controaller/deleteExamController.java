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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class deleteExamController implements Initializable {

    @FXML
    private ComboBox<?> comboBox;

    @FXML
    void onClickBack(ActionEvent event) {
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

    @FXML
    void onClickConfirm(ActionEvent event) {
/*
        AlertBox.display(":( NOT_Yet :(","message from Ahmed Sakr \n " +
                "i don't implement it yet \n" +
                "there is some errors here need more think");

 */

        String selectedItem = null;
        try {
            selectedItem = comboBox.getSelectionModel().getSelectedItem().toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        selectedItem = selectedItem.trim();
        if(selectedItem == null)
        {
            AlertBox.display("selection error","please select your exam");
            return;
        } else {
            AFX1pro.link.deleteExamFromDataBase(selectedItem);
            AlertBox.display("Done","exam deleted successfully!!");
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList options = FXCollections.observableArrayList();
        ResultSet rs = AFX1pro.link.examsNames();
        try {
            while(rs.next()){
                options.add(rs.getString(1));
            }
        }catch (Exception ex){
            System.out.println("exception in part of geeting exams" +ex);
        }
        comboBox.setItems(options);
    }
}
