package services;

import models.User;
import repositories.UserRepository;


import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<User> listAllUsers() {
        return userRepository.listAll();
    }

    @Transactional
    public void addUser(User user) {
        userRepository.persist(user);
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.getEntityManager().merge(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
