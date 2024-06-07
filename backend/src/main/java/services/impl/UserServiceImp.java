package services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import Exception.UserException;
import dto.request.UserDtoRegister;
import dto.response.FullUserDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import mapper.UserMapper;
import models.User;
import repositories.UserRepository;
import services.UserService;

@ApplicationScoped
public class UserServiceImp implements UserService {

    @Inject
    private UserRepository userRepo;

    @Inject
    Validator validator;

    @Transactional
    @Override
    public void registerAndSave(UserDtoRegister userRegister) throws Exception {
        validateUserRegister(userRegister);
        Optional<User> userEntity = userRepo.findByEmail(userRegister.email());

        if (userEntity.isPresent())
            throw new UserException("User with email [" + userRegister.email() + "] is already exist!");

        userRepo.persist(UserMapper.dtoToUser(userRegister));
    }

    public List<FullUserDto> findAll() {
        return userRepo.findAll().project(FullUserDto.class).list();
    }

    @Override
    public User findUserById(UUID id) throws Exception {
        return userRepo.findByIdOptional(id).orElseThrow(() -> new Exception("User not exist with id [ " + id + "]"));
    }

    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    // valida los campos del usuario a registrar, lanza una Exception con los campos incorrectos
    private void validateUserRegister(UserDtoRegister userRegister) throws Exception {
        var contrains = validator.validate(userRegister);
        if (!contrains.isEmpty()) {
            StringBuilder errorsMessage = new StringBuilder();
            contrains.stream()
                    .forEach(c -> errorsMessage.append(c.getMessageTemplate()).append(", "));
            throw new Exception(errorsMessage.toString());
        }
    }

}
