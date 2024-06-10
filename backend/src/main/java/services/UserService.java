package services;

import models.User;

import java.util.List;
import java.util.UUID;

import dto.request.UserDtoLogin;
import dto.request.UserDtoRegister;
import dto.request.UserDtoUpdate;
import dto.response.FullUserDtoResponse;
import dto.response.UserDtoResponse;

public interface UserService {
    public void registerAndSave(UserDtoRegister userRegister) throws Exception;
    public UserDtoResponse loginUser(UserDtoLogin UserLogin) throws Exception;
    public User findUserById(UUID id) throws Exception; 
    public void updateUser(UUID id, UserDtoUpdate userUpdate) throws Exception;
    public List<FullUserDtoResponse> findAll();
    public void deleteUser(UUID id) throws Exception;
}
