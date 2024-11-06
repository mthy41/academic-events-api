package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.DTO.user.DeleteUserDTO;
import com.academicevents.api.customerrors.*;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

@Service
@CrossOrigin(origins = "*")
public class UserHandlers {

    public static ResponseEntity<?> saveUser(User user) {
        Map<String, String> response = new HashMap<>();

        user.setPassword(HashPasswordHandler.hashPassword(user.getPassword()));
        if(UserDAO.searchUserByCpf(user.getCpf())) {
            throw new UserAlreadyExistsError("Usuário já existente");
        }

        if(UserDAO.saveUser(user)) {
            response.put("success", "Usuário criado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new RuntimeException("Erro ao persistir usuário.");
    }

    public static ResponseEntity<?> deleteUser(DeleteUserDTO user) {
        Map<String, String> response = new HashMap<>();

        if (user.getCpf().length() == 0 || user.getCpf().equals("") || user.getCpf() == null) {
            throw new InvalidInputDataError("CPF nulo ou inválido.");
        }

        if (!UserDAO.searchUserByCpf(user.getCpf())) {
            throw new UserNotFoundError("Usuário não encontrado");
        }

        if (!UserDAO.deleteUser(user.getCpf()))    {
            throw new DeletingUserError("Erro ao deletar o usuário");
        }

        response.put("success", "Usuário deletado com sucesso!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static boolean checkIfUserExistsByCpf(String userCpf){
        return UserDAO.searchUserByCpf(userCpf);
    }

    public static ResponseEntity<?> updateUserData(Map<String, String> attributesPackage){
        Map<String, String> response = new HashMap<>();

        List<String> validKeys = Arrays.asList("userCpf", "nome", "email", "rua", "numero", "bairro", "cidade", "estado");
        if(!new HashSet<>(validKeys).containsAll(attributesPackage.keySet())){
           response.put("error", "Ouve um erro ao serializar os dados.");
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Map<String, String> cleanedPackage = new HashMap<>();
        for(String key : attributesPackage.keySet()){
            String value = attributesPackage.get(key);

            //manual check for special attributes.
            if(key.equals("nome")){
                if(!DataComplianceHandler.checkUserName(value)){
                    response.put("error", "Nome inserido contém caracteres inválidos.");
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                } cleanedPackage.put(key, value); continue; }
            if(key.equals("userCpf")){
                if(!DataComplianceHandler.checkCpf(value)){
                    response.put("error", "CPF inserido é inválido.");
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }
                if(!UserDAO.searchUserByCpf(value)){
                    response.put("error", "CPF inserido não pertence à nenhum usuário.");
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                } continue;
            }

            if(!value.isBlank()){ cleanedPackage.put(key, value); }
        }
        String userCpf = attributesPackage.get("userCpf");

        if(!UserDAO.updateUserData(userCpf, cleanedPackage)){
            response.put("error", "Erro ao atualizar os dados.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("success", "Dados atualizados com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static boolean checkIfEmailBelongsToUser(String cpfParticipante, String emailParticipante) {
        User user = UserDAO.getUserByCpf(cpfParticipante);
        return user.getEmail().equals(emailParticipante);
    }
}
