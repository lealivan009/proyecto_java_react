package mapper;

import dto.request.UserDtoRegister;
import dto.response.PublicUserDtoResponse;
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

    public static PublicUserDtoResponse userToDto(User entity) {
        return new PublicUserDtoResponse(
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
