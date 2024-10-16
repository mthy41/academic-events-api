package com.academicevents.api.controllers.user;

import com.academicevents.api.enums.ROLES;
import com.academicevents.api.handlers.CreateUser;
import com.academicevents.api.handlers.LoginUser;
import com.academicevents.api.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/create/user")
    public String SignUp(@RequestBody User user) {
        CreateUser.saveUser(user);

        return "fim";
    }

    @PostMapping("/login")
    public User SingIn(@RequestBody Map<String, String> user){
        // return userData.get("cpf");
        LoginUser.searchUserByCpf(user);
        User teste = new User("matheus", "asdasd", "asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd", ROLES.ADM);
        return teste;
    }
}
