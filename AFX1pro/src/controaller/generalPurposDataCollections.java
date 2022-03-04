package controaller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class generalPurposDataCollections {
    public static String selectedItem =null;
    public static int examResult;
    public static int currentDealID;
    private static Stage stage = new Stage();

     public static void glopalStageForfirstCall (Scene scene) {
         stage.setScene(scene);
         stage.setResizable(false);
         stage.show();
     }
     public static void getScene(Scene scene) {
         stage.setScene(scene);
     }

     public static void closeStage() {
         stage.close();
     }
}
