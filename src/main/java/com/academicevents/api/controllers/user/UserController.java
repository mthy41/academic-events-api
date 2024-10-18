package com.academicevents.api.controllers.user;

import com.academicevents.api.enums.ROLES;
import com.academicevents.api.handlers.UserHandlers;
import com.academicevents.api.handlers.LoginUser;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/login")
    public User SingIn(@RequestBody Map<String, String> user){
        boolean login = LoginUser.getUserByCpf(user);
        User teste = new User("matheus", "asdasd", "asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd", ROLES.ADM);
        return teste;
    }

    @PostMapping("/create/user")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        if(UserHandlers.saveUser(user)) {
            response.put("success", "Usuário cadastrado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("error", "Erro ao cadastrar o usuaário");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<?> deleteUser(@RequestBody Map<String, String> user) {
        Map<String, String> response = new HashMap<>();
        if(UserHandlers.deleteUser(user.get("cpf"))) {
            response.put("success", "Usuário deletado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Erro ao deletar o usuaário");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
