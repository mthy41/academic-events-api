package com.academicevents.api;

import com.academicevents.api.DTO.user.DeleteUserDTO;
import com.academicevents.api.DTO.user.LoginUserDataDTO;
import com.academicevents.api.enums.ROLES;
import com.academicevents.api.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc ;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void LoginWithCorrectCredentialsShouldReturnSuccess() throws Exception {
        LoginUserDataDTO user = new LoginUserDataDTO("43394170841", "adminadmin");

        mockMvc.perform(post("/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(jsonPath("success").value("Login realizado com sucesso!"))
                .andExpect(status().isOk());
    }


    @Test
    void loginWithIncorrectCpfShouldReturnError() throws Exception {
        LoginUserDataDTO user = new LoginUserDataDTO("99999999999", "adminadmin");

        mockMvc.perform(post("/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(jsonPath("error").value("Credenciais erradas ou invalidas"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void loginWithIncorrectPasswordShouldReturnError() throws Exception {
        LoginUserDataDTO user = new LoginUserDataDTO("43394170841", "adminadmin1");

        mockMvc.perform(post("/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(jsonPath("error").value("Credenciais erradas ou invalidas"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void attemptCreateUserWithValidDataShouldReturnSuccess() throws Exception {
        User user = new User("SPRINGTESTCASE", "springtestcase", "springtestcase@springtestcase.com", "spring123123", "99999999999", "springtestcase", "99", "springtestcase", "springtestcase", "springtestcase", ROLES.ADM);

        mockMvc.perform(post("/create/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(jsonPath("success").value("Usuário criado com sucesso!"))
                .andExpect(status().isOk());
    }

    @Test
    void attemptDeleteExistingUserWithCorrectDataShouldReturnSuccess() throws Exception {
        DeleteUserDTO user = new DeleteUserDTO("99999999999");

        mockMvc.perform(delete("/delete/user")
                .contentType("application/json")
                .accept("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(jsonPath("success").value("Usuário deletado com sucesso!"))
                .andExpect(status().isOk());
    }

    @Test
    void attemptDeleteNonExistingUserWithCorrectDataShouldReturnError() throws Exception {
        DeleteUserDTO user = new DeleteUserDTO("123123");

        mockMvc.perform(delete("/delete/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(jsonPath("error").value("Usuário não encontrado"))
                .andExpect(status().isNotFound());
    }

    @Test
    void attempoDeleteUserWithNoCpf() throws Exception {
        DeleteUserDTO user = new DeleteUserDTO("");

        mockMvc.perform(delete("/delete/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(jsonPath("error").value("Entrada de dados inválida ou nula"))
                .andExpect(status().isBadRequest());
    }
}