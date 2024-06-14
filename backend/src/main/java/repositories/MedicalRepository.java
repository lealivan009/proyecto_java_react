package repositories;

import java.util.Optional;
import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotBlank;
import models.Medical;

@ApplicationScoped
public class MedicalRepository implements PanacheRepositoryBase<Medical, UUID> {

    public Optional<Medical> findByMatricule(@NotBlank(message = "Matricula is needed!") String matricule) {
        return find("matricule",matricule).firstResultOptional();
    }
    
}
