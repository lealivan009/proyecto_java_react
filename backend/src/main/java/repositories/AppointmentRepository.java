package repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import models.Appointment;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.util.UUID;

//implemento esa interfaz ya que Panache es una extensión de Quarkus que 
//ya me da las operaciones CRUD y consultas JPQL, utilizando Hibernate como ORM
@ApplicationScoped
public class AppointmentRepository implements PanacheRepositoryBase<Appointment, UUID> {
    //me lo da hibernate para interactuar con la bd
    @Inject
    EntityManager entityManager;
}

