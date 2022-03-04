package controaller;

import afx1pro.AFX1pro;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class AddExamController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField answerFour;

    @FXML
    private TextField answerOne;

    @FXML
    private TextField answerTwo;

    @FXML
    private TextField answerthree;

    @FXML
    private TextField examName;

    @FXML
    private TextArea examQuestion;

    @FXML
    private TextField trueAnswer;

    Set<String> exames = new LinkedHashSet<String>();
    @FXML
    void onClickNext(ActionEvent event) {
        String exam_Name =null,exam_question =null,answer_one =null,answer_two =null,
                answer_three =null,answer_four  =null,true_Answer =null;

        try {
            exam_Name = examName.getText();
            exam_question = examQuestion.getText();
            answer_one = answerOne.getText();
            answer_two = answerTwo.getText();
            answer_three = answerthree.getText();
            answer_four = answerFour.getText();
            true_Answer = trueAnswer.getText();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(exam_Name.trim().length());

        if (exam_Name.trim().length() == 0|| exam_question.trim().length() == 0||
                answer_one.trim().length() == 0
                || answer_two.trim().length() == 0 ||
                answer_three.trim().length() == 0 ||
                answer_four.trim().length() == 0|| true_Answer.trim().length() == 0) {

            AlertBox.display("not cpmplete","please complete entering the data");
        } else {
            boolean done = false;
            try {
                done = AFX1pro.link.Minsert(exam_question.trim(),answer_one.trim(),answer_two.trim(),answer_three.trim()
                ,answer_four.trim(),true_Answer.trim(),exam_Name.trim());
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (done ==true){
                examQuestion.setText("");
                answerOne.setText("");
                answerTwo.setText("");
                answerthree.setText("");
                answerFour.setText("");
                trueAnswer.setText("");
                exames.add(exam_Name.trim());
                AlertBox.display("done!","Successfully quesion added (: ");
            } else {
                AlertBox.display("error","unexpected error happend :( \n try again");
            }
        }

    }

    @FXML
    void onClickEnd(ActionEvent event) throws SQLException {

        for (String exam : exames) {
            ResultSet studentesID = AFX1pro.link.getAllStudentId();

            while (studentesID.next()) {
                AFX1pro.link.insertStudentExam(studentesID.getInt("id"), exam);
            }
        }

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
