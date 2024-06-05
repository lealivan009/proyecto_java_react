package models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name="medicals")
public class Medical extends BaseEntity {
    private String fullname;
    private String matricule;
    private String consultingPlace;
    private SpecialityType specialityType;
    @ManyToMany
    @JoinTable(
        name="medicals_x_schedules",
        joinColumns = @JoinColumn(name = "medicals_id"),
        inverseJoinColumns = @JoinColumn(name="schedules_id")
    )
    private List<Turno> consultingDates;
}
