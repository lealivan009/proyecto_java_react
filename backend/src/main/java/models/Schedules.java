package models;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private DayOfWeek nameDay;
    @Temporal(TemporalType.TIME)
    @Column(name="start_time")
    private LocalTime startTime;
    @Temporal(TemporalType.TIME)
    @Column(name="end_time")
    private LocalTime endTime;
    @Column(name="consulting_enable")
    private boolean consultingEnable;
}
