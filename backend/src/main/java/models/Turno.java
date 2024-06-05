package models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Turno extends BaseEntity {
    private String nameDay;
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date startTimeConsulting;
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date endTimeConsulting;
}
