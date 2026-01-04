import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        final String url="jdbc:mysql://localhost:3306/Library";
        final String user="user";
        final String password="your_password";
        Connection con=DriverManager.getConnection(url, user, password);
        boolean loop=true;
        while(loop){
            System.out.println("1.ADD BOOK");
            System.out.println("2.DELETE BOOK");
            System.out.println("3.SEARCH BOOK");
            System.out.println("4.VIEW BOOKS LIST");
            System.out.println("5.EXIT");
            System.out.println("Eneter Your Option:");
            int option=sc.nextInt();

            switch (option) {
                case 1:
                    Library.addBook(con);
                    break;
                case 2:
                    Library.removeBook(con);
                    break;
                case 3:
                    Library.SearchBook(con);
                    break;
                case 4:
                    Library.viewBooks(con);
                    break;
                case 5:
                    loop=false;
                    System.out.println("EXITING...");
                    break;
                default:
                    System.out.println("Invalid Option!");;
            }
        }
    }
}
