package com.academicevents.api.models;

public class PresenceList {
    public String cod;
    public String codEvento;

    public PresenceList(String cod, String codEvento) {
        this.codEvento = codEvento;
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(String codEvento) {
        this.codEvento = codEvento;
    }
}
