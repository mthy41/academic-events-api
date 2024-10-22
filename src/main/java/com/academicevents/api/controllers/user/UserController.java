package com.academicevents.api.controllers.user;

import com.academicevents.api.DTO.user.DeleteUser;
import com.academicevents.api.DTO.user.LoginUserData;
import com.academicevents.api.handlers.LoginUser;
import com.academicevents.api.handlers.UserHandlers;
import com.academicevents.api.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<?> SingIn(@RequestBody LoginUserData user) {
        return LoginUser.getUserByCpf(user);
    }

    @PostMapping("/create/user")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        return UserHandlers.saveUser(user);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUser user) {
        return UserHandlers.deleteUser(user);
    }
}
