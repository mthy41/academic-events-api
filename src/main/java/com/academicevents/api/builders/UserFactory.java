package com.academicevents.api.builders;

import com.academicevents.api.enums.ROLES;

import com.academicevents.api.models.Administrator;
import com.academicevents.api.models.Participant;
import com.academicevents.api.models.Professor;

import com.academicevents.api.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserFactory {
    public static Optional<? extends User> buildUser(ResultSet userSet){
        try{
            String userRoleStr = userSet.getString("role");
            return switch (userRoleStr) {
                case "administrador" -> Optional.of(new Administrator(
                        userSet.getString("nome"),
                        userSet.getString("email"),
                        userSet.getString("senha"),
                        userSet.getString("cpf"),
                        userSet.getString("rua"),
                        userSet.getString("numero"),
                        userSet.getString("bairro"),
                        userSet.getString("cidade"),
                        userSet.getString("estado"),
                        ROLES.ADM
                ));
                case "participante" -> Optional.of(new Participant(
                        userSet.getString("nome"),
                        userSet.getString("email"),
                        userSet.getString("senha"),
                        userSet.getString("cpf"),
                        userSet.getString("rua"),
                        userSet.getString("numero"),
                        userSet.getString("bairro"),
                        userSet.getString("cidade"),
                        userSet.getString("estado"),
                        ROLES.PARTICIPANT
                ));
                case "professor" -> Optional.of(new Professor(
                        userSet.getString("nome"),
                        userSet.getString("email"),
                        userSet.getString("senha"),
                        userSet.getString("cpf"),
                        userSet.getString("rua"),
                        userSet.getString("numero"),
                        userSet.getString("bairro"),
                        userSet.getString("cidade"),
                        userSet.getString("estado"),
                        ROLES.PROFESSOR
                ));
                default -> Optional.empty();
            };
        } catch (SQLException e) {throw new RuntimeException(e);} //TODO jogar a exception certa em customeerros
    }
}
