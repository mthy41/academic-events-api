package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginUser {
    public static ResponseEntity<?> getUserByCpf(Map<String, String> user){
        Optional<? extends User> bufferedUser;
        Map<String, String> response = new HashMap<>();
        if(!UserDAO.searchUserByCpf(user.get("cpf"))){
            response.put("error", "Credenciais erradas ou invalidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else { bufferedUser = UserDAO.getUserByCpf(user.get("cpf")); }

        if(!bufferedUser.map(User::getPassword).orElseThrow().equals(HashPasswordHandler.hashPassword(user.get("password")))){
            response.put("error", "Credenciais erradas ou invalidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        response.put("success", "Login realizado com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
