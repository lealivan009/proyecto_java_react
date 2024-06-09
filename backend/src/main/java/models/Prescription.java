package models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    @Column(name="emission_date")
    private LocalDate emissionDate;
    @Column(name="patient_name")
    private String patientName;
    @Column(name="medicament_description")
    private String medicamentDescription;
    private String posology;
    @Column(name="duration_tratament")
    private int durationTratament;
}
