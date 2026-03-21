package com.ipc1_proyecto1.models;

import java.time.LocalDate;

/**
 * @author Luis Monterroso
 */
public class Loans{
    private String id_loan;
    private String userName;
    private String id_b;
    private LocalDate startDate;
    private LocalDate endDate; 
    private String STATE;

    public Loans(String id_loan, String userName, String id_b, LocalDate startDate, LocalDate endDate, String STATE) {
        this.id_loan = id_loan;
        this.userName = userName;
        this.id_b = id_b;
        this.startDate = startDate;
        this.endDate = endDate;
        this.STATE = STATE;
    }

    public String getId_loan() {
        return id_loan;
    }

    public void setId_loan(String id_loan) {
        this.id_loan = id_loan;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId_b() {
        return id_b;
    }

    public void setId_b(String id_b) {
        this.id_b = id_b;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }
    
}
