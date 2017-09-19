package pl.hansonq.testproject.models.dao;

/**
 * Created by lukasz on 2017-09-19.
 */
public interface UserDao {
    boolean login(String name, String password);
    boolean register(String name, String password);
    void removeUser(int id);
}
