package bankprojectdb;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author KrIstIaN
 */
public class BankProjectDB extends DBConnect implements ActionListener{
JFrame[] f = new JFrame[8];
JFrame popUp = new JFrame();
JButton[] MB = new JButton[8];
JButton cnfrm = new JButton("Confirm");
JButton cncl = new JButton("Cancel");
JLabel accNum = new JLabel("Account Number:");
JLabel val = new JLabel();
JTextField accNumField = new JTextField();
JTextField[] tfield = new JTextField[6]; 
JPanel historyPane = new JPanel(new GridBagLayout());
BoxLayout boxLayout = new BoxLayout(historyPane, BoxLayout.Y_AXIS);
JScrollPane historyScroll = new JScrollPane(historyPane);
DBConnect connect = new DBConnect();
int ctr = 0;
int state = 0;
    BankProjectDB(){
        String[] title = {"New Account", "Balance Inquiry", "Deposit", 
            "Withdraw", "Client Profile", "Close Account", "History", "Exit"};
        f[0] = new JFrame("Java Bank Project");
        for (int i = 1; i < 8; i++){
            f[i] = new JFrame(title[i - 1]);
        }
       
        int x = 50;
        for (int i = 0; i < 8; i++){
            MB[i] = new JButton(title[i]);
            MB[i].setBounds(75, x, 150, 20);
            x += 20;
            f[0].add(MB[i]);
            MB[i].addActionListener(this);
            if (i != 0 && i != 7 )
                MB[i].setEnabled(false);
        }
                
        cnfrm.setBounds(150, 230, 120, 20);
        cnfrm.addActionListener(this);
        cncl.setBounds(15, 230, 120, 20);
        cncl.addActionListener(this);
        accNum.setBounds(15, 30, 150, 20);
        accNumField.setBounds(120, 30, 150, 20);
        
//-----------------------------------New Account------------------------------------------------------
        JLabel[] NAtext = new JLabel[5];
        NAtext[0] = new JLabel("Name:");
        NAtext[1] = new JLabel("Address:");
        NAtext[2] = new JLabel("Birthday:");
        NAtext[3] = new JLabel("Contact Number:");
        NAtext[4] = new JLabel("Initial Deposit:");

        x = 30;
        for(int i = 0; i < 5; i++){
            NAtext[i].setBounds(15, x, 150, 20);
            f[1].add(NAtext[i]);
            x += 40;
        }
        
//------------------------------------------------------------------------------------------        
    historyPane.setLayout(boxLayout);
    
    historyScroll.setBounds(15, 30, 285, 270);
    historyScroll.setBorder(BorderFactory.createEmptyBorder());
//------------------------------------------------------------------------------------------  
        for (int i = 1; i < 8; i++){
            f[i].setSize(300,300);
            f[i].setLayout(null);
            f[i].setVisible(false);
            f[i].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
//------------------------------------------------------------------------------------------ 

        
        
        f[0].setSize(300,300);
        f[0].setLayout(null);
        f[0].setVisible(true);
        f[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed (ActionEvent e){
        
        if (e.getSource() == MB[0]){ //NEW ACCOUNT
            int x = 30;
            for(int i = 0; i < 5; i++){
                tfield[i] = new JTextField();
                tfield[i].setBounds(120, x, 150, 20);
                tfield[i].setText("");
                f[1].add(tfield[i]);
                x += 40;
            }
            f[1].add(cnfrm);
            f[1].add(cncl);
            state = 1;
            f[1].setVisible(true);

        }
        if (e.getSource() == MB[1]){ //BALANCE INQUIRY
            f[2].add(cnfrm);
            f[2].add(cncl);
            f[2].add(accNum);
            f[2].add(accNumField);
            state = 2;
            f[2].setVisible(true);

        }
        if (e.getSource() == MB[2]){ //DEPOSIT
            val.setText("Deposit: ");
            val.setBounds(15, 70, 150, 20);
            f[3].add(val);
            tfield[0].setBounds(120, 70, 150, 20);
            tfield[0].setText("");
            f[3].add(tfield[0]);           
            f[3].add(cnfrm);
            f[3].add(cncl);
            f[3].add(accNum);
            f[3].add(accNumField);
            state = 3;
            f[3].setVisible(true);

        }
        if (e.getSource() == MB[3]){ //WITHDRAW
            val.setText("Withdraw: ");
            val.setBounds(15, 70, 150, 20);
            f[4].add(val);
            tfield[0].setBounds(120, 70, 150, 20);
            tfield[0].setText("");
            f[4].add(tfield[0]);   
            f[4].add(cnfrm);
            f[4].add(cncl);
            f[4].add(accNum);
            f[4].add(accNumField);
            state = 4;
            f[4].setVisible(true);

        }
        if (e.getSource() == MB[4]){ //CLIENT PROFILE
            f[5].add(cnfrm);
            f[5].add(cncl);
            f[5].add(accNum);
            f[5].add(accNumField);
            state = 5;
            f[5].setVisible(true);

        }
        if (e.getSource() == MB[5]){ //CLOSE ACCOUNT
            f[6].add(cnfrm);
            f[6].add(cncl);
            f[6].add(accNum);
            f[6].add(accNumField);
            state = 6;
            f[6].setVisible(true);

        }
        if (e.getSource() == MB[6]){ //HISTORY
            ResultSet rs = connect.getTransactions();
            historyPane.removeAll();
            JLabel jLabel = new JLabel();
            jLabel.setBounds(15, 10, 230, 20);
            f[7].add(jLabel);
            jLabel.setText("No.  |  Acc No.     Amount       Trans. Type");
            int i = 1;
            try{
                while(rs.next()){ 
                    jLabel = new JLabel(i + ".     |    " + rs.getInt("account_id") + "         "
                            + rs.getDouble("amount") + "         " + rs.getString("transaction_type"));
                    i++;
                    historyPane.add(jLabel);
                    historyPane.add(Box.createRigidArea(new Dimension(1, 5)));
                }
                f[7].add(historyScroll);
                f[7].setVisible(true);
            }catch(Exception x){
                System.out.println("Error: " + e);
            }
        }
        if (e.getSource() == MB[7]){ //EXIT
            f[0].dispose();

        }
        if (e.getSource() == cncl){
            f[state].dispose();
        }
        if (e.getSource() == cnfrm){
            if (state != 1)
                MB[6].setEnabled(true);
            if (state == 1){ //NEW ACCOUNT
                try{
                    int genAccNum;
                    do{
                        genAccNum = new Random().nextInt((9999 - 1000) + 1) + 1000;
                    }while (connect.checkExists(genAccNum));
                    if(Integer.parseInt(tfield[4].getText()) < 5000 )
                        JOptionPane.showMessageDialog(popUp, "Initial Deposit must not be less than 5000",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    else{
                        connect.addToDB(genAccNum, tfield[0].getText(), tfield[1].getText(),
                                tfield[3].getText(), Integer.parseInt(tfield[4].getText()));
                        JOptionPane.showMessageDialog(popUp,"Account number is: " + 
                                        Integer.toString(genAccNum),"Account Added", JOptionPane.PLAIN_MESSAGE);
                        System.out.println("Accunt Number is: " + Integer.toString(genAccNum));
                        for (int i = 0; i < 5; i++)
                            f[1].remove(tfield[i]);
                        ctr += 1;
                        f[1].dispose();
                        for (int i = 1; i < 6; i++)
                            MB[i].setEnabled(true);   
                    }
                    }catch(NumberFormatException x){
                        JOptionPane.showMessageDialog(popUp, "Invalid Input!",
                        "ERROR", JOptionPane.PLAIN_MESSAGE);
                        System.out.println(x);
                   }catch(Exception x){
                        System.out.println(x);
                   }                               
            }
            if (state == 2){ //BALANCE INQUIRY
                try{
                    int number = Integer.parseInt(accNumField.getText().replaceAll("\\s+",""));
                    if (connect.checkExists(number)){
                        JOptionPane.showMessageDialog(popUp, "Account Balance: " + Double.toString(connect.getDeposit(number)),
                            "Balance Inquiry", JOptionPane.PLAIN_MESSAGE);
                        connect.addTransaction(number, 0, "BALANCE INQUIRY");
                        f[2].dispose();
                    }
                }catch(NumberFormatException x){
                    JOptionPane.showMessageDialog(popUp, "Invalid Input",
                    "ERROR", JOptionPane.PLAIN_MESSAGE);
                }catch(Exception x){
                    System.out.println(x);
                }
            }
            if (state == 3){ //DEPOSIT
                try{
                    int number = Integer.parseInt(accNumField.getText().replaceAll("\\s+",""));
                    if (connect.checkExists(number)){
                        if (Double.parseDouble(tfield[0].getText()) >= 100){
                            double deposit = connect.getDeposit(number);
                            deposit += Double.parseDouble(tfield[0].getText());
                            double interest = (deposit * .05);
                            deposit += interest; //interest
                            connect.setDeposit(number, deposit);
                            JOptionPane.showMessageDialog(popUp, "You deposited: " + tfield[0].getText() + 
                                " + interest(" + Double.toString(interest) + ")",
                                "Deposit", JOptionPane.PLAIN_MESSAGE);
                            connect.addTransaction(number, Double.parseDouble(tfield[0].getText()) + interest, 
                                    "DEPOSIT");
                            f[3].dispose();
                        }
                        else
                            JOptionPane.showMessageDialog(popUp, "Deposit must not be less than 100",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                }catch(NumberFormatException x){
                    JOptionPane.showMessageDialog(popUp, "Invalid Input",
                    "ERROR", JOptionPane.PLAIN_MESSAGE);
                }catch(Exception x){
                    System.out.println(x);
                }
            }
            if (state == 4){ //WITHDRAW
                try{
                    int number = Integer.parseInt(accNumField.getText().replaceAll("\\s+",""));
                    if (connect.checkExists(number)){
                        double balance = connect.getDeposit(number);
                        if (balance - Double.parseDouble(tfield[0].getText()) < 5000 || balance < 5100 )
                            JOptionPane.showMessageDialog(popUp, "Withdraw failed. The Account "
                            + "balance will fall below the maintaining balance of 5000.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                        else if(Float.parseFloat(tfield[0].getText()) < 100)
                            JOptionPane.showMessageDialog(popUp, "Withdraw must not be less than 100",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                        else{
                            balance -= Double.parseDouble(tfield[0].getText());
                            connect.setDeposit(number, balance);
                            JOptionPane.showMessageDialog(popUp, "You withdrawed: " + tfield[0].getText(),
                                "Withdraw", JOptionPane.PLAIN_MESSAGE);
                            connect.addTransaction(number, Double.parseDouble(tfield[0].getText()), 
                                    "WITHDRAW");
                            f[4].dispose();
                        }
                    }
                }catch(NumberFormatException x){
                    JOptionPane.showMessageDialog(popUp, "Invalid Input",
                    "ERROR", JOptionPane.PLAIN_MESSAGE);
                }catch(Exception x){
                    System.out.println(x);
                }
            }
            if (state == 5){ //CLIENT PROFILE
                try{
                    int number = Integer.parseInt(accNumField.getText().replaceAll("\\s+",""));
                    if (connect.checkExists(number)){
                        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
                        JOptionPane.showMessageDialog(popUp, 
                            "<html><b>Name: </b>" + connect.getName(number) + 
                            "\n<html><b>Address: </b>" + connect.getAddress(number) +
                            "\n<html><b>Birthday: </b>" + connect.getDate(number) +
                            "\n<html><b>Contact Number: </b>" + connect.getContact(number) +
                            "\n<html><b>Account Balance: </b>" + connect.getDeposit(number),
                            "Client Profile", JOptionPane.PLAIN_MESSAGE);
                            f[5].dispose();
                    }
                }catch(NumberFormatException x){
                    JOptionPane.showMessageDialog(popUp, "Invalid Input",
                    "ERROR", JOptionPane.PLAIN_MESSAGE);
                }catch(Exception x){
                    System.out.println(x);
                }
            }
            if (state == 6){ //CLOSE ACCOUNT
                try{
                    int number = Integer.parseInt(accNumField.getText().replaceAll("\\s+",""));
                    if (connect.checkExists(number)){
                        int y = JOptionPane.showConfirmDialog(popUp, "Are you sure you want to close account "
                                + accNumField.getText().replaceAll("\\s+","") + " registered to "
                                + connect.getName(number) + "?");
                        if(y == JOptionPane.YES_OPTION){
                            connect.deleteAcc(number);
                            JOptionPane.showMessageDialog(popUp, "Account closed!",
                            "Success", JOptionPane.PLAIN_MESSAGE);
                            ctr -= 1;
                            if (ctr == 0)
                                for (int i = 1; i < 7; i++)
                                    MB[i].setEnabled(false);
                            f[6].dispose();
                        }
                    }
                }catch(NumberFormatException x){
                    JOptionPane.showMessageDialog(popUp, "Invalid Input",
                    "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
            /*
            try{
                
                
            }catch(Exception x){
                System.out.println(x);
            }
            */
                
            
        }
    }
    
    public static void main(String[] args) {
        new BankProjectDB();
    }
    
}