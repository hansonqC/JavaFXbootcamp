package pl.hansonq.testproject.models.dao;

import javafx.collections.ObservableList;

import java.util.List;
import java.util.Observer;

/**
 * Created by lukasz on 2017-09-20.
 */
public interface ContactDao {
List<String> getAllContactsName(String username);
String getNumber(String contact);
boolean addContact(String name, String number);
void removeContact(String name);
boolean editContact(String newName, String number, ObservableList list);

}


