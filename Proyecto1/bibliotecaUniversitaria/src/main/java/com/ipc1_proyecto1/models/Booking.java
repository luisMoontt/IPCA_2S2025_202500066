package com.ipc1_proyecto1.models;

import java.time.LocalDateTime;

/**
 * @author Luis Monterroso
 */
public class Booking {
    private String id_booking;
    private String username;
    private String id_b;
    private LocalDateTime fechaRegistro;
    private int state;

    public Booking(String id_booking, String username, String id_b, LocalDateTime fechaRegistro, int state) {
        this.id_booking = id_booking;
        this.username = username;
        this.id_b = id_b;
        this.fechaRegistro = fechaRegistro;
        this.state = state;
    }

    public String getId_booking() {
        return id_booking;
    }

    public void setId_booking(String id_booking) {
        this.id_booking = id_booking;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId_b() {
        return id_b;
    }

    public void setId_b(String id_b) {
        this.id_b = id_b;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
}
