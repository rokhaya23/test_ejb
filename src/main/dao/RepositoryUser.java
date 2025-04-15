package dao;

import entity.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RepositoryUser{
    void insert(User user);
    boolean delete(int id);
    List<User> getAll();
    void update(User user);
    User getById(int id);
}
