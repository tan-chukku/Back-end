package com.example.diamondstore.services;

import com.example.diamondstore.dto.UpdateUser;
import com.example.diamondstore.dto.UserLoginResponse;
import com.example.diamondstore.entities.User;
import com.example.diamondstore.repositories.RoleRepository;
import com.example.diamondstore.repositories.UserRepository;
import com.example.diamondstore.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(String fullName, String email, String password, String phone, String gender, Date dob) {
        User saveUser = userRepository.save(User.builder()
             .fullName(fullName)
             .email(email)
             .password(bCryptPasswordEncoder.encode(password))
             .phone(phone)
             .gender(gender)
             .dob(dob)
             .roleid(roleRepository.findRoleByRoleid(5))
             .status("active")
             .point(0)
             .build());
        return saveUser;
    }

    @Override
    public boolean isEmailDuplicated(String email) {
        try {
            return (userRepository.findUserByEmail(email) != null);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserLoginResponse login(String email, String password){
        User loginUser = userRepository.findUserByEmail(email);
        if(loginUser == null || !bCryptPasswordEncoder.matches(password,userRepository.findUserByEmail(email).getPassword())){
            return null;
        }else{
            return new UserLoginResponse(loginUser.getUserId(), loginUser.getEmail());
        }
    }

    @Override
    public Optional<User> getUserId(int id) {
         return userRepository.findById(id);
    }

    @Override
    public List<User> userList() {
       return userRepository.findAll();
    }

    @Override
    public User updateUser(UpdateUser updateUser, int id) {

        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        User saveUser = getUserId(id).get();
        saveUser.setEmail(updateUser.getEmail());
        saveUser.setPassword(b.encode(updateUser.getPassword()));
        saveUser.setPhone(updateUser.getPhone());
        saveUser.setGender(updateUser.getGender());
        saveUser.setFullName(updateUser.getFullName());
        saveUser.setDob(updateUser.getDob());
        //saveUser.setUpdatedAt(new Date());

        return userRepository.save(saveUser);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public void deleteById(Long id) {
//        userRepository.deleteById(id);
//    }
}
