package pl.hansonq.testproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
Button buttonHello;


    @Override
    public void initialize(URL location, ResourceBundle resources) {   // wykonuje sie po załadaowaniu programu
  buttonHello.setText("Zmieniłem nazwe hehhe");
/*  buttonHello.setOnMouseClicked(e -> say());//new javafx.event.EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
          System.out.println("Witaj świecie");
      }
  });*/

    }
    public void onButtonHelloClick(MouseEvent event){
        if(event.isAltDown()){
        System.out.println("hello Klikniecie: "+event.getClickCount());
    }
}}
