package com.academicevents.api.DAO;

import com.academicevents.api.DTO.EventCheckinDataDTO;
import com.academicevents.api.DTO.event.EventListDTO;
import com.academicevents.api.DTO.event.EventDTO;
import com.academicevents.api.customerrors.CheckinEventError;
import com.academicevents.api.customerrors.EventNotExistsError;
import com.academicevents.api.customerrors.ListingEventsError;
import com.academicevents.api.customerrors.SubscribeGeneralErrors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class EventDAO {
    @Autowired
    static Connection connection = DB.getConnection();

    public static boolean saveEvent(EventDTO event) {
        Connection connection = DB.getConnection();
        String uuid = UUID.randomUUID().toString().trim();

        String queryEvento = "INSERT INTO evento (codigo, nome, datainicio, datafim, instituicao, rua, numero, bairro, cidade, estado, banner, miniatura) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(queryEvento);

            statement.setString(1, uuid);
            statement.setString(2, event.getNome());
            statement.setDate(3, event.getDatainicio());
            statement.setDate(4, event.getDatafim());
            statement.setString(5, event.getInstituicao());
            statement.setString(6, event.getRua());
            statement.setString(7, event.getNumero());
            statement.setString(8, event.getBairro());
            statement.setString(9, event.getCidade());
            statement.setString(10, event.getEstado());
            statement.setString(11, event.getBanner());
            statement.setString(12, event.getMiniatura());

            statement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean searchEventByName(String name) {
        Connection connection = DB.getConnection();
        boolean searchResult;
        String query = "SELECT nome FROM evento WHERE nome = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            searchResult = result.next();
        } catch (SQLException e ) {
            throw new EventNotExistsError("Evento inexistente. Verifique o nome do evento e tente novamente.");
        }
        return searchResult;
    }

    public static String searchCodeByName(String name) {
        Connection connection = DB.getConnection();
        String query = "SELECT codigo FROM evento WHERE nome = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result.getString("codigo");
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public static boolean deleteEvent(String nome) {
        String query = "DELETE FROM evento WHERE nome = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nome);
            statement.execute();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static EventDTO getEventByName(String nome) {
        String query = "SELECT * FROM evento WHERE nome = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nome);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return new EventDTO(
                        result.getString("nome"),
                        result.getString("codigo"),
                        result.getString("instituicao"),
                        result.getDate("datainicio"),
                        result.getDate("datafim"),
                        result.getString("banner"),
                        result.getString("miniatura"),
                        result.getString("rua"),
                        result.getString("numero"),
                        result.getString("bairro"),
                        result.getString("cidade"),
                        result.getString("estado"));
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static ArrayList<EventListDTO> listEvents() {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM evento";
        ArrayList<EventListDTO> events = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                EventListDTO event = new EventListDTO(
                        result.getString("nome"),
                        result.getString("instituicao"),
                        result.getDate("dataInicio"),
                        result.getDate("dataFim"),
                        result.getString("banner"),
                        result.getString("miniatura"),
                        result.getString("rua"),
                        result.getString("numero"),
                        result.getString("bairro"),
                        result.getString("cidade"),
                        result.getString("estado")
                );
                events.add(event);
            }
        } catch (SQLException e ) {
            System.out.println(e);
            throw new ListingEventsError("Erro na listagem dos eventos");
        }
        return events;
    }

    public static boolean checkIfEventExistsByName(String nomeEvento) {
        Connection connection = DB.getConnection();
        String query = "SELECT * FROM evento WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nomeEvento);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new EventNotExistsError("Erro checando existência do evento pelo nome.");
        }
    }

    public static boolean subscribeEvent(String eventCode, String cpfParticipante, String participante) {
        Connection conn = DB.getConnection();
        System.out.println("Codigo do evento: " + eventCode + " CPF: " + cpfParticipante);
        String query = "INSERT INTO lpevento (codigo_evento, cpf_participante, participante) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, eventCode);
            statement.setString(2, cpfParticipante);
            statement.setString(3, participante);
            statement.execute();
        } catch (SQLException e) {
            throw new SubscribeGeneralErrors("Erro ao registrar participante no evento.");
        }
        return true;
    }

    public static String getLpEventCode(String eventCode) {
        Connection conn = DB.getConnection();
        String query = "SELECT codigo FROM lpevento WHERE codigo_evento = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, eventCode);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result.getString("codigo");
            }
        } catch (SQLException e) {
            throw new SubscribeGeneralErrors("Erro buscando código da lista de presença do evento.");
        }
        return null;
    }

    public static boolean checkIfLectureExistsByEventCode(String eventCode) {
        Connection conn = DB.getConnection();
        String query = "SELECT * FROM palestra WHERE codigo_ev = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, eventCode);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean checkinEventWithCPFAndEventName(String codEvento, String CPFparticipante, String codigoPalestra) {
        Connection conn = DB.getConnection();
        String query = "INSERT INTO participa_palestra (codigo_ev, cpf_participante, codigo_palestra) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, codEvento);
            preparedStatement.setString(2, CPFparticipante);
            preparedStatement.setString(3, codigoPalestra);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            throw new CheckinEventError("Erro inserindo o participante na lista de checkin");
        }
    }

    public static String getLectureCode(String eventCode) {
        Connection conn = DB.getConnection();
        String query = "SELECT codigo FROM palestra WHERE codigo_ev = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, eventCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("codigo");
            }
        } catch (SQLException e) {
            throw new CheckinEventError("Erro buscando o código da palestra");
        }
        return null;
    }
}
