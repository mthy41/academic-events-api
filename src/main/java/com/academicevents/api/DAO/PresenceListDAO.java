package com.academicevents.api.DAO;

import com.academicevents.api.models.PresenceList;
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

    public static boolean savePresenceList(PresenceList list) {
        String query = "INSERT INTO listapresenca (codigo) VALUES (?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, list.cod);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static int getPresenceListLastId() {
        String query = "SELECT codigo FROM listapresenca ORDER BY codigo DESC LIMIT 1";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result.getInt("codigo");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
