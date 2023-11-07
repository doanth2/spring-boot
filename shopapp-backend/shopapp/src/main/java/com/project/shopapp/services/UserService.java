package com.project.shopapp.services;

import com.project.shopapp.models.dtos.UpdateUserDTO;
import com.project.shopapp.models.dtos.UserDTO;
import com.project.shopapp.entities.User;

public interface UserService {
    User createUser(UserDTO userDTO) throws Exception;
    String userLogin(String phoneNumber, String password, Long roleId) throws Exception;
    User getUserDetailsFromToken(String token) throws Exception;
    User updateUser(Long userId, UpdateUserDTO updatedUserDTO) throws Exception;
    void deleteUser(Long id);
}
