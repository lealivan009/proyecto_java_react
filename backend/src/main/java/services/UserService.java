package services;

import models.User;

import java.util.UUID;

import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import dto.request.UserDtoUpdate;
import dto.response.FullUserDto;

public interface UserService {
    public void registerAndSave(UserDtoRegister userRegister) throws Exception;
    public FullUserDto loginUser(UserDtoLogin UserLogin) throws Exception;
    public User findUserById(UUID id) throws Exception; 
    public void updateUser(UUID id, UserDtoUpdate userUpdate) throws Exception;
    public Object findAll();
}
