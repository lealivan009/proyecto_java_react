package models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.enumerations.SpecialityType;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name="medicals")
public class Medical extends BaseEntity {
    private String fullname;
    private String matricule;
    @Column(name="consulting_place")
    private String consultingPlace;
    @Enumerated(EnumType.STRING)
    @Column(name="speciality_type")
    private SpecialityType specialityType;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name="medicals_x_schedules",
        joinColumns = @JoinColumn(name = "medicals_id"),
        inverseJoinColumns = @JoinColumn(name="schedules_id")
    )
    private List<Schedules> consultingDates;
}
