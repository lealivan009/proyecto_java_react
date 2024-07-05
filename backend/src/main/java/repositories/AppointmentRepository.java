package repositories;

import jakarta.enterprise.context.ApplicationScoped;

import models.Appointment;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;
import java.util.UUID;

import dto.response.AppointmentDtoResponse;


//implemento esa interfaz ya que Panache es una extensión de Quarkus que 
//ya me da las operaciones CRUD y consultas JPQL, utilizando Hibernate como ORM
@ApplicationScoped
public class AppointmentRepository implements PanacheRepositoryBase<Appointment, UUID> {
    
    public List<AppointmentDtoResponse> getAllByUser(UUID userId){
        return find("user.id", userId).project(AppointmentDtoResponse.class).list();
    }
}

