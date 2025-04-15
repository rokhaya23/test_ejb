package dao;

import entity.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Auth{

    public void setCurrentUser(User user);
    public User getCurrentUser();
    public void addUserAction(String action);
    public List<String> getUserActions();
    public void clearSession();
    public boolean login(String username, String password);
    public void logout();
}
