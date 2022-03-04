package controaller;

import afx1pro.AFX1pro;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class deleteUserController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField user_Id;

    @FXML
    void OnClickDlete(ActionEvent event) {
        String userID = null;

        try {
            userID = user_Id.getText();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if(userID != null) {

            boolean found = false;
            try {
                found = AFX1pro.link.isUserExist(Integer.parseInt(userID));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if(found==true) {

                try {
                    AFX1pro.link.deleteUserFromDB(Integer.parseInt(userID));
                    AlertBox.display("DONE","Successfully deleted");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
               user_Id.setText("");
            } else {
                AlertBox.display("not found","this id is already don't exist in system");
            }

        }else {
            AlertBox.display("empty field!","please enter id");
        }
    }

    @FXML
    void onClickBack(ActionEvent event) {
        Scene scene;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/frontend/system.fxml"));
            scene = new Scene(root);
            generalPurposDataCollections.getScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
