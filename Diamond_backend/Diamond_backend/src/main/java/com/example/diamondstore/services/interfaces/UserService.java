package com.example.diamondstore.services.interfaces;

import com.example.diamondstore.dto.UpdateUser;
import com.example.diamondstore.dto.UserLoginResponse;
import com.example.diamondstore.entities.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    boolean isEmailDuplicated(String email);
    User register(String fullName, String email, String password, String phone, String gender, Date dob);
    UserLoginResponse login(String username, String password);
    Optional<User> getUserId(int userid);
    List<User> userList();
    User save(User user);
    User updateUser(UpdateUser updateUser, int id);
    //void deleteById(Long id);
}
