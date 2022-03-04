/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afx1pro;

import controaller.generalPurposDataCollections;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mo
 */
public class AFX1pro extends Application {
    public static dataLink link;
    @Override
    public void start(Stage primStage) throws Exception {
        link=dataLink.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("/frontend/login.fxml"));

        Scene scene = new Scene(root);

        generalPurposDataCollections.glopalStageForfirstCall(scene);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
