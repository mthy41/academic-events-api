package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.DTO.user.LoginUserDataDTO;
import com.academicevents.api.DTO.user.UserProfileDTO;
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
        User bufferedUser;
        Map<String, String> response = new HashMap<>();
        if(!UserDAO.searchUserByCpf(user.getCpf())){
            response.put("error", "Credenciais erradas ou invalidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else { bufferedUser = UserDAO.getUserByCpf(user.getCpf()); }

        assert bufferedUser != null;
        if(!bufferedUser.getPassword().equals(HashPasswordHandler.hashPassword(user.getPassword()))){
            response.put("error", "Credenciais erradas ou invalidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        UserProfileDTO loggedUser = UserDAO.loadUserData(user.getCpf());
        response.put("nome", loggedUser.getNome());
        response.put("email", loggedUser.getEmail());
        response.put("foto", loggedUser.getFoto());
        response.put("cpf", loggedUser.getCpf());
        response.put("rua", loggedUser.getRua());
        response.put("numero", loggedUser.getNumero());
        response.put("bairro", loggedUser.getBairro());
        response.put("cidade", loggedUser.getCidade());
        response.put("estado", loggedUser.getEstado());
        response.put("role", loggedUser.getRole());
        response.put("success", "Login realizado com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
