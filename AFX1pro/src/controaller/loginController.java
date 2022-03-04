package controaller;

import afx1pro.AFX1pro;
import afx1pro.dataLink;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class loginController implements Initializable {

    @FXML
    private ImageView loading;

    @FXML
    private TextField id;

    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loading.setVisible(false);
    }

    @FXML
    void loginAction(ActionEvent event){
        loading.setVisible(true);
        PauseTransition wait = new PauseTransition();
        wait.setDuration(Duration.seconds(2));

        wait.setOnFinished(e -> {

            String userID = id.getText();
            String userPassWord = password.getText();
            id.setText("");
            password.setText("");

            userID = userID.trim();
            userPassWord = userPassWord.trim();
            //System.out.println(userID +"   "+userPassWord);
            if(userID.isEmpty() || userPassWord.isEmpty())
            {
                AlertBox.display("login empty", "please enter data \nso we can complete your request");
            } else {
                boolean found = false;
                try {
                    found = AFX1pro.link.checkPassword(userID,userPassWord);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                if(found == true){
                    //System.out.println("here 2");
                    generalPurposDataCollections.currentDealID= Integer.parseInt(userID);
                    String typeOfUser = AFX1pro.link.checkIfAdmin(Integer.parseInt(userID));
                    if(Objects.equals(typeOfUser, "student")) {


                        Scene scene;
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/frontend/studet_ui.fxml"));
                            scene= new Scene(root);
                            generalPurposDataCollections.getScene(scene);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else if(Objects.equals(typeOfUser, "prof")) {
                        Scene scene;
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/frontend/prof1.fxml"));
                            scene = new Scene(root);
                            generalPurposDataCollections.getScene(scene);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    } else if(Objects.equals(typeOfUser, "admin")) {
                        Scene scene;
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/frontend/system.fxml"));
                            scene = new Scene(root);
                            generalPurposDataCollections.getScene(scene);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        AlertBox.display("unknown error!!" ,
                                "we can't detect your privilage in system \n" +
                                        "go to it unit and tell him about that " );
                    }
                    } else  {
                    AlertBox.display("unknown user" ,
                            "sorry!! , you are not part of the system");
                }


                }
            loading.setVisible(false);

        });

       wait.play();
    }
}

