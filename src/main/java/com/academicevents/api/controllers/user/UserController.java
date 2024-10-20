package com.academicevents.api.controllers.user;

import com.academicevents.api.handlers.LoginUser;
import com.academicevents.api.handlers.UserHandlers;
import com.academicevents.api.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
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
        return LoginUser.getUserByCpf(user);
    }

    @PostMapping("/create/user")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        return UserHandlers.saveUser(user);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<?> deleteUser(@RequestBody @Schema(description = "CPF do usuário para ser deletado.", example ="{\"cpf\": \"12345678900\"}") Map<String, String> user) {
        return UserHandlers.deleteUser(user.get("cpf"));
    }
}
