package com.academicevents.api.controllers.user;

import com.academicevents.api.handlers.UserHandlers;
import com.academicevents.api.DTO.user.DeleteUserDTO;
import com.academicevents.api.DTO.user.LoginUserDataDTO;
import com.academicevents.api.handlers.LoginUser;
import com.academicevents.api.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<?> SingIn(@RequestBody LoginUserDataDTO user) { return LoginUser.getUserByCpf(user); }

    @PostMapping("/create/user")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        return UserHandlers.saveUser(user);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserDTO user) {
        return UserHandlers.deleteUser(user);
    }

    @PutMapping("/update/user")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String> attributesPackage){
        return UserHandlers.updateUserData(attributesPackage);
    }

    @PutMapping("/update/user/password")
    public ResponseEntity<?> updateUserPassword(@RequestBody Map<String, String> passwordPackage){
        return UserHandlers.updateUserPassword(passwordPackage);
    }
}