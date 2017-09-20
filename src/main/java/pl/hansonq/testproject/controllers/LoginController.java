package pl.hansonq.testproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.hansonq.testproject.models.UserSession;
import pl.hansonq.testproject.models.Utils;
import pl.hansonq.testproject.models.dao.UserDao;
import pl.hansonq.testproject.models.dao.impl.UserDaoImpl;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lukasz on 2017-09-19.
 */
public class LoginController implements Initializable {
    @FXML
TextField textLogin,textLoginR;
    @FXML
PasswordField textPassword,textPasswordR,textPasswordRepeatR;
    @FXML
Button buttonLogin,buttonRegister;

private UserSession userSession = UserSession.getInstance();
private UserDao userdao = new UserDaoImpl();
    public void initialize(URL location, ResourceBundle resources) {
        buttonLogin.setOnMouseClicked(e -> tryLogin());
        buttonRegister.setOnMouseClicked(e ->tryRegister());
    }

    private void tryRegister() {
        String loginR = textLoginR.getText();
        String passwordR =textPasswordR.getText();

        if(!checkRegisterData()){
                    return;
        }
        if(userdao.register(loginR,passwordR)){
            Utils.createSimpleDialog("Rejestracja", "","Zarejestrowałeś się poprawnie");
        }else{
            Utils.createSimpleDialog("Rejestracja", "","Podany login już istnieje");
        }

    }

    private boolean checkLoginData(){
        String login = textLogin.getText();
        String password = textPassword.getText();

        if(login.isEmpty()|| password.isEmpty()){
            Utils.createSimpleDialog("Logowanie","","Pola nie mogą być puste !");
        }
        if(login.length()<=3 || password.length() <=5){
            Utils.createSimpleDialog("Logowanie","","Dane za krótkie !");
        }
        return  true;
    }
// zrób rejestracje
    private void tryLogin() {
        String login = textLogin.getText();
        String password = textPassword.getText();
        if(!checkLoginData()){
            return;
        }
        if(userdao.login(login,password)){
            userSession.setUsername(login);
            userSession.setLogedIn(true);
            Utils.createSimpleDialog("Logowanie","","Zalogowano poprawnie !");
        }else{
            Utils.createSimpleDialog("Logowanie","","Podano niepoprawne dane !");
        }

    }
    private boolean checkRegisterData(){
        String loginR = textLoginR.getText();
        String passwordR =textPasswordR.getText();
        String passwordRepeatR = textPasswordRepeatR.getText();
        if(loginR.isEmpty()|| passwordR.isEmpty()||passwordRepeatR.isEmpty()){
            Utils.createSimpleDialog("Rejestracja","","Pola nie mogą być puste !");
            return false;
        }
        if(loginR.length()<=3 || passwordR.length() <=5){
            Utils.createSimpleDialog("Rejestracja","","Dane są za krótkie !");
            return false;
        }
        if(!passwordR.equals(passwordRepeatR)){
            Utils.createSimpleDialog("Rejestracja","","Hasła nie sa identyczne !");
            return false;
        }
        return  true;
    }
}
