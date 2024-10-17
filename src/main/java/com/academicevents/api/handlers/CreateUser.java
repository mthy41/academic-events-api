package com.academicevents.api.handlers;

import com.academicevents.api.DAO.DB;
import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.customerrors.UserAlreadyExistsError;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
@CrossOrigin(origins = "*")
public class CreateUser {
    public static void saveUser(User user) {
        Connection conn = DB.getConnection();
        if(!UserDAO.searchUserByCpf(user.getCpf())){
            UserDAO.saveUser(user);
        }
    }
}
