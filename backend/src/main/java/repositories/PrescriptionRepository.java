package repositories;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import models.Prescription;

@ApplicationScoped
public class PrescriptionRepository implements PanacheRepositoryBase<Prescription, UUID> {
    
}
