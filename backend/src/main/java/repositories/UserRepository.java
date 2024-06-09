package repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dto.response.FullUserDto;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import models.User;


@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, UUID> {

    public Optional<User> findByEmail(String email){
        return find("email",email).firstResultOptional();
    }
    
    public List<FullUserDto> findAllFullUserDto(){
        return findAll().project(FullUserDto.class).list();
    }

    public void softDelete(User user){
        user.setEnable(false);
        persist(user);    
    }
}
