package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.DTO.user.DeleteUserDTO;
import com.academicevents.api.customerrors.WrongCredentialsError;
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
        System.out.println(user);
        if(!UserDAO.searchUserByCpf(user.getCpf())) {
            UserDAO.saveUser(user);
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
        User bufferedUser;
        if(!UserDAO.searchUserByCpf(user.get("cpf"))){
            throw new WrongCredentialsError("Credenciais erradas ou invalidas");
        } else { bufferedUser = UserDAO.getUserByCpf(user.get("cpf")); }

        if(bufferedUser != null && !bufferedUser
                .getPassword()
                .equals(HashPasswordHandler.hashPassword(user.get("password")))){
            throw new WrongCredentialsError("Credenciais erradas ou invalidas");
        }
        return true;
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

    public static ResponseEntity<?> updateUserPassword(Map<String, String> passwordPackage){
        Map<String, String> response = new HashMap<>();
        String userCpf, oldPassword, newPassword;
        if(!passwordPackage.containsKey("userCpf")){
            response.put("error", "Bad Request!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        userCpf = passwordPackage.get("userCpf");

        if(!passwordPackage.containsKey("oldPassword")){
            response.put("error", "Bad Request!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        oldPassword = passwordPackage.get("oldPassword");

        if(!passwordPackage.containsKey("newPassword")){
            response.put("error", "Bad Request!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        newPassword = passwordPackage.get("newPassword");

        if(!DataComplianceHandler.checkPassword(newPassword)){
            response.put("error", "Erro ao atualizar nova senha: nova senha inserida é inválida.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        User bufferedUser = UserDAO.getUserByCpf(userCpf);
        if(bufferedUser == null){
            response.put("erro", "Erro ao atualizar senha: usuário não encontrado.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        String userType = bufferedUser.getRole().getDisplayName();
        if(!HashPasswordHandler.hashPassword(bufferedUser.getPassword()).equals(oldPassword)){
            response.put("error", "Senha inserida não corresponde à senha do usuário.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        if(!UserDAO.updateUserPassword(userType, userCpf, newPassword)){
            response.put("error", "Erro ao atualizar a senhado usuário.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("success", "Senha atualizada com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
