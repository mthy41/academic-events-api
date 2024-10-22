package com.academicevents.api.controllers.user;

import com.academicevents.api.enums.ROLES;
import com.academicevents.api.handlers.UserHandlers;
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
        if(UserHandlers.saveUser(user)) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "Usuário cadastrado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("Problema no cadastro.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> SingIn(@RequestBody Map<String, String> user){
        if(UserHandlers.getUserByCpf(user)){
            return new ResponseEntity<>("Usuário logado com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Erro ao fazer login", HttpStatus.UNAUTHORIZED);
    }
}
