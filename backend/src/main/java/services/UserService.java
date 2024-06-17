package services;

import models.User;

import java.util.List;
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

public interface UserService {
    /**
     * Registra un usuario en la base de datos
     * @param userRegister del tipo UserDtoregister que viene en el cuerpo de la peticion con algunos campos obligatorios que se validan con un Validator
     * @throws InvalidFieldException
     * @throws PasswordNotCoincidentException
     * @throws EntityAlredyExistException
     */
    public void registerAndSave(UserDtoRegister userRegister) throws InvalidFieldException, PasswordNotCoincidentException, EntityAlredyExistException ;
    
    /**
     * Verifica que el usuario exista en la base de datos comparando su username y password
     * @param userLogin del tipo UserDtoLogin con username y password
     * @return UserDtoResponse que representa un user sin los atributos de la base de datos
     * @throws InvalidFieldException
     * @throws IncorrectUsernameOrPasswordExpection
     */
    public UserDtoResponse loginUser(UserDtoLogin userLogin) throws InvalidFieldException, IncorrectUsernameOrPasswordExpection ;

    /**
     * Busca un usuario segun su id en base de datos
     * @param id del tipo UUID 
     * @return un tipo User de base de datos.
     * @throws EntityNotFoundException
     */
    public User findUserById(UUID id) throws EntityNotFoundException ; 

    /**
     * Modifica algun campo de usuario en base de datos
     * @param id del tipo UUID
     * @param userUpdate cuerpo de la peticion donde pueden venir campos null dependiendo los atributos que se quiere modificar
     * @throws InvalidFieldException
     * @throws EntityNotFoundException
     */
    public void updateUser(UUID id, UserDtoUpdate userUpdate) throws InvalidFieldException, EntityNotFoundException;

    /**
     * Busca todos los usuarios en base de datos
     * @return un tipo List con todos los usuarios
     */
    public List<UserDtoResponse> findAll();

    /**
     * Elimina o inavilita un usuario de base de datos
     * @param id del tipo UUID
     * @throws EntityNotFoundException
     */
    public void deleteUser(UUID id) throws EntityNotFoundException;
}
