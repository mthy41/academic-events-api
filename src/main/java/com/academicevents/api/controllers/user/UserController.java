package com.academicevents.api.controllers.user;

import com.academicevents.api.DTO.event.EventListDTO;
import com.academicevents.api.DTO.event.SearchEventDTO;
import com.academicevents.api.DTO.user.UserCpf;
import com.academicevents.api.DTO.workshop.WorkshopInfoDTO;
import com.academicevents.api.handlers.UserHandlers;
import com.academicevents.api.DTO.user.DeleteUserDTO;
import com.academicevents.api.DTO.user.LoginUserDataDTO;
import com.academicevents.api.handlers.LoginUser;
import com.academicevents.api.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<?> SingIn(@RequestBody LoginUserDataDTO user) { return LoginUser.getUserByCpf(user); }

    @PostMapping("/create/user")
    public ResponseEntity<?> SignUp(@RequestBody User user) {
        return UserHandlers.saveUser(user);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserDTO user) {
        return UserHandlers.deleteUser(user);
    }

    @PutMapping("/update/user")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String> attributesPackage){
        return UserHandlers.updateUserData(attributesPackage);
    }

    @PutMapping("/update/user/password")
    public ResponseEntity<?> updateUserPassword(@RequestBody Map<String, String> passwordPackage){
        return UserHandlers.updateUserPassword(passwordPackage);
    }

    @PostMapping("/user/listsubscribedevents")
    public ResponseEntity<?> listSubscribedEvents(UserCpf cpf) {
        Map<String, ArrayList<EventListDTO>> response = new HashMap<>();
        response.put("subscriptions", UserHandlers.listSubscribedEvents(cpf.getCpf()));
        if (response.get("subscriptions").isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma inscrição encontrada!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user/listsubscribedworkshops")
    public ResponseEntity<?> listSubscribedWorkshop(UserCpf cpf) {
        Map<String, ArrayList<WorkshopInfoDTO>> response = new HashMap<>();
        response.put("subscriptions", UserHandlers.listSubscribedWorkshop(cpf.getCpf()));
        if (response.get("subscriptions") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma inscrição encontrada!");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}