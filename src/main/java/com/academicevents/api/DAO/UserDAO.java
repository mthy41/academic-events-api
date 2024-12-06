package com.academicevents.api.DAO;

import com.academicevents.api.DTO.user.UserProfileDTO;
import com.academicevents.api.DTO.workshop.WorkshopInfoDTO;
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
import java.util.*;

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
        String query = "INSERT INTO " + userType + " (cpf, foto, nome, telefone, email, senha, rua, numero, bairro, cidade, estado, role) " + "VALUES (?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getCpf());
            statement.setString(2, user.getFoto());
            statement.setString(3, user.getNome());
            statement.setString(4, user.getTelefone());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getRua());
            statement.setString(8, user.getNumero());
            statement.setString(9, user.getBairro());
            statement.setString(10, user.getCidade());
            statement.setString(11, user.getEstado());
            statement.setString(12, user.getRole().getDisplayName());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new WrongCredentialsError(e.getMessage());
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
                return UserFactory.buildUser(result);
                }
            } catch (SQLException e ) {throw new RuntimeException(e);}
        }
        return null;
    }

    public static boolean deleteUser(String cpf) {
        System.err.println("Cpf: " + cpf);
        conn = DB.getConnection();
        User bufferedUser = getUserByCpf(cpf);
        String query = "DELETE FROM "+bufferedUser.getRole().getDisplayName()+" WHERE cpf = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            statement.execute();
        } catch (SQLException e ) {
            throw new UserNotFoundError("Usuário não encontrado");
        }
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
            throw new RuntimeException(e);
        } return true;
    }

    public static boolean updateUserPassword(String userType, String userCpf, String newPassword){
        boolean success = false;
        conn = DB.getConnection();
        try {
            String query = "UPDATE "+userType+" SET senha = "+newPassword+"WHERE cpf = "+userCpf;
            PreparedStatement statement = conn.prepareStatement(query);
            if(statement.execute()){ success = true; }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return success;
    }


    public static UserProfileDTO loadUserData(String cpf) {
        conn = DB.getConnection();
        String query = "SELECT nome, email, foto, telefone, cpf, rua, numero, bairro, cidade, estado, role " +
                "FROM administrador WHERE cpf = ? " +
                "UNION " +
                "SELECT nome, email, foto, telefone, cpf, rua, numero, bairro, cidade, estado, role " +
                "FROM participante WHERE cpf = ? " +
                "UNION " +
                "SELECT nome, email, foto, telefone, cpf, rua, numero, bairro, cidade, estado, role " +
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

    public static ArrayList<WorkshopInfoDTO> listSubscribedWorkshop(String cpf) {
        Connection conn = DB.getConnection();
        String query = "SELECT codigo_ev, codigo_mc FROM participa_mc WHERE cpf_participante = ?";
        ArrayList<WorkshopInfoDTO> workshopInfo = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cpf);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                workshopInfo.add(WorkshopDAO.getWorkshopInfoByCode(result.getString("codigo_mc")));
            }
            return workshopInfo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
