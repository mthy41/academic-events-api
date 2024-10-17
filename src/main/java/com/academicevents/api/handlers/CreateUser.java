package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.customerrors.UserAlreadyExistsError;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "*")
public class CreateUser {
    public static boolean saveUser(User user) {
        if(!UserDAO.searchUserByCpf(user.getCpf())){
            return UserDAO.saveUser(user);
        } else {
            throw new UserAlreadyExistsError("Usuario ja cadastrado");
        }
    }
}
