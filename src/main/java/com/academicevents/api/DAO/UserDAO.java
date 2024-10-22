package com.academicevents.api.DAO;

import com.academicevents.api.builders.UserFactory;
import com.academicevents.api.customerrors.UserAlreadyExistsError;
import com.academicevents.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public static Optional<? extends User> getUserByCpf(String cpf){
        String admQuery = "SELECT * FROM administrador WHERE cpf = '"+cpf+"'";
        String proQuery = "SELECT * FROM professor WHERE cpf = '"+cpf+"'";
        String parQuery = "SELECT * FROM participante WHERE cpf = '"+cpf+"'";
        List<String> queries = Arrays.asList(admQuery, proQuery, parQuery);

        for(String query : queries){
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet result = statement.executeQuery();
                if(result.next()){
                    return UserFactory.buildUser(result);
                }
            } catch (SQLException e ) {throw new RuntimeException(e);}
        } return Optional.empty();
    }

    public static boolean changeUserName(String userCpf, String newName){
        if(!searchUserByCpf(userCpf)){ return false; }
        String userType = getUserByCpf(userCpf).orElseThrow().getRole().getDisplayName();
        String query = "UPDATE "+userType+" SET nome = "+newName;
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {throw new RuntimeException(e);}
        return true;
    }
}
