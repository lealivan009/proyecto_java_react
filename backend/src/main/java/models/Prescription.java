package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name="prescriptions")
public class Prescription extends BaseEntity {
    private Turno emissionDate;
    private String patientName;
    private String medicamentDescription;
    private String posology;
    private int durationTratament;
}
