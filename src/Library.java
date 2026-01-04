import java.sql.*;
import java.util.Scanner;

public class Library {
    public static void addBook(Connection con)throws Exception{
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter Book ID:");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Name:");
        String name=sc.nextLine();
        System.out.print("Enter Book Author:");
        String author=sc.nextLine();
        System.out.print("Enter Book Published Year:");
        int year=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book ISBN:");
        String Isbn=sc.nextLine();
        try{
            String query="INSERT INTO BOOKS VALUES(?,?,?,?,?);";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt( 1, id);
            pst.setString(2, name);
            pst.setString(3, author);
            pst.setInt(4, year);
            pst.setString(5, Isbn);
            pst.executeUpdate();
            System.out.println("Book Added Successfully!");
        }catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error :ID or ISBN Already Exist " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void removeBook(Connection con)throws Exception{
        try{
            Scanner sc =new Scanner(System.in);
            System.out.print("Enter Book ID :");
            int num=sc.nextInt();
            String query="DELETE FROM BOOKS WHERE BID =?;";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt( 1, num);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book Deleted Successfully!");
            } else {
                System.out.println("No Book Found with given ID!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void viewBooks(Connection con){
        try{
            String query="SELECT * FROM BOOKS;";
            Statement st=con.createStatement();
            ResultSet res=st.executeQuery(query);
            System.out.printf(" %-6s  %-21s  %-15s  %-5s  %-11s \n", "BID", "Book Name", "Author", "Year", "ISBN");
            
            while (res.next()) {
                System.out.printf(" %-6d  %-21s  %-15s  %-5d  %-11s \n",
                                  res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void SearchBook(Connection con)throws Exception{
        try{
            Scanner sc =new Scanner(System.in);
            System.out.print("Enter Book ID to Search:");
            int num=sc.nextInt();
            String query=String.format("SELECT * FROM BOOKS WHERE BID =%d;",num);
            Statement st=con.createStatement();
            ResultSet res=st.executeQuery(query);
            if(res.next()){
                System.out.printf(" %-6s  %-21s  %-15s  %-5s  %-11s \n", "BID", "Book Name", "Author", "Year", "ISBN");
                System.out.printf(" %-6d  %-21s  %-15s  %-5d  %-11s \n",
                res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5));
            }else{
                System.out.println("Invalid Book ID!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
