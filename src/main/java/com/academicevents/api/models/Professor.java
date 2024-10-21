package com.academicevents.api.models;

import com.academicevents.api.enums.ROLES;

public class Professor extends User {
    public Professor(
            String name,
            String email,
            String foto,
            String password,
            String cpf,
            String rua,
            String numero,
            String bairro,
            String cidade,
            String estado,
            ROLES role) {
        super(name, foto, email, password, cpf, rua, numero, bairro, cidade, estado, role);
    }
}
