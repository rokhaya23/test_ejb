package dao;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class AuthRepository implements Auth{

    private  static final Logger logger = LogManager.getLogger(AuthRepository.class.getName());

    @PersistenceContext(unitName = "PERSISTENCE")
    EntityManager entityManager;

    private User currentUser;
    private List<String> userActions = new ArrayList<>();

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
        logger.info("User set in session: " + user.getNom());
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void addUserAction(String action) {
        userActions.add(action);
        logger.info("Action added for user " + currentUser.getNom() + ": " + action);
    }

    @Override
    public List<String> getUserActions() {
        return userActions;
    }

    @Override
    public void clearSession() {
        currentUser = null;
        userActions.clear();
        logger.info("User session cleared");
    }

    @Override
    public boolean login(String username, String password) {
        try {
            // Utiliser JPA pour vérifier les identifiants
            User user = entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                            User.class)
                    .setParameter("username", username)
                    .setParameter("password", password) // Idéalement, utilisez un hachage
                    .getSingleResult();

            // Si l'utilisateur est trouvé, définir comme utilisateur courant
            this.setCurrentUser(user);
            this.addUserAction("User logged in");
            logger.info("User logged in: " + username);
            return true;
        } catch (Exception e) {
            logger.error("Login failed for user: " + username, e);
            return false;
        }
    }

    @Override
    public void logout() {
        if (this.currentUser != null) {
            this.addUserAction("User logged out");
            logger.info("User logged out: " + currentUser.getNom());
        }
        this.clearSession();
    }
}
