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
        String query = "INSERT INTO " + userType + " (cpf, nome, rua, numero, bairro, cidade, estado) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement =  conn.prepareStatement(query);
            statement.setString(1, user.getCpf());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRua());
            statement.setString(6, user.getNumero());
            statement.setString(7, user.getBairro());
            statement.setString(8, user.getCidade());
            statement.setString(9, user.getEstado());
            statement.setString(10, user.getRole().getDisplayName());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
