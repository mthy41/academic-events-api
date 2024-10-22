package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.DTO.user.LoginUserDataDTO;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginUser {
    public static ResponseEntity<?> getUserByCpf(LoginUserDataDTO user){
        Optional<? extends User> bufferedUser;
        Map<String, String> response = new HashMap<>();
        if(!UserDAO.searchUserByCpf(user.getCpf())){
            response.put("error", "Credenciais erradas ou invalidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else { bufferedUser = UserDAO.getUserByCpf(user.getCpf()); }

        if(!bufferedUser.map(User::getPassword).orElseThrow().equals(HashPasswordHandler.hashPassword(user.getPassword()))){
            response.put("error", "Credenciais erradas ou invalidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        response.put("success", "Login realizado com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
