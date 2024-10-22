package com.academicevents.api.models;

import com.academicevents.api.enums.ROLES;

public class Administrator extends User {
    public Administrator(
            String name,
            String foto,
            String email,
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
