package pl.hansonq.testproject.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lukasz on 2017-09-05.
 */
public class MysqlConnector {

    private static  final String SQL_LINK = "jdbc:mysql://5.135.218.27:3306/hansonq";
    private static  final String SQL_USER = "hansonq";
    private static  final String SQL_PASS = "Komornik12";
    private static  final String SQL_CLASS = "com.mysql.jdbc.Driver";

    private static MysqlConnector connector = new MysqlConnector();

    public static MysqlConnector getInstance(){     //wzorzec Singleton. prywatny konstruktor i tylko metoda publiczna która zwraca jedyną instancje
                                                    //JEŻELI KLASA JEST SINGLETONEM TO MA TYLKO JEDNĄ INSTANCJE
        return connector;
    }

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private MysqlConnector() {
        connect();   //metoda wywoływana z konstruktorem
    }
    private void connect(){
        try {
            Class.forName(SQL_CLASS);    //refleksja - przywołuje klase z java.sql do naszej klasy
            connection = DriverManager.getConnection(SQL_LINK,SQL_USER,SQL_PASS);
            System.out.println("Połaczono");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
