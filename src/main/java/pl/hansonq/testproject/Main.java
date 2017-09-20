package pl.hansonq.testproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginView.fxml"));  // getClassLoader - przeszukuje wszystkie foldery w obrebie projektu

        primaryStage.setTitle("AkademiaKodu");
        primaryStage.setScene(new Scene(root, 350, 220));
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->closing());
    }

    private void closing() {

            int result= JOptionPane.YES_OPTION;
            if(JOptionPane.showConfirmDialog(null, "Czy napewno chcesz wyjść z programu ?",null, JOptionPane.YES_NO_OPTION,0, null)==result){
                System.exit(0);
            };
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}
