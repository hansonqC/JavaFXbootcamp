package pl.hansonq.testproject.models.dao.impl;

import pl.hansonq.testproject.models.MysqlConnector;
import pl.hansonq.testproject.models.dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lukasz on 2017-09-19.
 */
public class UserDaoImpl implements UserDao {

    private MysqlConnector connector = MysqlConnector.getInstance();
    public boolean login(String name, String password) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE user =?");
            preparedStatement.setString(1,name);
            ResultSet resulSet = preparedStatement.executeQuery();
            if(!resulSet.next()){  // na początku jest -1, sprawdzamy, jezeli jest 0 tzn że jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego użytkownika
                return  false;
            }
            preparedStatement.close();
            return resulSet.getString("password").equals(password);  // hasło poprawne



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String name, String password) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE user =?");
            preparedStatement.setString(1,name);
            ResultSet resulSet = preparedStatement.executeQuery();
            if(resulSet.next()){  // na początku jest -1, sprawdzamy, jezeli jest 0 tzn że jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego użytkownika
                return  false;
            }
           PreparedStatement preparedStatementInsert = connector.getConnection().prepareStatement("INSERT INTO user VALUES(?,?,?)");
            preparedStatementInsert.setInt(1,0);
            preparedStatementInsert.setString(2,name);
            preparedStatementInsert.setString(3,password);
            preparedStatementInsert.execute();
            preparedStatement.close();
            preparedStatementInsert.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeUser(int id) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE id =?");
            preparedStatement.setInt(1,id);
            ResultSet resulSet = preparedStatement.executeQuery();
            if(!resulSet.next()){  // na początku jest -1, sprawdzamy, jezeli jest 0 tzn że jest taki uzytkownik, jezeli -1, zwracamy false, tzn nie ma takiego użytkownika
                System.out.println("nie ma takiego użytkownika");
            }
            PreparedStatement preparedStatementDelete = connector.getConnection().prepareStatement("DELETE FROM user WHERE id=?");
            preparedStatementDelete.setInt(1,0);
           preparedStatementDelete.execute();

            preparedStatement.close();
            preparedStatementDelete.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
