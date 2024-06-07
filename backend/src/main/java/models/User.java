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
@Table(name = "users")
public class User extends BaseEntity {
    
    private String email;
    private String password;
    private String photo;
    private String name;
    private String lastname;
    private String dni;
}
