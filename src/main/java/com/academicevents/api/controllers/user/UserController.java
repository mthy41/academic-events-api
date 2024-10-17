package com.academicevents.api.controllers.user;

import com.academicevents.api.enums.ROLES;
import com.academicevents.api.handlers.CreateUser;
import com.academicevents.api.handlers.LoginUser;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/create/user")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        if(CreateUser.saveUser(user)) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "Usuário cadastrado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("Problema no cadastro.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public User SingIn(@RequestBody Map<String, String> user){
        // return userData.get("cpf");
        LoginUser.searchUserByCpf(user);
        User teste = new User("matheus", "asdasd", "asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd", ROLES.ADM);
        return teste;
    }
}
