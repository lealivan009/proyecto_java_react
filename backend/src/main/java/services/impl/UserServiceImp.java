package services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import dto.request.UserDtoUpdate;
import dto.response.UserDtoResponse;
import exceptions.EntityAlredyExistException;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectUsernameOrPasswordExpection;
import exceptions.InvalidFieldException;
import exceptions.PasswordNotCoincidentException;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.UserMapper;
import models.User;
import repositories.UserRepository;
import services.UserService;
import validator.Validator;

@ApplicationScoped
public class UserServiceImp implements UserService {

    @Inject
    private UserRepository userRepo;

    @Inject
    Validator validator;

    @Transactional
    @Override
    public void registerAndSave(UserDtoRegister userRegister) throws InvalidFieldException, PasswordNotCoincidentException, EntityAlredyExistException  {
        //valida campos de entrada obligatorios y que las contraseñas coincidan
        validator.validate(userRegister);
        if(!userRegister.password().equals(userRegister.repeatPassword()))
            throw new PasswordNotCoincidentException();

        Optional<User> userEntity = userRepo.findByEmail(userRegister.email());
        if (userEntity.isPresent())
            throw new EntityAlredyExistException("User with email [" + userRegister.email() + "] is already exist!");

        User userToPersist = UserMapper.dtoToUser(userRegister);
        userToPersist.setEnable(true);
        userToPersist.setPassword(BcryptUtil.bcryptHash(userToPersist.getPassword()));
        userRepo.persist(userToPersist);
    }
    
    @Override
    public UserDtoResponse loginUser(UserDtoLogin userLogin) throws InvalidFieldException, IncorrectUsernameOrPasswordExpection{
        validator.validate(userLogin);

        User userEntity = userRepo.findByEmail(userLogin.email()).orElseThrow(()-> new IncorrectUsernameOrPasswordExpection());
        if(!BcryptUtil.matches(userLogin.password(), userEntity.getPassword()))
            throw new IncorrectUsernameOrPasswordExpection();

        return UserMapper.userToDto(userEntity);
    }

    @Override
    public List<UserDtoResponse> findAll() {
        return userRepo.findAllPublicUserDto();
    }

    @Override
    public User findUserById(UUID id) throws EntityNotFoundException {
        return userRepo.findByIdOptional(id).orElseThrow(() -> new EntityNotFoundException("User not exist with id " + id));
    }

    @Transactional
    @Override
    public void updateUser(UUID id, UserDtoUpdate userUpdate) throws InvalidFieldException, EntityNotFoundException {
        validator.validate(userUpdate);
        User userEntity = findUserById(id);
        
        if(userUpdate.email() != null)
            userEntity.setEmail(userUpdate.email());
        if(userUpdate.name() != null)
            userEntity.setName(userUpdate.name());
        if(userUpdate.lastname() != null)
            userEntity.setLastname(userUpdate.lastname());
        if(userUpdate.dni() != null)
            userEntity.setDni(userUpdate.dni());
        if(userUpdate.photo() != null)
            userEntity.setPhoto(userUpdate.photo());
        if(userUpdate.birthDate() != null)
            userEntity.setBirthDate(userUpdate.birthDate());
        if(userUpdate.password() != null)
            userEntity.setPassword(BcryptUtil.bcryptHash(userUpdate.password()));
        
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) throws EntityNotFoundException  {
        var userEntity = findUserById(id);
        userRepo.softDelete(userEntity);
    }

}
