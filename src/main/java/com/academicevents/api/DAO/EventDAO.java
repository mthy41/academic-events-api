package com.academicevents.api.DAO;

import com.academicevents.api.customerrors.ListingEventsError;
import com.academicevents.api.models.Event;
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

    public static boolean saveEvent(Event event) {
        String uuid = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();

        String queryListaPresenca = "INSERT INTO lpevento (codigo, codigo_evento) VALUES (?,?)";
        String queryEvento = "INSERT INTO evento (codigo, nome, datainicio, datafim, instituicao, rua, numero, bairro, cidade, estado) VALUES (?,?,?,?,?,?,?,?,?,?)";
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

            statement.execute();

            PreparedStatement statement2 = connection.prepareStatement(queryListaPresenca);
            statement2.setString(1, uuid2);
            statement2.setString(2, uuid);
            statement2.execute();

        } catch (SQLException e) {
            return false;
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
