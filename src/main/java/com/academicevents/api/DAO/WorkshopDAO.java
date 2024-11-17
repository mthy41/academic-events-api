package com.academicevents.api.DAO;

import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import com.academicevents.api.DTO.workshop.WorkshopInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkshopDAO {
    @Autowired
    static Connection conn = DB.getConnection();

    public static void createWorkshop(WorkshopCreateDTO workshop) {

    }

    public static ResultSet searchWorkshopByName(WorkshopCreateDTO workshop) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM minicurso WHERE titulo = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshop.getTitulo());
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result;
            }
            return null;
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkWorkshopExistsByName(WorkshopCreateDTO workshop) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM minicurso WHERE titulo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshop.getTitulo());
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static boolean saveWorkshop(WorkshopCreateDTO workshop) {
        Connection conn = DB.getConnection();
        String query = "INSERT INTO minicurso (codigo, banner, codigo_evento, titulo, descricao, datainicio, datafim, status, qtddparticipantes) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshop.getCodigo());
            statement.setString(2, workshop.getBanner());
            statement.setString(3, workshop.getCodigoEvento());
            statement.setString(4, workshop.getTitulo());
            statement.setString(5, workshop.getDescricao());
            statement.setObject(6, workshop.getDatainicio());
            statement.setObject(7, workshop.getDatafim());
            statement.setBoolean(8, workshop.isStatus());
            statement.setInt(9, workshop.getVagas());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<WorkshopInfoDTO> listWorkshopsByEventName(String eventName) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM minicurso WHERE codigo_evento = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            ArrayList<WorkshopInfoDTO> workshops = new ArrayList<>();
            while(result.next()) {
                WorkshopInfoDTO workshop = new WorkshopInfoDTO(
                        result.getString("titulo"),
                        result.getString("descricao"),
                        result.getString("banner"),
                        result.getDate("datainicio"),
                        result.getDate("datafim"),
                        result.getBoolean("status"),
                        result.getInt("qtddparticipantes")
                );


                workshops.add(workshop);
            }

            return workshops;
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<WorkshopInfoDTO> listWorkshopsByEventCode(String codigoEvento) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM minicurso WHERE codigo_evento = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, codigoEvento);
            ResultSet result = statement.executeQuery();
            ArrayList<WorkshopInfoDTO> workshops = new ArrayList<>();
            while(result.next()) {
                WorkshopInfoDTO workshop = new WorkshopInfoDTO(
                        result.getString("titulo"),
                        result.getString("descricao"),
                        result.getString("banner"),
                        result.getDate("datainicio"),
                        result.getDate("datafim"),
                        result.getBoolean("status"),
                        result.getInt("qtddparticipantes")
                );
                workshops.add(workshop);
            }
            return workshops;
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }
}
