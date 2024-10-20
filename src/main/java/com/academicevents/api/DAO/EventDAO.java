package com.academicevents.api.DAO;

import com.academicevents.api.customerrors.ListingEventsError;
import com.academicevents.api.models.Event;
import com.academicevents.api.models.PresenceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class EventDAO {
    @Autowired
    static Connection connection = DB.getConnection();

    public static boolean saveEvent(Event event) {
        int eventNextCod = EventDAO.getEventLastId() + 1;
        int presenceListNextCod = PresenceListDAO.getPresenceListLastId() + 1;
        PresenceList presenceList = new PresenceList(String.valueOf(presenceListNextCod));
        PresenceListDAO.savePresenceList(presenceList);
        String query = "INSERT INTO evento (codigo, codigo_listapresenca, nome, datainicio, datafim, instituicao, rua, numero, bairro, cidade, estado) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(eventNextCod));
            statement.setString(2, presenceList.cod);
            statement.setString(3, event.getNome());
            statement.setDate(4, event.getDatainicio());
            statement.setDate(5, event.getDatafim());
            statement.setString(6, event.getInstituicao());
            statement.setString(7, event.getRua());
            statement.setString(8, event.getNumero());
            statement.setString(9, event.getBairro());
            statement.setString(10, event.getCidade());
            statement.setString(11, event.getEstado());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean searchEventByName(String name) {
        boolean searchResult;
        String query = "SELECT nome FROM evento WHERE nome = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            searchResult = result.next();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return searchResult;
    }

    public static int getEventLastId() {
        String query = "SELECT codigo FROM evento ORDER BY codigo DESC LIMIT 1";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result.getInt("codigo");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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

    public static Event getEventByName(String nome) {
        String query = "SELECT * FROM evento WHERE nome = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nome);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return new Event(
                        result.getString("nome"),
                        result.getString("instituicao"),
                        result.getDate("datainicio"),
                        result.getDate("datafim"),
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

    public static ArrayList<Event> listEvents() {
        String query = "SELECT * FROM evento";
        ArrayList<Event> events = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                events.add(new Event(
                        result.getString("nome"),
                        result.getString("instituicao"),
                        result.getDate("datainicio"),
                        result.getDate("datafim"),
                        result.getString("rua"),
                        result.getString("numero"),
                        result.getString("bairro"),
                        result.getString("cidade"),
                        result.getString("estado")));
            }
        } catch (SQLException e ) {
            throw new ListingEventsError("Erro na listagem dos eventos");
        }
        return events;
    }
}
