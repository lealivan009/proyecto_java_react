package repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.User;


@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    // Métodos adicionales para la gestión de User si es necesario
}
