package dao;

import entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless

public class RoleRepository implements RepositoryR {
    private  static final Logger logger = LogManager.getLogger(RoleRepository.class.getName());

    @PersistenceContext(unitName = "PERSISTENCE")
    EntityManager entityManager;


    @Override
    public void insert(Role role) {
        logger.info("Start Insertation role "+ role);
        entityManager.persist(role);
        logger.info("End Insertation role "+role);
    }

    @Override
    public boolean delete(int id) {
        logger.info("Start Delete role "+ id);
        Role role = getById(id);
        entityManager.remove(role);
        logger.info("End Delete role "+ id);
        return true;
    }

    @Override
    public List<Role> getAll() {
        logger.info("Start GET ALL USERS  ");
        List<Role> roles =entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        logger.info("End GET ALL USERS  ");
        return roles;
    }

    @Override
    public void update(Role role) {
        logger.info("Start Udpate role "+ role);
        entityManager.merge(role);
        logger.info("End Udpate role "+ role);
    }

    @Override
    public Role getById(int id) {
        logger.info("Start get by role "+ id);
        Role role = entityManager.find(Role.class,id);
        logger.info("End get by role "+ id);
        return role;
    }
}
