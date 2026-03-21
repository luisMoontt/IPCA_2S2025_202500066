package com.ipc1_proyecto1.controllers;
import com.ipc1_proyecto1.models.User;
import com.ipc1_proyecto1.models.Book;
import com.ipc1_proyecto1.models.Loans;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Admin
 */
public class StartController{
    UserController u_controller = new UserController();
    BookController b_controller = new BookController();
    LoanController l_controller = new LoanController(u_controller, b_controller);
    
    public void startSystem(){
        createAdmin();
        loadUsersFromFile("../src/main/java/com/ipc1_proyecto1/documents/cuentas.txt", u_controller);
        printAccounts();
        loadBooks();
        printBooks();
        loadLoansFromFile("../src/main/java/com/ipc1_proyecto1/documents/prestamos.txt", l_controller);
        printLoans();
        l_controller.updateLoanStates();
        printLoans();
        //test();
        //u_controller.saveUsersToFile();
        //l_controller.saveLoansToFile();
    }
    
    public void createAdmin(){
       u_controller.createAdmin();
    }
    
    public void printAccounts(){
        u_controller.createStudent("202500066", "Luis Monterroso", "123456", "Ing Sistemas");
        
        User[] all = u_controller.listUsers();
        User[] ope = u_controller.listOperators();
        User[] stu = u_controller.listStudents();
        
        

        for (User u : all) {
            System.out.print("- " + u.getUserName() + " | Rol: " + u.getRol() + " | " + u.getFullName()+" | Loan books:{");
           
            for (String b : u.getLoans()) {
                System.out.print(" "+b);
            }
            System.out.println(" }");
        }
        System.out.println("OPERADORES");
        for (User u : ope) {
            System.out.println("- " + u.getUserName() + " | " + u.getFullName());
        }
        System.out.println("ESTUDIANTES");
        for (User e : stu) {
            System.out.print("- " + e.getUserName() + " | " + e.getFullName()+ " | "+e.getCareer()+" | "+e.getFine()+" | Loan books:{");
           
            for (String b : e.getLoans()) {
                System.out.print(" "+b);
            }
            System.out.println(" }");
        }
    }
    public static void loadUsersFromFile(String filePath, UserController controller) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue;
                }

                String[] fields = line.trim().split(";");

                if (fields.length == 3) {
                    String userName = fields[0].trim();
                    String fullName = fields[1].trim();
                    String password = fields[2].trim();

                    controller.createOperator(userName, fullName, password);
                    
                } else if (fields.length == 6) {
                    String userName  = fields[0].trim();
                    String fullName  = fields[1].trim();
                    String password  = fields[2].trim();
                    String career    = fields[3].trim();
                    int fine  = Integer.parseInt(fields[4].trim());
                    String rawData = fields[5].trim();
                    
                    String [] loans = rawData.split(",");

                    controller.loadStudent(userName, fullName, password, career, fine, loans);
                    
                } else {
                    System.out.println("Línea " + lineNumber + ": Formato inválido, se omite → \"" + line + "\"");
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato numérico en el archivo: " + e.getMessage());
        }
    }

    public void printBooks(){
        Book[] all = b_controller.listBooks();
        for (Book b : all) {
            System.out.println("- " + b.getId_b() + " - "+ b.getISBN() +" | "+ b.getTitle()+ " - "+b.getAuthor()+" - "+b.getGenre()+" - "+b.getYearDeparture()+" - "+b.getStock());
        }
    }
    public void loadBooks(){
        b_controller.loadBook("1000", "1231231231231", "EL PRINCIPITO", "Reynal & Hitchcock", "fabula", 1943, 10);
        b_controller.loadBook("1001", "3213213123213", "CREPUSCULO", "Reynal & Hitchcock", "fabula", 1943, 10);
        b_controller.loadBook("1002", "1010100101010", "REBELION EN LA GRANJA", "Reynal & Hitchcock", "fabula", 1943, 11);
        b_controller.loadBook("1003", "0101010100101", "1984", "Reynal & Hitchcock", "fabula", 1943, 15);
        b_controller.loadBook("1004", "6767676767767", "EL SEÑOR DE LAS MOSCAS", "asdasd", "fabula", 1943, 9);
    }
    
    public void printLoans(){
        Loans[] loans = l_controller.listLoans();
        for (Loans l : loans) {
            System.out.println("- " + l.getId_loan()+" "+l.getUserName()+" "+l.getId_b()+" "+l.getSTATE()+" ("+l.getStartDate()+" - "+l.getEndDate()+")");
        }
    };
    public static void loadLoansFromFile(String filePath, LoanController controller){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                
                String[] fields = line.trim().split(";");
                if (fields.length == 6) {
                    String id_loan   = fields[0].trim();
                    String userName  = fields[1].trim();
                    String id_b      = fields[2].trim();
                    LocalDate startDate = LocalDate.parse(fields[3].trim());
                    LocalDate endDate   = LocalDate.parse(fields[4].trim());
                    String STATE = fields[5].trim();
                    controller.loadLoan(id_loan, userName, id_b, startDate, endDate, STATE);
                } else {
                    System.out.println("FORMATO INVALIDO");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato numérico en el archivo: " + e.getMessage());
        }
        
    }
  
    public void test(){
        User fnd = u_controller.findUser("202500076");
        u_controller.login("admin","admin");
        //u_controller.deleteUser("202500067");
        
        System.out.println(u_controller.loggedUser);
        System.out.println(fnd.getFullName());
    }

}
