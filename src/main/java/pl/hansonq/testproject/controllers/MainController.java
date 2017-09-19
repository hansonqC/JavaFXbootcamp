package pl.hansonq.testproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pl.hansonq.testproject.models.MysqlConnector;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

//Dorobić widok  logowania login/hasło i odpowiedznia tabela
    // Podglą kontaktów w mainview


    public void initialize(URL location, ResourceBundle resources) {   // wykonuje sie po załadaowaniu programu
        MysqlConnector.getInstance();
    }

}
