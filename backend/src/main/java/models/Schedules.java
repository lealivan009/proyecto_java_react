package models;

import java.util.Date;

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
@Table(name="schedules")
public class Schedules extends BaseEntity {
    @Column(name="name_day")
    private String nameDay;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_time")
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_time")
    private Date endTime;
}
