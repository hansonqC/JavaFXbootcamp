package pl.hansonq.testproject.models.dao.impl;

import pl.hansonq.testproject.models.MysqlConnector;
import pl.hansonq.testproject.models.dao.ContactDao;

import java.util.List;

/**
 * Created by lukasz on 2017-09-20.
 */
public class ContactDaoImpl implements ContactDao{

    private MysqlConnector connector = MysqlConnector.getInstance();
    @Override
    public List<String> getAllContactsName(String username) {
        return null;
    }

    @Override
    public String getNumber(String contact) {
        return null;
    }

    @Override
    public boolean addContact(String name, String number) {
        return false;
    }

    @Override
    public void removeContact(String name) {

    }

    @Override
    public boolean editContact(String name, String number) {
        return false;
    }
}
