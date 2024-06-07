package services;

import models.User;

import java.util.UUID;

import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import dto.response.FullUserDto;

public interface UserService {
    public void registerAndSave(UserDtoRegister userRegister) throws Exception;
    public FullUserDto loginUser(UserDtoLogin UserLogin) throws Exception;
    public User findUserById(UUID id) throws Exception; 
    public User updateUser(User user);
    public Object findAll();
}
