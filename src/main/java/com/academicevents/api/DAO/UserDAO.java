package com.academicevents.api.DAO;

import com.academicevents.api.builders.UserFactory;
import com.academicevents.api.customerrors.UserNotFoundError;
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
        String query = "INSERT INTO " + userType + " (cpf, foto, nome, email, senha, rua, numero, bairro, cidade, estado, role) " + "VALUES (?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement =  conn.prepareStatement(query);
            statement.setString(1, user.getCpf());
            statement.setString(2, user.getFoto());
            statement.setString(3, user.getNome());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRua());
            statement.setString(7, user.getNumero());
            statement.setString(8, user.getBairro());
            statement.setString(9, user.getCidade());
            statement.setString(10, user.getEstado());
            statement.setString(11, user.getRole().getDisplayName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
//            return false;
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

    public static boolean deleteUser(String cpf) {
        String query = "DELETE FROM administrador WHERE cpf = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            statement.execute();
        } catch (SQLException e ) {
            throw new UserNotFoundError("Usuário não encontrado");
        }
        return true;
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

    public static boolean changeUserEmail(String userCpf, String newEmail){
        if(!searchUserByCpf(userCpf)){ return false; }
        String userType = getUserByCpf(userCpf).orElseThrow().getRole().getDisplayName();
        String query = "UPDATE "+userType+" SET email = "+newEmail;
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.execute();
        } catch(SQLException e){throw new RuntimeException(e);}
        return true;
    }
}
