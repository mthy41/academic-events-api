package com.academicevents.api.handlers;

import com.academicevents.api.DAO.DB;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class LoginUser {
    public static Optional<? extends User> searchUserByCpf(Map<String, String> user){
        String userRole;
        String userCPF = user.get("cpf");
        Connection conn = DB.getConnection();

        String admQuery = "SELECT * FROM administrador WHERE cpf = '"+userCPF+"'";
        String proQuery = "SELECT * FROM professor WHERE cpf = '"+userCPF+"'";
        String parQuery = "SELECT * FROM participante WHERE cpf = '"+userCPF+"'";
        List<String> queries = Arrays.asList(admQuery, proQuery, parQuery);

        for(String query : queries){
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet result = statement.executeQuery();
                if(result.next()){
                    userRole = result.getString("role");
                    System.out.println(result.getString("nome"));
                    //TODO criar e retornar o objeto de usuário baseado no role do usuário buscado.
                    result.close();
                }
            } catch (SQLException e ) {throw new RuntimeException(e);}
        }
        return Optional.empty();
    }
}
