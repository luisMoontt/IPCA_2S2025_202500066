package com.ipc1_proyecto1.models;

/**
 * @author Luis Monterroso
 */
public class User {
   private String userName;
   private String fullName;
   private String password; 
   private String career; // SOLO PARA ESTUDIANTES
   private int rol;
   private int loanFine; // SOLO PARA ESTUDIANTES
   private String[] bookLoans = new String[3]; // SOLO PARA ESTUDIANTES
   
   public User(String userName, String fullName, String password, String career, int rol, int loanFine, String[] bookLoans) {
       this.userName = userName;
       this.fullName = fullName;
       this.password = password;
       this.career = career;
       this.rol = rol;
       this.loanFine = loanFine;
       this.bookLoans = bookLoans;
   }
  public User(String userName, String fullName, String password, int rol) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.rol = rol;
    } 

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getCareer() {
        return career;
    }

    public int getRol() {
        return rol;
    }

    public int getLoanFine() {
        return loanFine;
    }

    public String[] getBookLoans() {
        return bookLoans;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public void setLoanFine(int loanFine) {
        this.loanFine = loanFine;
    }

    public void setBookLoans(String[] bookLoans) {
        this.bookLoans = bookLoans;
    }

}
