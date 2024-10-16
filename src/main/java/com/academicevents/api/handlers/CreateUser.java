package com.academicevents.api.handlers;

import com.academicevents.api.DAO.DB;
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
        String userType = user.getRole().getDisplayName();
        String query = "INSERT INTO " + userType + " (cpf, senha, role) " + "VALUES (?, ?, ?)";

        try {
            PreparedStatement statement =  conn.prepareStatement(query);
            statement.setString(1, user.getCpf());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().getDisplayName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
