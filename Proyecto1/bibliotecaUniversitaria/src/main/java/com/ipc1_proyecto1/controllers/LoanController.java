package com.ipc1_proyecto1.controllers;
 
import com.ipc1_proyecto1.models.Loans;
import com.ipc1_proyecto1.models.User;
import com.ipc1_proyecto1.models.Book;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
 
/**
 * @author Luis Monterroso
 */
public class LoanController {
 
    private Loans[] loans;
    private int loansLimit = 100;
    private int totalLoans;
 
    private UserController u_controller;
    private BookController b_controller;
 
    public LoanController(UserController u_controller, BookController b_controller) {
        this.loans = new Loans[loansLimit];
        this.totalLoans = 0;
        this.u_controller = u_controller;
        this.b_controller = b_controller;
    }
 
    // CREATE / LOAD ///////////////////////////////////////////////////////////////
    public void loadLoan(String id_loan, String userName, String id_b, LocalDate startDate, LocalDate endDate, String STATE){
        loans[totalLoans] = new Loans(id_loan, userName, id_b, startDate, endDate, STATE);
        totalLoans++;
    }
    public void createLoan(String userName, String id_b, LocalDate startDate, LocalDate endDate) {
        String STATE = "ACTIVO";
        int id = 00+totalLoans;
        String id_loan = String.valueOf(id);
        
        User student = u_controller.findUser(userName);
        if (student == null) {
            System.out.println("USUARIO NO ENCONTRADO.");
            return;
        }
        if (student.getRol() != 3) {
            System.out.println("SOLO LOS ESTUDIANTES PUEDEN TENER PRÉSTAMOS.");
            return;
        }
 
        Book book = b_controller.findBook(id_b);
        if (book == null) {
            System.out.println("LIBRO NO ENCONTRADO.");
            return;
        }
        if (book.getStock() <= 0) {
            System.out.println("EL LIBRO NO TIENE STOCK DISPONIBLE.");
            return;
        }
 
        String[] studentLoans = student.getLoans();
        int emptySlot = -1;
        for (int i = 0; i < studentLoans.length; i++) {
            if (studentLoans[i] == null) {
                emptySlot = i;
                break;
            }
        }
        if (emptySlot == -1) {
            System.out.println("EL ESTUDIANTE YA TIENE EL MÁXIMO DE PRÉSTAMOS ACTIVOS (3).");
            return;
        }
        
 
        // Crear el préstamo
        Loans newLoan = new Loans(id_loan, userName, id_b, startDate, endDate, STATE);
        loans[totalLoans] = newLoan;
        totalLoans++;
 
        studentLoans[emptySlot] = id_loan;
        book.setStock(book.getStock() - 1);
 
        System.out.println("PRÉSTAMO CREADO EXITOSAMENTE.");
    }
 
    // RETURN ///////////////////////////////////////////////////////////////
    public void returnLoan(String id_loan) {
        
        
        Loans loan = findLoan(id_loan);
        if (loan == null) {
            System.out.println("PRÉSTAMO NO ENCONTRADO.");
            return;
        }
        if (loan.getSTATE().equals("DEVUELTO")) {
            System.out.println("ESTE PRÉSTAMO YA FUE DEVUELTO.");
            return;
        }
 
        // Cambiar estado a DEVUELTO
        loan.setSTATE("DEVUELTO");
 
        // Eliminar el préstamo del arreglo del estudiante
        User student = u_controller.findUser(loan.getUserName());
        if (student != null) {
            String[] studentLoans = student.getLoans();
            for (int i = 0; i < studentLoans.length; i++) {
                if (studentLoans[i] != null && studentLoans[i].equals(id_loan)) {
                    studentLoans[i] = null;
                    break;
                }
            }
            student.setLoans(studentLoans);
        }
 
        // Devolver stock al libro
        Book book = b_controller.findBook(loan.getId_b());
        if (book != null) {
            book.setStock(book.getStock() + 1);
        }
 
        System.out.println("PRÉSTAMO DEVUELTO EXITOSAMENTE.");
    }
 
    // UPDATE STATES ////////////////////////////////////////////////////////
    public void updateLoanStates() {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < totalLoans; i++) {
            if (loans[i] != null && loans[i].getSTATE().equals("ACTIVO")) {
                if (today.isAfter(loans[i].getEndDate())) {
                    loans[i].setSTATE("RETRASO");
                }
            }
        }
    }
 
    // READ /////////////////////////////////////////////////////////////////
    public Loans findLoan(String id_loan) {
        for (int i = 0; i < totalLoans; i++) {
            if (loans[i] != null && loans[i].getId_loan().equals(id_loan)) {
                return loans[i];
            }
        }
        return null;
    }
 
    public Loans[] listLoans() {
        Loans[] result = new Loans[totalLoans];
        for (int i = 0; i < totalLoans; i++) {
            result[i] = loans[i];
        }
        return result;
    }
 
    public Loans[] listLoansByUser(String userName) {
        int count = 0;
        for (int i = 0; i < totalLoans; i++) {
            if (loans[i] != null && loans[i].getUserName().equals(userName)) count++;
        }
        Loans[] result = new Loans[count];
        int j = 0;
        for (int i = 0; i < totalLoans; i++) {
            if (loans[i] != null && loans[i].getUserName().equals(userName)) {
                result[j] = loans[i];
                j++;
            }
        }
        return result;
    }
    // PRINNNNNNNTTTTTTTTTTTTTT /////////////////
    public void saveLoansToFile() {
        String filePath = "../src/main/java/com/ipc1_proyecto1/documents/prestamos.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            
            for (int i = 0; i < totalLoans; i++) {
                bw.write(loans[i].getId_loan() + ";" +
                loans[i].getUserName() + ";" +
                loans[i].getId_b() + ";" +
                loans[i].getStartDate().toString() + ";" +
                loans[i].getEndDate().toString() + ";" +
                loans[i].getSTATE() + ";");
                bw.newLine();
            }

            System.out.println("PRESTAMOS GUARDADOS EXITOSAMENTE.");
        } catch (IOException e) {
            System.out.println("ERROR AL GUARDAR EL ARCHIVO: " + e.getMessage());
        }
    } 
}