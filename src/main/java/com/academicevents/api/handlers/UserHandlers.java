package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@Service
@CrossOrigin(origins = "*")
public class UserHandlers {
    public static ResponseEntity<?> saveUser(User user) {
        Map<String, String> response = new HashMap<>();

        if(!UserDAO.searchUserByCpf(user.getCpf())){
            user.setPassword(HashPasswordHandler.hashPassword(user.getPassword()));
            response.put("success", "Usuaário criado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("error", "Usuaário já existente");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public static boolean deleteUser(String cpf) {
        if (UserDAO.searchUserByCpf(cpf))    {
            return UserDAO.deleteUser(cpf);
        }
        return false;
    }
}
