package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "users")
public class User extends BaseEntity {
    
    private String email;
    private String password;
    private String photo;
    private String name;
    private String lastname;
    private String dni;
    @Temporal(TemporalType.DATE)
    @Column(name ="bith_date")
    private LocalDate birthDate;

    @Column(name = "is_enable")
    private boolean isEnable;
    
    @CreationTimestamp
    @Column(name = "create_user")
    private LocalDateTime createdUser;
    @UpdateTimestamp
    @Column(name = "update_user")
    private LocalDateTime updateUser;
}
