package dao;

import entity.Role;

import java.util.List;

public interface RepositoryR {
    void insert(Role role);
    boolean delete(int id);
    List<Role> getAll();
    void update(Role role);
    Role getById(int id);
}
