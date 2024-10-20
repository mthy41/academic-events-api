package com.academicevents.api.controllers.user;

import com.academicevents.api.handlers.LoginUser;
import com.academicevents.api.handlers.UserHandlers;
import com.academicevents.api.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<?> SingIn(@RequestBody @Schema(description = "CPF e senha do usuario", example = "{\"cpf\": \"12345678900\", \"password\": \"123456\"}")
                Map<String, String> user) {
        Map<String, String> response = new HashMap<>();
        boolean login = LoginUser.getUserByCpf(user);
        if (login) {
            response.put("success", "Usuário logado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Erro ao logar o usuaário");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/user")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        if(UserHandlers.saveUser(user)) {
            response.put("success", "Usuário cadastrado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("error", "Erro ao cadastrar o usuário");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<Map<String, String>> deleteUser(
            @RequestBody @Schema(description = "CPF do usuário para ser deletado.", example ="{\"cpf\": \"12345678900\"}")
            Map<String, String> user) {
        Map<String, String> response = new HashMap<>();

        if (UserHandlers.deleteUser(user.get("cpf"))) {
            response.put("success", "Usuário deletado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("error", "Erro ao deletar o usuário");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
