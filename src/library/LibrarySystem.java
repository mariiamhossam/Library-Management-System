/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import Library.users.Librarians;
import Library.users.Readers;
import java.util.Scanner;
import java.time.*;


/**
 *
 * @author yosef
 */
public class LibrarySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Librarians admin[] = new Librarians[5];

        //initialization of librarians
        admin[0] = new Librarians(1, "admin1@admn.com", "1111", "Librarians", "Ahmed", "Hassan",
                "ABC", "201078496455", false, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2030, 1, 2));           //Book name is "" instead of null

        admin[1] = new Librarians(2, "admin2@admn.com", "2222", "librarian", "Ali", "Mohamed",
                "XYZ", "201123947957", false, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2020, 1, 2));

        admin[2] = new Librarians(3, "admin3@admn.com", "3333", "librarian", "Mohamed", "Fathy",
                "LMN", "201038774654", false, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2020, 1, 2));

        admin[3] = new Librarians(4, "admin4@admn.com", "4444", "librarian", "Sara", "Ahmed",
                "DFG", "201118476563", false, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2020, 1, 2));

        admin[4] = new Librarians(5, "admin5@admn.com", "5555", "librarian", "Soha", "Akram",
                "XXX", "201038365454", false, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2030, 1, 2));

        Readers reader[] = new Readers[100];

        //initialization of readers
        reader[0] = new Readers(11, "karim11@reader.com", "karim11", "reader", "Karim", "Hossam",
                "ZZZ", "01091751184", false, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2020, 1, 2));            //Book name is "" instead of null

        reader[1] = new Readers(22, "mona22@reader.com", "mona22", "reader", "Mona", "Hamdy",
                "KLM", "01001234567", true, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2030, 1, 2));

        reader[2] = new Readers(33, "khaled33@reader.com", "khaled33", "reader", "Khaled", "Hassan",
                "YYY", "01234567896", true, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2030, 1, 2));

        reader[3] = new Readers(44, "amr44@reader.com", "amr44", "reader", "Amr", "Ahmed",
                "FGH", "01167854378", false, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2030, 1, 2));

        reader[4] = new Readers(55, "omar55@reader.com", "omar55", "reader", "Omar", "Ashraf",
                "FFF", "014456565662", false, false, "", LocalDate.of(2030, 1, 1), LocalDate.of(2020, 1, 2));

        Books book[] = new Books[200];
        //initialization of books
        book[0] = new Books("x", 0, "x");
        book[1] = new Books("y", 1, "x");
        book[2] = new Books("z", 1, "x");
        book[3] = new Books("a", 1, "x");
        book[4] = new Books("b", 1, "x");

        //initialization of readers by default values
        for (int i = 5; i < 100; i++) {
            reader[i] = new Readers(0, "empty", "empty", "empty", "empty", "empty",
                    "empty", "empty", false, false, "empty", LocalDate.of(2030, 1, 1), LocalDate.of(2030, 1, 1));

        }
        //intialization of books by default values
        for (int i = 5; i < 200; i++) {
            book[i] = new Books("empty", 0, "empty");

        }

        while (true) {
            String Email, password;
            Scanner input = new Scanner(System.in);
            System.out.println("\t\t\t\t* WELCOME TO LIBRARY SYSTEM *\t\t\t\t");
            System.out.println("Please enter your Email and password to log in");
            System.out.print("Email: ");
            Email = input.next();
            System.out.print("Password: ");
            password = input.next();

            Librarians lib = new Librarians();
            Readers r = new Readers();

            int userIndex;
            boolean isFound = false;
            for (int i = 0; i < 5; i++) {

                if (Email.equals(admin[i].Email) && password.equals(admin[i].getPassword())) {
                    isFound = true;
                    userIndex = i;
                    lib.Admin_page(userIndex, reader, admin, book);
                    break;
                }
            }

            if (isFound == false) {
                for (int i = 0; i < 100; i++) {
                    if (Email.equals(reader[i].Email) && password.equals(reader[i].getPassword())) {

                        userIndex = i;
                        r.Reader_page(userIndex, reader, admin, book);
                        isFound=true;
                        break;
                    } 

                }
            }

            if (isFound == false) {
                System.out.println("INCORRECT EMAIL OR PASSWORD! PLEASE TRY AGAIN");
            }

        }

    }

}
