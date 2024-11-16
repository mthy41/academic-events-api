package com.academicevents.api.DAO;

import com.academicevents.api.customerrors.PresenceListNotFoundError;
import com.academicevents.api.customerrors.SubscribeGeneralErrors;
import com.academicevents.api.models.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PresenceListDAO {
    @Autowired
    static Connection conn = DB.getConnection();

    public static boolean savePresenceList(Lecture list) {
        Connection conn = DB.getConnection();
        String query = "INSERT INTO palestra (codigo, codigo_ev, tema) VALUES (?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, list.getCod());
            statement.setString(2, list.codEvento);
            statement.setString(3, "Palestra - "  + list.getEventName());
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean checkIfUserIsSubscribed(String eventCode, String cpfParticipante) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM participa_palestra WHERE codigo_ev = ? AND cpf_participante = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, eventCode);
            statement.setString(2, cpfParticipante);
            System.err.println(eventCode);
            System.err.println(cpfParticipante);
            System.err.println(query);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SubscribeGeneralErrors("Erro ao verificar se o participante está participando do evento.");
        }
    }
}

