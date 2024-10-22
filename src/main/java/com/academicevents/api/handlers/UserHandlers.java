package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.DTO.user.DeleteUserDTO;
import com.academicevents.api.customerrors.UserAlreadyExistsError;
import com.academicevents.api.customerrors.WrongCredentialsError;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.xml.crypto.Data;
import java.util.Map;
import java.util.Optional;

import java.util.HashMap;
import java.util.Map;

@Service
@CrossOrigin(origins = "*")
public class UserHandlers {

    public static ResponseEntity<?> saveUser(User user) {
        Map<String, String> response = new HashMap<>();
        user.setPassword(HashPasswordHandler.hashPassword(user.getPassword()));

        if(!UserDAO.searchUserByCpf(user.getCpf()) && UserDAO.saveUser(user)) {
            response.put("success", "Usuário criado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("error", "Usuário já existente");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public static ResponseEntity<?> deleteUser(DeleteUserDTO user) {
        Map<String, String> response = new HashMap<>();
        if (UserDAO.searchUserByCpf(user.getCpf()) && UserDAO.deleteUser(user.getCpf()))    {
            response.put("success", "Usuário deletado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("error", "Erro ao deletar o usuário");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static boolean getUserByCpf(Map<String, String> user){
        Optional<? extends User> bufferedUser;
        if(!UserDAO.searchUserByCpf(user.get("cpf"))){
            throw new WrongCredentialsError("Credenciais erradas ou invalidas");
        } else { bufferedUser = UserDAO.getUserByCpf(user.get("cpf")); }

        if(!bufferedUser.map(User::getPassword)
                .orElseThrow()
                .equals(HashPasswordHandler.hashPassword(user.get("password")))){
            throw new WrongCredentialsError("Credenciais erradas ou invalidas");
        }
        return true;
    }

    public static boolean updateUserByCpf(String userCpf, Map<String, String> attributesPackage){
        if(!UserDAO.searchUserByCpf(userCpf)) { return false; }

        if(attributesPackage.containsKey("nome")){
            String userName = attributesPackage.get("nome");
            if(!DataComplianceHandler.checkUserName(userName)){ return false; }
        }

        return false;
    }
}
