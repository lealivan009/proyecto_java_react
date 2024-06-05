package services;

import models.User;

import java.util.UUID;

import dto.request.UserDtoRegister;

public interface UserService {

    public void registerAndSave(UserDtoRegister userRegister) throws Exception;
    public User findUserById(UUID id) throws Exception; 
    public User updateUser(User user);
    public Object findAll();
}
