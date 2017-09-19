package pl.hansonq.testproject.models;

import javafx.scene.control.Alert;

/**
 * Created by lukasz on 2017-09-19.
 */
public class Utils {
    public static void createSimpleDialog(String name, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(name);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }
}
