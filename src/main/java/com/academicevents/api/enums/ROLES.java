package com.academicevents.api.enums;

public enum ROLES {
    ADM("administrador", "Pode gerenciar eventos e turmas"),
    PROFESSOR("professor", "Pode gerenciar turmas"),
    PARTICIPANT("participante", "Pode acessar e participar das aulas");

    private final String displayName;
    private final String description;

    ROLES(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
