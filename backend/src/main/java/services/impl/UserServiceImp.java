package services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import dto.request.UserDtoUpdate;
import dto.response.FullUserDtoResponse;
import dto.response.UserDtoResponse;
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
    public void registerAndSave(UserDtoRegister userRegister) throws Exception {
        validator.validate(userRegister);
        Optional<User> userEntity = userRepo.findByEmail(userRegister.email());

        if (userEntity.isPresent())
            throw new Exception("User with email [" + userRegister.email() + "] is already exist!");

        User userToPersist = UserMapper.dtoToUser(userRegister);
        userToPersist.setEnable(true);
        userToPersist.setPassword(BcryptUtil.bcryptHash(userToPersist.getPassword()));
        userRepo.persist(userToPersist);
    }
    
    @Override
    public UserDtoResponse loginUser(UserDtoLogin userLogin) throws Exception {
        validator.validate(userLogin);

        User userEntity = userRepo.findByEmail(userLogin.email()).orElseThrow(()-> new Exception("Incorrect email or passwords"));
        if(!BcryptUtil.matches(userLogin.password(), userEntity.getPassword()))
            throw new Exception("Incorrect email or passwords");

        return UserMapper.userToDto(userEntity);
    }

    @Override
    public List<FullUserDtoResponse> findAll() {
        return userRepo.findAllFullUserDto();
    }

    @Override
    public User findUserById(UUID id) throws Exception {
        return userRepo.findByIdOptional(id).orElseThrow(() -> new Exception("User not exist with id " + id));
    }

    @Transactional
    @Override
    public void updateUser(UUID id, UserDtoUpdate userUpdate) throws Exception {
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
    public void deleteUser(UUID id) throws Exception {
        var userEntity = findUserById(id);
        userRepo.softDelete(userEntity);
    }

}
