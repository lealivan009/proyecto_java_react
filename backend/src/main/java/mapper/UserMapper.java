package mapper;

import dto.request.UserDtoRegister;
import dto.response.UserDtoResponse;
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
                .birthDate(register.birthDate())
                .build();
    }

    public static UserDtoResponse userToDto(User entity) {
        return new UserDtoResponse(
            entity.getId(),
            entity.getEmail(), 
            entity.getPhoto(),
            entity.getName(), 
            entity.getLastname(), 
            entity.getDni(),
            entity.getBirthDate()
        );
    }
}
