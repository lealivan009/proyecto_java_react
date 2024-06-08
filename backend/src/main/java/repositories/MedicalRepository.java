package repositories;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import models.Medical;

@ApplicationScoped
public class MedicalRepository implements PanacheRepositoryBase<Medical, UUID> {
    
}
