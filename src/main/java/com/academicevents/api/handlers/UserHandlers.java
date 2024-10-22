package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.customerrors.UserAlreadyExistsError;
import com.academicevents.api.customerrors.WrongCredentialsError;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.xml.crypto.Data;
import java.util.Map;
import java.util.Optional;

@Service
@CrossOrigin(origins = "*")
public class UserHandlers {
    public static boolean saveUser(User user) {
        if(!UserDAO.searchUserByCpf(user.getCpf())){
            user.setPassword(HashPasswordHandler.hashPassword(user.getPassword()));
            return UserDAO.saveUser(user);
        } else {
            throw new UserAlreadyExistsError("Usuario ja cadastrado");
        }
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
