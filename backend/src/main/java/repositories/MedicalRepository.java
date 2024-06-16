package repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dto.response.MedicalDtoResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotBlank;
import models.Medical;

@ApplicationScoped
public class MedicalRepository implements PanacheRepositoryBase<Medical, UUID> {

    public Optional<Medical> findByMatricule(@NotBlank(message = "Matricula is needed!") String matricule) {
        return find("matricule",matricule).firstResultOptional();
    }

    public List<MedicalDtoResponse> findAllMedicalDto(){
        return findAll().project(MedicalDtoResponse.class).list();
    } 
    
}
