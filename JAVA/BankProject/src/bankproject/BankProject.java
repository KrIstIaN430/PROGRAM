package bankproject;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

/**
 *
 * @author KrIstIaN
 */
public class BankProject implements ActionListener{
JFrame[] f = new JFrame[7];
JFrame popUp = new JFrame();
JButton[] MB = new JButton[7];
JButton cnfrm = new JButton("Confirm");
JButton cncl = new JButton("Cancel");
JLabel accNum = new JLabel("Account Number:");
JLabel val = new JLabel();
JTextField accNumField = new JTextField();
JTextField [] tfield = new JTextField[6]; 
Account[] accountList = new Account[50];
int ctr = 0;
int state = 0;
    BankProject(){
        String[] title = {"New Account", "Balance Inquiry", "Deposit", 
            "Withdraw", "Client Profile", "Close Account", "Exit"};
        f[0] = new JFrame("Java Bank Project");
        for (int i = 1; i < 7; i++){
            f[i] = new JFrame(title[i - 1]);
        }
        

        int x = 50;
        for (int i = 0; i < 7; i++){
            MB[i] = new JButton(title[i]);
            MB[i].setBounds(75, x, 150, 20);
            x += 20;
            f[0].add(MB[i]);
            MB[i].addActionListener(this);
            if (i != 0)
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
        for (int i = 1; i < 7; i++){
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
        if (e.getSource() == MB[6]){ //EXIT
            f[0].dispose();

        }
        if (e.getSource() == cnfrm){
            if (state == 1){ //NEW ACCOUNT
                try{      
                    boolean w = false;
                    int genAccNum = new Random().nextInt((9999 - 1000) + 1) + 1000;
                    do{
                        for (int i = 0; i < accountList.length; i++){
                        if(accountList[i] != null)
                            if(accountList[i].accNum == genAccNum){
                                genAccNum = new Random().nextInt((9999 - 1000) + 1) + 1000;
                                w = true;
                            }
                            else
                                w = false;
                    }
                    }while (w == true);
                        
                    Account account = new Account(tfield[0].getText(), tfield[1].getText(), tfield[2].getText(), 
                            Long.parseUnsignedLong(tfield[3].getText()), Integer.parseInt(tfield[4].getText()), genAccNum);
                    if (accountList[accountList.length - 1] != null)
                        JOptionPane.showMessageDialog(popUp, "Only 50 Account can be added!",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    else if(Integer.parseInt(tfield[4].getText()) < 5000 )
                        JOptionPane.showMessageDialog(popUp, "Initial Deposit must not be less than 5000",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    else{
                        for (int i = 0; i<accountList.length; i++){
                            if(accountList[i] == null){
                                accountList[i] = account;
                                JOptionPane.showMessageDialog(popUp,"Account number is: " + 
                                        Integer.toString(genAccNum),"Account Added", JOptionPane.PLAIN_MESSAGE);
                                System.out.println("Accunt Number is: " + Integer.toString(genAccNum));
                                for (i = 0; i < 5; i++)
                                    f[1].remove(tfield[i]);
                                ctr += 1;
                                f[1].dispose();
                                for (i = 1; i < 7; i++)
                                    MB[i].setEnabled(true);
                                break;
                            }
                            else if(accountList[i].accNum == account.accNum){
                                System.out.println("Account already added");
                                break;
                            }
                        }   
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
                int z = findAccount(Integer.parseInt(accNumField.getText().replaceAll("\\s+","")));
                if (z != 404){
                    JOptionPane.showMessageDialog(popUp, "Account Balance: " + Float.toString(accountList[z].deposit),
                        "Balance Inquiry", JOptionPane.PLAIN_MESSAGE);
                        f[2].dispose();
                }
            }
            if (state == 3){ //DEPOSIT
                try{
                    int z = findAccount(Integer.parseInt(accNumField.getText().replaceAll("\\s+","")));
                    if (z != 404){
                        if (Float.parseFloat(tfield[0].getText()) >= 100){
                            accountList[z].deposit += Float.parseFloat(tfield[0].getText());
                            double interest = accountList[z].deposit * .05;
                            accountList[z].deposit += interest;
                            JOptionPane.showMessageDialog(popUp, "You deposited: " + tfield[0].getText() + 
                                " + interest(" + Double.toString(interest) + ")",
                                "Deposit", JOptionPane.PLAIN_MESSAGE);
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
                    int z = findAccount(Integer.parseInt(accNumField.getText().replaceAll("\\s+","")));
                    if (z != 404){
                        double bal = accountList[z].deposit;
                        if (bal - Float.parseFloat(tfield[0].getText()) < 5000 || bal < 5100 )
                            JOptionPane.showMessageDialog(popUp, "Withdraw failed. The Account "
                            + "balance will fall below the maintaining balance of 5000.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                        else if(Float.parseFloat(tfield[0].getText()) < 100)
                            JOptionPane.showMessageDialog(popUp, "Withdraw must not be less than 100",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                        else{
                            accountList[z].deposit -= Float.parseFloat(tfield[0].getText());
                            JOptionPane.showMessageDialog(popUp, "You withdrawed: " + tfield[0].getText(),
                                "Withdraw", JOptionPane.PLAIN_MESSAGE);
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
                int z = findAccount(Integer.parseInt(accNumField.getText().replaceAll("\\s+","")));
                if (z != 404){
                    UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
                    JOptionPane.showMessageDialog(popUp, 
                        "<html><b>Name: </b>" + accountList[z].name + 
                        "\n<html><b>Address: </b>" + accountList[z].address +
                        "\n<html><b>Birthday: </b>" + accountList[z].Birthday +
                        "\n<html><b>Contact Number: </b>" + accountList[z].cntctNum +
                        "\n<html><b>Account Balance: </b>" + accountList[z].deposit,
                        "Client Profile", JOptionPane.PLAIN_MESSAGE);
                        f[5].dispose();
                }
            }
            if (state == 6){ //CLOSE ACCOUNT
                try{
                    int z = findAccount(Integer.parseInt(accNumField.getText().replaceAll("\\s+","")));
                    if (z != 404){
                        int y = JOptionPane.showConfirmDialog(popUp, "Are you sure you want to close account "
                                + accNumField.getText().replaceAll("\\s+","") + " registered to "
                                + accountList[z].name + "?");
                        if(y == JOptionPane.YES_OPTION){
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
    public int findAccount(int accNum){
        try{
            if (accNum < 1000){
                    JOptionPane.showMessageDialog(popUp, "Invalid Account Number",
                    "Error", JOptionPane.PLAIN_MESSAGE);
                    return 404;
            }
            for (int i = 0; i < accountList.length; i++){
                if(accountList[i] != null)
                    if(accountList[i].accNum == Integer.parseInt(accNumField.getText())){
                        return i;
                    }
            }
            JOptionPane.showMessageDialog(popUp, "Account not found",
                    "ERROR", JOptionPane.PLAIN_MESSAGE);
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(popUp, "Field must not be blank",
                    "ERROR", JOptionPane.PLAIN_MESSAGE);
        }
        return 404;
    }
    
    public class Account{
    
        String name;
        String address;
        String Birthday;
        long cntctNum;
        float deposit;
        int accNum;
        Account(){
            
        }
        Account(String name, String address, String Birthday, long cntctNum, float deposit, int accNum){
            this.name = name;
            this.address = address;
            this.Birthday = Birthday;
            this.cntctNum = cntctNum;
            this.deposit = deposit;
            this.accNum = accNum;
        }
    }
    
    public static void main(String[] args) {
        new BankProject();
    }
    
}
