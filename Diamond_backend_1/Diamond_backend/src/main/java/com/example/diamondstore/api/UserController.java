package com.example.diamondstore.api;
import com.example.diamondstore.dto.CreateUser;
import com.example.diamondstore.dto.UpdateUser;
import com.example.diamondstore.dto.UserLogin;
import com.example.diamondstore.dto.UserLoginResponse;
import com.example.diamondstore.entities.User;
import com.example.diamondstore.response.ApiResponse;
import com.example.diamondstore.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUser createUser) throws Exception {
        try{
            if(userService.isEmailDuplicated(createUser.getEmail())) {
                return ResponseEntity.ok(ApiResponse.builder()
                        .success(false)
                        .message("Email is duplicated!")
                        .build());
            } else {
                User user = userService.register(createUser.getFullName(), createUser.getEmail(), createUser.getPassword(), createUser.getPhone(), createUser.getGender(), createUser.getDob());
                return ResponseEntity.ok(ApiResponse.builder()
                        .success(true)
                        .message("Create user success!")
                        .data(user)
                        .build());
            }
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Create user fail! Error: " + e.getMessage())
                    .build());
        }
    }

    @GetMapping("/alluser")
    public ResponseEntity<ApiResponse> getAllUser() throws Exception {
        List<User> userList = userService.userList();
        if(userList.isEmpty()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("List user is empty!")
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get All User")
                    .data(userList)
                    .build());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserId(@PathVariable int id) throws Exception {
        Optional<User> user = userService.getUserId(id);
        if(user.isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get User By ID Success")
                    .data(user)
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Get User By ID Fail")
                    .build());
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable int id, @RequestBody UpdateUser updateUser) {
        if(!userService.getUserId(id).isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Update fail! Can't find this user!")
                    .build());
        }else{
            User upUser = userService.updateUser(updateUser, id);
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("User updated successfully")
                    .data(upUser)
                    .build());
        }
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
//        if(!userService.getUserId(id).isPresent()){
//            return ResponseEntity.ok(ApiResponse.builder()
//                    .success(false)
//                    .message("Delete fail! Can't find this user!")
//                    .build());
//        }else {
//            userService.deleteById(id);
//            return ResponseEntity.ok(ApiResponse.builder()
//                    .success(true)
//                    .message("User deleted successfully")
//                    .build());
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLogin userLogin) {
        UserLoginResponse response = userService.login(userLogin.getEmail(), userLogin.getPassword());
        if (response == null) {
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Login fail!")
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Login success!")
                    .data(response)
                    .build());
        }
    }
}
