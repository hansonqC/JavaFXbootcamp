package pl.hansonq.testproject.models.dao.impl;

import javafx.collections.ObservableList;
import pl.hansonq.testproject.models.MysqlConnector;
import pl.hansonq.testproject.models.UserSession;
import pl.hansonq.testproject.models.dao.ContactDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 2017-09-20.
 */
public class ContactDaoImpl implements ContactDao {

    private MysqlConnector connector = MysqlConnector.getInstance();
    private UserSession userSession = UserSession.getInstance();

    @Override
    public List<String> getAllContactsName(String username) {
        List<String> nameList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT name FROM contact INNER JOIN user ON user.id = contact.user WHERE user.username = ?"
            );

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nameList.add(resultSet.getString("name"));
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameList;
    }


    @Override
    public String getNumber(String contact) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT number FROM contact WHERE name = ?"
            );

            preparedStatement.setString(1, contact);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("number");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean addContact(String name, String number) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "INSERT INTO contact VALUES(?,?,?,?)"
            );

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, number);
            preparedStatement.setInt(4, userSession.getId());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void removeContact(String name) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "DELETE FROM contact WHERE name = ?"
            );
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean editContact(String newName, String number, ObservableList list) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "UPDATE contact SET number = ? WHERE name = ?"
            );

            preparedStatement.setString(1, number);
            preparedStatement.setString(2, newName);

            preparedStatement.execute();
            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

