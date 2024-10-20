package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "*")
public class UserHandlers {
    public static ResponseEntity<?> saveUser(User user) {
        ResponseEntity<?> response = null;
        if(!UserDAO.searchUserByCpf(user.getCpf())){
            user.setPassword(HashPasswordHandler.hashPassword(user.getPassword()));
            if (UserDAO.saveUser(user)) {
                response = new ResponseEntity<>("Usuaário criado com sucesso!", HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("Usuário ja existe.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    public static boolean deleteUser(String cpf) {
        if (UserDAO.searchUserByCpf(cpf))    {
            return UserDAO.deleteUser(cpf);
        }
        return false;
    }
}
