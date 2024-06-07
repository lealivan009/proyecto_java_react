package mapper;

import dto.request.UserDtoRegister;
import dto.response.FullUserDto;
import models.User;

public class UserMapper {
    public static User dtoToUser(UserDtoRegister register) {
        return User.builder()
                .email(register.email())
                .password(register.password())
                .dni(register.dni())
                .photo(register.photo())
                .name(register.name())
                .lastname(register.lastname())
                .build();
    }

    public static FullUserDto userToDto(User entity) {
        return new FullUserDto(
            entity.getId(),
            entity.getEmail(), 
            entity.getPassword(), 
            entity.getPhoto(),
            entity.getName(), 
            entity.getLastname(), 
            entity.getDni()
        );
    }
}
