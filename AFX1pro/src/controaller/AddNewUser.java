package controaller;

import afx1pro.AFX1pro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class AddNewUser implements Initializable {


        @FXML
        private ComboBox<?> combBox;

        @FXML
        private TextField user_Id;

        @FXML
        private TextField user_Name;

        @FXML
        private PasswordField user_Password;

        @FXML
        void onClickAddNewUser(ActionEvent event) {
            String userID = null,userPassword = null,userName = null,userPrivillage = null;
            try {
                userID = user_Id.getText();
                userPassword =user_Password.getText();
                userName = user_Name.getText();
                userPrivillage = combBox.getSelectionModel().getSelectedItem().toString();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            userID =userID.trim();
            userPassword =userPassword.trim();
            userName =userName.trim();
            if(userID.length() == 0|| userPassword.length() == 0|| userName.length() == 0|| userPrivillage.length() == 0) {
                AlertBox.display("uncomplete Data" ,"Please enter all information\n" +
                        "so we can complete your request");
            } else{
                boolean found = true;
                try {
                    found = AFX1pro.link.isUserExist(Integer.parseInt(userID));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                if(found==false) {
                    boolean done = false;
                    try {
                        done=AFX1pro.link.addUser(Integer.parseInt(userID),userName,userPrivillage,userPassword);

                        if(userPrivillage == "student") {
                            ResultSet examesTOtheNewUser = AFX1pro.link.examsNames();
                            while (true) {
                                try {
                                    if (!examesTOtheNewUser.next()) break;
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                                AFX1pro.link.insertStudentExam(Integer.parseInt(userID), examesTOtheNewUser.getString("exam_name"));
                            }
                        }

                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    if(done == true) {
                        AlertBox.display("User Add","Successfully Added!!");
                    } else {
                        AlertBox.display("User Add","Unexpected Error happened \n please try again");
                    }
                    user_Id.setText("");
                    user_Name.setText("");
                    user_Password.setText("");
                } else {
                    AlertBox.display("used before!!","please user another iD \n " +
                            "case that Id is already used");
                }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList options = FXCollections.observableArrayList("admin","student","prof");
        combBox.setItems(options);
    }

}
