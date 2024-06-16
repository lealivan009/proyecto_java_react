package services;

import models.User;

import java.util.List;
import java.util.UUID;

import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import dto.request.UserDtoUpdate;
import dto.response.PublicUserDtoResponse;
import exceptions.EntityAlredyExistException;
import exceptions.EntityNotFoundException;
import exceptions.IncorrectUsernameOrPasswordExpection;
import exceptions.InvalidFieldException;
import exceptions.PasswordNotCoincidentException;

public interface UserService {
    public void registerAndSave(UserDtoRegister userRegister) throws InvalidFieldException, PasswordNotCoincidentException, EntityAlredyExistException ;
    public PublicUserDtoResponse loginUser(UserDtoLogin UserLogin) throws InvalidFieldException, IncorrectUsernameOrPasswordExpection ;
    public User findUserById(UUID id) throws EntityNotFoundException ; 
    public void updateUser(UUID id, UserDtoUpdate userUpdate) throws InvalidFieldException, EntityNotFoundException ;
    public List<PublicUserDtoResponse> findAll();
    public void deleteUser(UUID id) throws EntityNotFoundException ;
}
