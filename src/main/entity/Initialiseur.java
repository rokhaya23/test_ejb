package entity;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
@Startup
public class Initialiseur {

    @PersistenceContext(unitName = "PERSISTENCE")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            // Vérifie si l'utilisateur "admin" existe déjà
            Long count = entityManager.createQuery(
                            "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                    .setParameter("username", "admin")
                    .getSingleResult();

            if (count == 0) {
                // Créer un rôle admin si nécessaire
                Role adminRole = entityManager.createQuery(
                                "SELECT r FROM Role r WHERE r.name = :name", Role.class)
                        .setParameter("name", "admin")
                        .getResultStream()
                        .findFirst()
                        .orElseGet(() -> {
                            Role r = new Role();
                            r.setName("admin");
                            entityManager.persist(r);
                            return r;
                        });

                // Créer l'utilisateur admin
                User u = new User();
                u.setNom("Admin");
                u.setPrenom("Root");
                u.setAge(23);
                u.setUsername("admin");
                u.setPassword("admin123");
                u.setEmail("admin@gmail.com");
                u.setRole(adminRole);

                entityManager.persist(u);

                System.out.println("Utilisateur admin créé avec succès.");
            } else {
                System.out.println("L'utilisateur admin existe déjà.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
