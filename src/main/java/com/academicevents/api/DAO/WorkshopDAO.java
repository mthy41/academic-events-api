package com.academicevents.api.DAO;

import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import com.academicevents.api.DTO.workshop.WorkshopInfoDTO;
import com.academicevents.api.controllers.workshop.SubscribeWorkshopDTO;
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

    public static boolean checkIfWorkshopExistsByName(String nomeWorkshop) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM minicurso WHERE titulo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nomeWorkshop);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkIfUserIsAlreadySubscribed(String cpf, String codigoMinicurso) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM participa_mc WHERE cpf_participante = ? AND codigo_mc = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            statement.setString(2, codigoMinicurso);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static boolean subscribeWorkshop(SubscribeWorkshopDTO workshop, String codgigoMinicurso, String codigoEvento) {
        Connection conn = DB.getConnection();
        String query = "INSERT INTO participa_mc (cpf_participante, codigo_mc, codigo_ev) VALUES (?,?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshop.getCpf());
            statement.setString(2, codgigoMinicurso);
            statement.setString(3, codigoEvento);
            statement.execute();
            return true;
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static String getWorkshopCodeByName(String nomeWorkshop) {
        Connection conn = DB.getConnection();
        String query = "SELECT codigo FROM minicurso WHERE titulo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nomeWorkshop);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result.getString("codigo");
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String getEventCodeByWorkshopName(String nomeWorkshop) {
        Connection conn = DB.getConnection();
        String query = "SELECT codigo_evento FROM minicurso WHERE titulo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nomeWorkshop);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result.getString("codigo_evento");
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean removeSubscription(String workshopCode, String cpf) {
        Connection conn = DB.getConnection();
        String query = "DELETE FROM participa_mc WHERE codigo_mc = ? AND cpf_participante = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshopCode);
            statement.setString(2, cpf);
            statement.execute();
            return true;
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }
}
