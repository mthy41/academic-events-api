package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.customerrors.UserAlreadyExistsError;
import com.academicevents.api.customerrors.WrongCredentialsError;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

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
}
