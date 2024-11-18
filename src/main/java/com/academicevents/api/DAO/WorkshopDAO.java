package com.academicevents.api.DAO;

import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import com.academicevents.api.DTO.workshop.WorkshopInfoDTO;
import com.academicevents.api.controllers.workshop.SubscribeWorkshopDTO;
import com.academicevents.api.models.User;
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

    public static boolean searchWorkshopByName(String workshopName) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM minicurso WHERE titulo = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshopName);
            ResultSet result = statement.executeQuery();
            return result.next();
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

        String query = "INSERT INTO minicurso (codigo, banner, codigo_evento, titulo, descricao, datainicio, datafim, status, vagas) VALUES (?,?,?,?,?,?,?,?,?)";
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
                        result.getInt("vagas")
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
                        result.getInt("vagas")
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

    public static void updateVagasDecrease(String workshopCode) {
        Connection conn = DB.getConnection();
        String query = "UPDATE minicurso SET vagas = vagas - 1 WHERE codigo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshopCode);
            statement.execute();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static int getNumbersOfVacancies(String workshopCode) {
        Connection conn = DB.getConnection();
        String query = "SELECT vagas FROM minicurso WHERE codigo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshopCode);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result.getInt("vagas");
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    public static void validateStatusByNumberOfSubscribers(String workshopCode) {
        Connection conn = DB.getConnection();
        int vagas = getNumbersOfVacancies(workshopCode);
        if (vagas == 0) {
            String query = "UPDATE minicurso SET status = false WHERE codigo = ?";
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, workshopCode);
                statement.execute();
            } catch (SQLException e ) {
                throw new RuntimeException(e);
            }
        } else {
            String query = "UPDATE minicurso SET status = true WHERE codigo = ?";
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, workshopCode);
                statement.execute();
            } catch (SQLException e ) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void updateVagasIncrease(String workshopCode) {
        Connection conn = DB.getConnection();
        String query = "UPDATE minicurso SET vagas = vagas + 1 WHERE codigo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshopCode);
            statement.execute();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<User> listSubscribedWorkshop(String codigoEvento, String codigoMinicurso) {
        Connection conn = DB.getConnection();
        String query = "SELECT cpf_participante FROM participa_mc WHERE codigo_ev = ? AND codigo_mc = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, codigoEvento);
            statement.setString(2, codigoMinicurso);
            ResultSet result = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while(result.next()) {
                String cpf = result.getString("cpf_participante");
                System.out.println(cpf);
                User user = UserDAO.getUserByCpf(cpf);
                user.setPassword(""); // gambiarra para não retornar a senha
                users.add(user);
            }
            return users;
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }

    }

    public static WorkshopInfoDTO getWorkshopInfoByCode(String codigoMc) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM minicurso WHERE codigo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, codigoMc);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                WorkshopInfoDTO workshop = new WorkshopInfoDTO(
                        result.getString("titulo"),
                        result.getString("descricao"),
                        result.getString("banner"),
                        result.getDate("datainicio"),
                        result.getDate("datafim"),
                        result.getBoolean("status"),
                        result.getInt("vagas")
                );
                return workshop;
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
