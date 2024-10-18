package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.customerrors.WrongCredentialsError;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginUser {
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
        System.out.println("deu certo");
        return true;
    }
}
