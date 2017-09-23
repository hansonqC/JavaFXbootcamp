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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pl.hansonq.testproject.models.MysqlConnector;
import pl.hansonq.testproject.models.UserSession;
import pl.hansonq.testproject.models.Utils;
import pl.hansonq.testproject.models.dao.ContactDao;
import pl.hansonq.testproject.models.dao.impl.ContactDaoImpl;

import javax.swing.*;
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
    TextField textNumber, textName, textContactName, textContactNumber;

    @FXML
    Button buttonLogout,buttonDelete,buttonAdd;
    @FXML
    ListView<String> listContacts;
    @FXML
    Label labelLoged;
    private ObservableList contactItems;

    private UserSession session = UserSession.getInstance();
    private ContactDao contactDao = new ContactDaoImpl();

    public void initialize(URL location, ResourceBundle resources) {

        textName.setEditable(false);
        textNumber.setEditable(false);

        loadContacts();

        listContacts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            textName.setText(newValue);
            textNumber.setText(contactDao.getNumber(newValue));
        });


        buttonLogout.setOnMouseClicked(e -> logout());
        updateActions();

        buttonAdd.setOnMouseClicked( e-> addContact());

    buttonDelete.setOnMouseClicked(e -> deleteContact());



        // wykonuje sie po załadaowaniu programu

    }

    private void addContact() {
        contactDao.addContact(textContactName.getText(), textContactNumber.getText());
        Utils.createSimpleDialog("Dodawanie","","Dodano nowy kontakt");
        textContactName.clear();
        textContactNumber.clear();
        loadContacts();

    }

    private void deleteContact() {
        Alert alert = new Alert(Alert.AlertType.ERROR,"Czy napewno chcesz usunąć wskazanego uzytkownika ?",ButtonType.OK,ButtonType.NO);
        if(alert.getResult()==ButtonType.OK){
            contactDao.removeContact(listContacts.getSelectionModel().getSelectedItem());
            Utils.createSimpleDialog("Usuwanie","","Poprawnie usunąłes kontakt");
            loadContacts();
        }
    }

    private void updateActions() {
        textName.setOnMouseClicked(e -> {
            if (e.getClickCount() >= 2) {
                textName.setEditable(true);
            }
        });
        textName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    contactDao.editContact(textName.getText(), textNumber.getText(), listContacts.getSelectionModel().getSelectedItem());
                    loadContacts();
                    textName.setEditable(false);
                }
            }
        });
        textNumber.setOnMouseClicked(e -> {
            if (e.getClickCount() >= 2) {
                textNumber.setEditable(true);
            }
        });

        textNumber.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    contactDao.editContact(textName.getText(), textNumber.getText(), listContacts.getSelectionModel().getSelectedItem());
                    loadContacts();
                    textNumber.setEditable(false);
                }
            }
        });

    }


    private void logout() {
        session.setLogedIn(false);
        session.setUsername(null);
        session.setId(0);

        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("loginView.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            //  stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts() {
        contactItems = FXCollections.observableArrayList(contactDao.getAllContactsName(session.getUsername()));
        listContacts.setItems(contactItems);

    }
}


