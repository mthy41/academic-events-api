package com.academicevents.api.DAO;

import com.academicevents.api.customerrors.UserAlreadyExistsError;
import com.academicevents.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDAO {
    @Autowired
    static Connection conn = DB.getConnection();

    public static boolean searchUserByCpf(String cpf) {
        boolean searchResult;
        String query = "SELECT cpf FROM ("
                + "SELECT cpf FROM administrador "
                + "UNION ALL "
                + "SELECT cpf FROM professor "
                + "UNION ALL "
                + "SELECT cpf FROM participante"
                + ") AS users WHERE cpf = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            ResultSet result = statement.executeQuery();
            searchResult = result.next();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return searchResult;
    }

    public static boolean saveUser(User user) {
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
            throw new UserAlreadyExistsError("Usuario ja cadastrado");
        }
        return true;
    }
}
