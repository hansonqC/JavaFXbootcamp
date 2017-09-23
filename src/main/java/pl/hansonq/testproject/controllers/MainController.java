package pl.hansonq.testproject.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pl.hansonq.testproject.models.MysqlConnector;
import pl.hansonq.testproject.models.UserSession;
import pl.hansonq.testproject.models.dao.ContactDao;
import pl.hansonq.testproject.models.dao.impl.ContactDaoImpl;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //Dorobić widok  logowania login/hasło i odpowiedznia tabela
    // Podglą kontaktów w mainview
    @FXML
    TextField textNumber, textName;

    @FXML
    Button buttonLogout;
    @FXML
    ListView listContacts;
    @FXML
    Label labelLoged;
    private ObservableList contactItems;

    private UserSession session = UserSession.getInstance();
    private ContactDao contactDao = new ContactDaoImpl();
    public void initialize(URL location, ResourceBundle resources) {

        textName.setEditable(false);
        textNumber.setEditable(false);

        loadContacts();

        listContacts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textName.setText(newValue);
                textNumber.setText(contactDao.getNumber(newValue));
            }
        });




        buttonLogout.setOnMouseClicked(e ->logout());


        textName.setOnMouseClicked( e->{
            if(e.getClickCount()>=2){
                textName.setEditable(true);
            }
        });


        // wykonuje sie po załadaowaniu programu
        //   MysqlConnector.getInstance();
        //   labelLoged.setTextFill(Paint.valueOf("red"));
        //  labelLoged.setText("Zalogowany :"+userSession.getUsername());
    }

    private void logout() {
        session.setLogedIn(false);
        session.setUsername(null);
        session.setId(0);

        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginView.fxml")) ;
            Scene scene = new Scene(root);

            stage.setScene(scene);
          //  stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts(){
        contactItems = FXCollections.observableArrayList(contactDao.getAllContactsName(session.getUsername()));
        listContacts.setItems(contactItems);

    }
}


