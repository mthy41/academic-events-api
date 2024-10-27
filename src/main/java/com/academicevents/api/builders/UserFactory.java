package com.academicevents.api.builders;
import com.academicevents.api.enums.ROLES;
import com.academicevents.api.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFactory {
    public static User buildUser(ResultSet userSet){
        try{
            String userRoleStr = userSet.getString("role");
            ROLES userRole = switch (userRoleStr) {
                case "administrador" -> ROLES.ADM;
                case "participante" -> ROLES.PARTICIPANT;
                case "professor" -> ROLES.PROFESSOR;
                default -> null;
            };

            return new User(
                    userSet.getString("nome"),
                    userSet.getString("foto"),
                    userSet.getString("email"),
                    userSet.getString("senha"),
                    userSet.getString("cpf"),
                    userSet.getString("rua"),
                    userSet.getString("numero"),
                    userSet.getString("bairro"),
                    userSet.getString("cidade"),
                    userSet.getString("estado"),
                    userRole);

        } catch (SQLException e) {throw new RuntimeException(e);}
    }
}
