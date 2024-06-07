package mapper;

import dto.request.UserDtoRegister;
import models.User;

public class UserMapper {
    public static User dtoToUser(UserDtoRegister register){
        return User.builder()
            .email(register.email())
            .password(register.password())
            .dni(register.dni())
            .photo(register.photo())
            .name(register.name())
            .lastname(register.lastname())
            .build();
    }    
}
