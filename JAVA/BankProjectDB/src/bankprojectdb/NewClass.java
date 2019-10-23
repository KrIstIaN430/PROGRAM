package bankprojectdb;
import java.sql.*;

public class NewClass extends DBConnect{
    public static void main(String[] args) {
        
        DBConnect connect = new DBConnect();
        
       
        System.out.println(connect.checkExists(123456));
        
        
        
    }
}
