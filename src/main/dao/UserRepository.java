package dao;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRepository implements RepositoryUser{

    private  static final Logger logger = LogManager.getLogger(UserRepository.class.getName());

    @PersistenceContext(unitName = "PERSISTENCE")
    EntityManager entityManager;


    @Override
    public void insert(User user) {
        logger.info("Start Insertation user "+ user);
        entityManager.persist(user);
        logger.info("End Insertation user "+user);
    }

    @Override
    public boolean delete(int id) {
        logger.info("Start Delete user "+ id);
        User user = getById(id);
        entityManager.remove(user);
        logger.info("End Delete user "+ id);
        return true;
    }

    @Override
    public List<User> getAll() {
        //Start logger
        logger.info("Start GET ALL USERS  ");
        List<User> users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        logger.info("End GET ALL USERS  ");
        return users;
    }

    @Override
    public void update(User user) {
        logger.info("Start Udpate user "+ user);
        entityManager.merge(user);
        logger.info("End Udpate user "+ user);
    }

    @Override
    public User getById(int id) {
        //Start logger
        logger.info("Start get by user "+ id);
        User user = entityManager.find(User.class,id);
        logger.info("End get by user "+ id);
        return user;
    }
}
