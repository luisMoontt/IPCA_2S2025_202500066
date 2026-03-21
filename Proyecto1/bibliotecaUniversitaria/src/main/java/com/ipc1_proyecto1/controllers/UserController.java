package com.ipc1_proyecto1.controllers;
import com.ipc1_proyecto1.models.User;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Luis Monterroso
 */
public class UserController {
    private User[] users;
    private int usersLimit = 100;
    private int totalUsers;
    public String loggedUser = null;
    
    public UserController(){
        this.users = new User[usersLimit];
        this.totalUsers = 0;
    }

    // CREATE AND LOAD /////////////////////////////
    public void createStudent(String userName, String fullName, String password, String career) {
    if (!userExists(userName)) {
        String[] bookLoans = new String[3];
        users[totalUsers] = new User(userName, fullName, password, career, 3, 0, bookLoans);
        totalUsers++;
        System.out.println("ESTUDIANTE CREADO EXITOSAMENTE");
    } else {
        System.out.println("El usuario '" + userName + "' ya existe.");
    }
}
    public void loadStudent(String userName, String fullName, String password, String career, int loanFine, String[] bookLoans){
        users[totalUsers] = new User(userName, fullName, password, career, 3, loanFine, bookLoans);
        totalUsers++;
    }
    public void createOperator(String userName, String fullName, String password){
        users[totalUsers] = new User(userName, fullName, password, 2);
        totalUsers++;
    }
    public void createAdmin(){
       users[totalUsers] = new User("admin", "admin", 1); 
       totalUsers++;
    }
    // READ ///////////////////////////////
    public User[] listUsers(){
        User[] result = new User[totalUsers];
        for (int i=0; i<totalUsers; i++){
            result[i] = users[i];
        }
        return result;
    }
    public User[] listStudents(){
        User[] students = new User[countStudents()];
        int j = 0;
        for (int i=0; i<totalUsers; i++){
            if(users[i].getRol() == 3){
                students[j] = users[i];
                j++;
            }
        }
        return students;
    }
    public User[] listOperators(){
        User[] operators = new User[countOperators()];
        int j = 0;
        for (int i=0; i<totalUsers; i++){
            if(users[i].getRol() == 2){
                operators[j] = users[i];
                j++;
            }
        }
        return operators;
    }
    // DELETE ///////////////////////////////
//    public void deleteUser(String userName){
//        if(userExists(userName)){
//            if(findUser(userName).getFine() == 0){
//                int i = userIndex(userName); 
//                users[i].setUserName(null);
//                users[i].setFullName(null);
//                users[i].setPassword(null);
//                users[i].setCareer(null);
//                users[i].setRol(0);
//                users[i].setFine(0);
//            } else {
//                System.out.println("USUARIO CON PRESTAMOS PENDIENTES");
//            }
//        } else {
//            System.out.println("USUARIO NO ENCONTRADO");
//        }
//    }
    // ///////////////////////////////////////////
    public User findUser(String userName){
        User us = null;
        if(userExists(userName)){
            for (int i=0; i<totalUsers; i++){
                if (users[i].getUserName().equals(userName)){
                    System.out.println("ENCONTRADO");
                    return users[i];
                }
            }
        }
        return us;
    }
    public int userIndex(String userName){
        int index = 0;
        if(userExists(userName)){
            for (int i=0; i<totalUsers; i++){
                if (users[i].getUserName().equals(userName)){
                    index = i;
                    return index;
                }
            }
        }
        return index;
    }
    // ///////////////////////////////////
    public boolean userExists(String userName){
        for (int i=0; i<totalUsers; i++){
            if(users[i].getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
    // ///////////////////////////////////
    public boolean login(String userName, String password){
        if (userExists(userName)){
            for (int i=0; i<totalUsers; i++){
                if(users[i].getUserName().equals(userName) && users[i].getPassword().equals(password)){
                    System.out.println("BIENVENIDO");
                    loggedUser = userName;
                    return true;
                }
            }
            System.out.println("CREDENCIALES INVALIDAS");
            return false;
        } else {
            System.out.println("NOMBRE DE USURIO NO ENCONTRADO");
        }
        return false;
    }
    // /////////////////////////////////////
    public int countStudents(){
        int number = 0;
        for (int i=0; i<totalUsers; i++){
            if(users[i].getRol() == 3){
                number++;
            }
        }
        return number;
    }
    public int countOperators(){
        int number = 0;
        for (int i=0; i<totalUsers; i++){
            if(users[i].getRol() == 2){
                number++;
            }
        }
        return number;
    }
    
    // PRINNNNNNNTTTTTTTTTTTTTT /////////////////
    public void saveUsersToFile() {
        String filePath = "src/main/java/com/ipc1_proyecto1/documents/cuentas.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < totalUsers; i++) {
                if (users[i].getRol() == 2) {
                    bw.write(users[i].getUserName() + ";" +
                             users[i].getFullName() + ";" +
                             users[i].getPassword() + ";");
                    bw.newLine();
                }
            }
            for (int i = 0; i < totalUsers; i++) {
                if (users[i].getRol() == 3) {
                    String[] loans = users[i].getLoans();
                    StringBuilder loansStr = new StringBuilder();
                    for (int j = 0; j < loans.length; j++) {
                        loansStr.append(loans[j] == null ? "null" : loans[j]);
                        if (j < loans.length - 1) loansStr.append(",");
                    }
                    bw.write(users[i].getUserName() + ";" +
                             users[i].getFullName() + ";" +
                             users[i].getPassword() + ";" +
                             users[i].getCareer() + ";" +
                             users[i].getFine() + ";" +
                             loansStr + ";");
                    bw.newLine();
                }
            }

            System.out.println("USUARIOS GUARDADOS EXITOSAMENTE.");
        } catch (IOException e) {
            System.out.println("ERROR AL GUARDAR EL ARCHIVO: " + e.getMessage());
        }
    } 
    
}

