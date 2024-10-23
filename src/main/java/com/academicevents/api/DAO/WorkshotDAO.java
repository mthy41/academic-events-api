package com.academicevents.api.DAO;

import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkshotDAO {
    @Autowired
    static Connection conn = DB.getConnection();

    public static void createWorkshop(WorkshopCreateDTO workshop) {

    }

    public static boolean searchWorkshopByName(WorkshopCreateDTO workshop) {
        String query = "SELECT * FROM workshop WHERE codigo_evento = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshop.getCodigoEvento());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (workshop.getTitulo().equals(result.getString("titulo"))) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }
}
