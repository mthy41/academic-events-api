package com.academicevents.api.models;

public class Lecture {
    public String cod;
    public String codEvento;
    private String eventName;

    public Lecture(String cod, String codEvento, String eventName) {
        this.cod = cod;
        this.codEvento = codEvento;
        this.eventName = eventName;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(String codEvento) {
        this.codEvento = codEvento;
    }
}
