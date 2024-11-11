package com.academicevents.api.DAO;

import com.academicevents.api.DTO.user.UserProfileDTO;
import com.academicevents.api.builders.UserFactory;
import com.academicevents.api.customerrors.UserNotFoundError;
import com.academicevents.api.customerrors.WrongCredentialsError;
import com.academicevents.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserDAO {
    @Autowired
    static Connection conn = DB.getConnection();

    public static boolean searchUserByCpf(String cpf) {
        conn = DB.getConnection();

        boolean searchResult;
        String query = "SELECT cpf FROM ("
                + "SELECT cpf FROM administrador "
                + "UNION ALL "
                + "SELECT cpf FROM professor "
                + "UNION ALL "
                + "SELECT cpf FROM participante"
                + ") AS users WHERE cpf = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            ResultSet result = statement.executeQuery();
            searchResult = result.next();
            DB.closeConnection();
        } catch (SQLException e ) {
            throw new WrongCredentialsError("Credenciais erradas ou invalidas");
        }
        return searchResult;
    }

    public static boolean saveUser(User user) {
        conn = DB.getConnection();

        String userType = user.getRole().getDisplayName();
        System.out.println(userType);
        String phoneQuery = "";
        String query = "INSERT INTO " + userType + " (cpf, foto, nome, email, senha, rua, numero, bairro, cidade, estado, role) " + "VALUES (?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement statement =  conn.prepareStatement(query);
                statement.setString(1, user.getCpf());
                statement.setString(2, user.getFoto());
                statement.setString(3, user.getNome());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPassword());
                statement.setString(6, user.getRua());
                statement.setString(7, user.getNumero());
                statement.setString(8, user.getBairro());
                statement.setString(9, user.getCidade());
                statement.setString(10, user.getEstado());
                statement.setString(11, user.getRole().getDisplayName());
                statement.execute();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        System.out.println(userType);

            if (userType.equals("administrador")) {

                phoneQuery = "INSERT INTO telefoneadmin (telefone, cpf_admin) VALUES (?,?)";
            } else if(userType.equals("professor")) {
                phoneQuery = "INSERT INTO telefoneprof (telefone, cpf_prof) VALUES (?,?)";
            }

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(phoneQuery);
                preparedStatement.setString(1, user.getTelefone());
                preparedStatement.setString(2, user.getCpf());
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public static User getUserByCpf(String cpf){
        conn = DB.getConnection();
        String admQuery = "SELECT * FROM administrador WHERE cpf = '"+cpf+"'";
        String proQuery = "SELECT * FROM professor WHERE cpf = '"+cpf+"'";
        String parQuery = "SELECT * FROM participante WHERE cpf = '"+cpf+"'";
        List<String> queries = Arrays.asList(admQuery, proQuery, parQuery);

        for(String query : queries){
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet result = statement.executeQuery();
                if(result.next()){
                DB.closeConnection();
                return UserFactory.buildUser(result);
                }
            } catch (SQLException e ) {throw new RuntimeException(e);}
        }
        DB.closeConnection();
        return null;
    }

    public static boolean deleteUser(String cpf) {
        System.err.println("Cpf: " + cpf);
        conn = DB.getConnection();
        String query = "DELETE FROM administrador WHERE cpf = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            statement.execute();
            DB.closeConnection();
        } catch (SQLException e ) {
            throw new UserNotFoundError("Usuário não encontrado");
        }
        DB.closeConnection();
        return true;
    }

    public static boolean updateUserData(String userCpf, Map<String, String> attPackage){
        String userType = Objects.requireNonNull(getUserByCpf(userCpf)).getRole().getDisplayName();
        conn = DB.getConnection();
        try {
            for(String k : attPackage.keySet()) {
                String query = "UPDATE "+userType+" SET "+k+" = '"+attPackage.get(k)+"' WHERE cpf = '"+userCpf+"';";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.execute();
            }
        } catch (SQLException e) {
            DB.closeConnection();
            throw new RuntimeException(e);
        } DB.closeConnection(); return true;
    }

    public static boolean updateUserPassword(String userType, String userCpf, String newPassword){
        boolean success = false;
        conn = DB.getConnection();
        try {
            String query = "UPDATE "+userType+" SET senha = "+newPassword+"WHERE cpf = "+userCpf;
            PreparedStatement statement = conn.prepareStatement(query);
            if(statement.execute()){ success = true; }
        } catch (SQLException e) {
            DB.closeConnection();
            throw new RuntimeException(e);
        }
        DB.closeConnection();
        return success;
    }


    public static UserProfileDTO loadUserData(String cpf) {
        conn = DB.getConnection();
        String query = "SELECT nome, email, foto, cpf, rua, numero, bairro, cidade, estado, role " +
                "FROM administrador WHERE cpf = ? " +
                "UNION " +
                "SELECT nome, email, foto, cpf, rua, numero, bairro, cidade, estado, role " +
                "FROM participante WHERE cpf = ? " +
                "UNION " +
                "SELECT nome, email, foto, cpf, rua, numero, bairro, cidade, estado, role " +
                "FROM professor WHERE cpf = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            statement.setString(2, cpf);
            statement.setString(3, cpf);

            ResultSet result = statement.executeQuery();
            result.next();

            return  new UserProfileDTO(
                    result.getString("nome"),
                    result.getString("email"),
                    result.getString("foto"),
                    result.getString("telefone"),
                    result.getString("cpf"),
                    result.getString("rua"),
                    result.getString("numero"),
                    result.getString("bairro"),
                    result.getString("cidade"),
                    result.getString("estado"),
                    result.getString("role"));
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }
}
