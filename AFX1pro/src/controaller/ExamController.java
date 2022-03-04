package controaller;

import afx1pro.AFX1pro;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExamController implements Initializable {

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label quesion;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private RadioButton radio4;

    @FXML
    private ToggleGroup selected_answer;

   // @FXML
    // private Button previous;

    @FXML
    private Button next;
/*
    @FXML
    void OnClickPrevious(ActionEvent event) {
    }

 */
    ResultSet examOfStudent;
    int count=0;
    String student_choice ,right_choice;

    @FXML
    void onClickNext(ActionEvent event) throws SQLException {
        if(radio1.isSelected()) {
            student_choice =label1.getText();
        } else if(radio2.isSelected()) {
            student_choice =label2.getText();
        } else if(radio3.isSelected()) {
            student_choice =label3.getText();
        } else {
            student_choice =label4.getText();
        }
        //System.out.println(student_choice +"  "+right_choice);
        if(student_choice.equals(right_choice))  generalPurposDataCollections.examResult +=1;
        //System.out.println(generalPurposDataCollections.examResult);
        if(examOfStudent.next()){
            quesion.setText(examOfStudent.getString("statement"));
            label1.setText(examOfStudent.getString("ch1"));
            label2.setText(examOfStudent.getString("ch2"));
            label3.setText(examOfStudent.getString("ch3"));
            label4.setText(examOfStudent.getString("ch4"));
            right_choice = examOfStudent.getString("rch");
        }else {
            if(count==0) {
                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(false);
                radio1.setVisible(false);
                radio2.setVisible(false);
                radio3.setVisible(false);
                radio4.setVisible(false);
                next.setText("confirm");
                quesion.setText("congratulation your grade is : " +generalPurposDataCollections.examResult);
                AFX1pro.link.addExamResult(generalPurposDataCollections.currentDealID,generalPurposDataCollections.examResult,
                        generalPurposDataCollections.selectedItem);

                System.out.println(generalPurposDataCollections.currentDealID +" "
                +generalPurposDataCollections.examResult +" "+generalPurposDataCollections.selectedItem);
                count++;
                generalPurposDataCollections.selectedItem=null;
            }else {
                Scene scene;
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/frontend/studet_ui.fxml"));
                    scene= new Scene(root);
                    generalPurposDataCollections.getScene(scene);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        examOfStudent = AFX1pro.link.MQuestions(generalPurposDataCollections.selectedItem);
        generalPurposDataCollections.examResult =0;
        try {
            if(examOfStudent.next()){
                quesion.setText(examOfStudent.getString("statement"));
                label1.setText(examOfStudent.getString("ch1"));
                label2.setText(examOfStudent.getString("ch2"));
                label3.setText(examOfStudent.getString("ch3"));
                label4.setText(examOfStudent.getString("ch4"));
                right_choice = examOfStudent.getString("rch");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
