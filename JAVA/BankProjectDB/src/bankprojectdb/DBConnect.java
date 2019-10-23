package bankprojectdb;
import java.sql.*;
import java.time.LocalDate;
/**
 *
 * @author KrIstIaN
 */
public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    public DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankprojectdb", "root", "");
            st = con.createStatement();
            System.out.println();
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public boolean checkExists(int number){
        try{
            pst = con.prepareStatement("SELECT * FROM `accounts` WHERE `account_id` = ?");
            pst.setInt(1, number);
            rs = pst.executeQuery();
            
            return rs.first();

        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return false;
    }   
    
    public void addToDB(int number, String name, String address, String contact, double deposit){
        LocalDate locald = LocalDate.of(1997, 04, 30);
        Date date = Date.valueOf(locald); 
        try{
            pst = con.prepareStatement("INSERT INTO `accounts` (account_id, Name, Address, Birthday, contact, deposit) "
                    + "VALUES(?, ?, ?, ?, ?, ?)");
            pst.setInt(1, number);
            pst.setString(2, name);
            pst.setString(3, address);
            pst.setDate(4, date);
            pst.setString(5, contact);
            pst.setDouble(6, deposit);
        
        pst.executeUpdate();
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public String getName(int number){
        try{
            pst = con.prepareStatement("SELECT * FROM `accounts` WHERE `account_id` = ?");
            pst.setInt(1, number);
            rs = pst.executeQuery();
            while(rs.next())
                return rs.getString("Name");

        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }
    public String getAddress(int number){
        try{
            pst = con.prepareStatement("SELECT * FROM `accounts` WHERE `account_id` = ?");
            pst.setInt(1, number);
            rs = pst.executeQuery();
            while(rs.next())
                return rs.getString("Address");

        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }
    public Date getDate(int number){
        try{
            pst = con.prepareStatement("SELECT * FROM `accounts` WHERE `account_id` = ?");
            pst.setInt(1, number);
            rs = pst.executeQuery();
            while(rs.next())
                return rs.getDate("Birthday");

        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }
    public String getContact(int number){
        try{
            pst = con.prepareStatement("SELECT * FROM `accounts` WHERE `account_id` = ?");
            pst.setInt(1, number);
            rs = pst.executeQuery();
            while(rs.next())
                return rs.getString("contact");

        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }
    public double getDeposit(int number){
        try{
            pst = con.prepareStatement("SELECT * FROM `accounts` WHERE `account_id` = ?");
            pst.setInt(1, number);
            rs = pst.executeQuery();
            while(rs.next())
                return rs.getDouble("Deposit");
            
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return 0;
    }
    public void setDeposit(int number, double deposit){
        try{
            pst = con.prepareStatement("UPDATE `accounts` SET `Deposit`= ? WHERE `account_id` = ?");
            pst.setDouble(1, deposit);
            pst.setInt(2, number);
            pst.executeUpdate();            
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public void deleteAcc(int number){
        try{
            pst = con.prepareStatement("DELETE FROM `accounts`WHERE `account_id` = ?");
            pst.setDouble(1, number);
            pst.executeUpdate();            
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public void addTransaction(int number, double amount, String type){
        try{
            pst = con.prepareStatement("INSERT INTO `transactions`(account_id, amount, transaction_type) "
                    + "VALUES (?, ?, ?)");
            pst.setInt(1, number);
            pst.setDouble(2, amount);
            pst.setString(3, type);
            pst.executeUpdate();            
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public ResultSet getTransactions(){
        try{
            pst = con.prepareStatement("SELECT * FROM `transactions` WHERE 1");
            rs = pst.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return rs;
    }
}
