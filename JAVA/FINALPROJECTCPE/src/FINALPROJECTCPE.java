import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class FINALPROJECTCPE {
        static int CT = 0;
    static double bill = 0;
    public static void main(String[] args) throws IOException {
        JFrame POS = new JFrame("Papa John's Pizza");
        
        
        //PANELS--------------------------------
        JPanel OPTIONS = new JPanel();
        JPanel RECEIPT = new JPanel();
        JPanel BLANK = new JPanel();
        
        JPanel HOME = new JPanel(new GridBagLayout());
        
        JPanel MENU1 = new JPanel(new GridLayout(7,1,1,1));
        JPanel MENU2 = new JPanel(new GridLayout(2,1,1,1));
        JPanel MENU3 = new JPanel(new GridLayout(6,1,1,1));
        JPanel MENU4 = new JPanel(new GridLayout(5,1,1,1));
        JPanel MENU5 = new JPanel(new GridLayout(2,1,1,1));
        
        JPanel ITEM = new JPanel(new BorderLayout());
        JPanel BASKETContainer = new JPanel(new GridBagLayout());
        JPanel BILLContainer = new JPanel(new GridBagLayout());
        JPanel CATContainer = new JPanel(new GridBagLayout());
        //---------------------------------------
        //LABELS
        JLabel BASKET = new JLabel("BASKET");
        JLabel CONTAINS = new JLabel("OPTIONS");
        JLabel OPTION2 = new JLabel("CHOOSE");
        JLabel HOMELABEL = new JLabel("<html>Papa John's<br><center>Pizza</html>");
        JLabel SLOGAN = new JLabel("Better Ingredients. Better Pizza.");
        JLabel ITEMPRICE = new JLabel("");
        JLabel TOTALBILL = new JLabel("TOTAL BILL: ₱0.00");
        JLabel CAT1 = new JLabel("ITEM");
        JLabel CAT2 = new JLabel("OPTION");
        JLabel CAT3 = new JLabel("PRICE");
       
        //---------------------------------------

        //LABEL FORMAT
        Font font = new Font("Cambria", Font.PLAIN, 20);
        Font font2 = new Font("Cambria", Font.PLAIN, 14);
        BASKET.setFont(font);
        BASKET.setForeground(Color.decode("#D6D6D7"));
        BASKET.setHorizontalAlignment(JLabel.CENTER);
        BASKETContainer.add(BASKET);
        
        CONTAINS.setFont(font);
        CONTAINS.setForeground(Color.decode("#D6D6D7"));
        CONTAINS.setHorizontalAlignment(JLabel.CENTER);
        ITEM.add(CONTAINS, BorderLayout.PAGE_START);
        
        OPTION2.setBounds(0, 20, 199, 100);
        OPTION2.setHorizontalAlignment(JLabel.CENTER);
        OPTION2.setFont(font);
        OPTION2.setForeground(Color.decode("#D6D6D7"));
        
        ITEMPRICE.setFont(font);
        ITEMPRICE.setForeground(Color.decode("#D6D6D7"));
        
        HOMELABEL.setFont(new Font("Cambria", Font.PLAIN, 45));
        HOMELABEL.setForeground(Color.decode("#D6D6D7"));
        SLOGAN.setFont(new Font("Cambria", Font.PLAIN, 15));
        SLOGAN.setForeground(Color.decode("#D6D6D7"));
        SLOGAN.setHorizontalAlignment(JLabel.CENTER);
        TOTALBILL.setFont(font);
        TOTALBILL.setForeground(Color.decode("#D6D6D7"));
        GridBagConstraints c = new GridBagConstraints(); 
        c.weightx = 0.5;
        CATContainer.add(CAT1, c);
        CATContainer.add(CAT2, c);
        CATContainer.add(CAT3, c);
        CAT1.setFont(font2);
        CAT1.setForeground(Color.decode("#D6D6D7"));
        CAT2.setFont(font2);
        CAT2.setForeground(Color.decode("#D6D6D7"));
        CAT3.setFont(font2);
        CAT3.setForeground(Color.decode("#D6D6D7"));

        
        //--------------------------------------

        
        //LISTS
        DefaultListModel<String> ItemList = new DefaultListModel<>();
        DefaultListModel<String> OrderOption = new DefaultListModel<>();
        DefaultListModel<String> PriceList = new DefaultListModel<>();
        
        JList<String> item = new JList<>(ItemList);
        JList<String> option = new JList<>(OrderOption);
        JList<String> price = new JList<>(PriceList);
        
        double priceArray[][] = {{295, 430, 520}, {350, 485, 595}, {295, 430, 520}, 
            {350, 485, 595}, {295, 430, 520}, {295, 430, 520}, {350, 485, 595}, 
            {295, 430, 520}, {295, 430, 520}, {350, 485, 595}, {350, 485, 595}, 
            {99, 297}, {99, 297}, {99, 297}, {99, 297}, {99, 297}, {99, 297}, 
            {99, 297}, {190}, {99}, {199}, {199}, {199}, {175}, {175}, {145},
            {120}, {95}, {120}, {120}, {139}, {139}, {139}, {45, 50, 90}, {45, 50},
            {45, 50}, {30}};
             
        //--------------------------------------
        
        
        //ITEMS
        ButtonGroup bG = new ButtonGroup();
        JRadioButton RBUTTON1 = new JRadioButton("Regular  (9″)");
        JRadioButton RBUTTON2 = new JRadioButton("Family   (12″)");
        JRadioButton RBUTTON3 = new JRadioButton("Party     (14″)");
        RBUTTON1.setFont(font2);
        RBUTTON2.setFont(font2);
        RBUTTON3.setFont(font2);
        ITEMPRICE.setBounds(70, 400, 150, 20);
        ITEM.add(ITEMPRICE);
        
        RBUTTON1.setBounds(50, 120, 150, 20);
        RBUTTON2.setBounds(50, 150, 150, 20);
        RBUTTON3.setBounds(50, 180, 150, 20);
        
        ITEM.add(OPTION2);
        ITEM.add(RBUTTON1);
        ITEM.add(RBUTTON2);
        ITEM.add(RBUTTON3);
        ITEM.add(BLANK);
        
        RBUTTON1.setVisible(false); 
        RBUTTON2.setVisible(false); 
        RBUTTON3.setVisible(false); 

        RBUTTON1.setBackground(Color.decode("#004050"));
        RBUTTON2.setBackground(Color.decode("#004050"));
        RBUTTON3.setBackground(Color.decode("#004050"));
        
        RBUTTON1.setForeground(Color.decode("#D6D6D7"));
        RBUTTON2.setForeground(Color.decode("#D6D6D7"));
        RBUTTON3.setForeground(Color.decode("#D6D6D7"));
        
        bG.add(RBUTTON1);
        bG.add(RBUTTON2);
        bG.add(RBUTTON3);
        
        JButton ADDTOCART = new JButton("ADD TO CART");
        ADDTOCART.setBackground(Color.decode("#336673"));
        ADDTOCART.setForeground(Color.decode("#F1F1F1"));
        ADDTOCART.setFont(font);
        
        JPanel flowPanel = new JPanel(new FlowLayout());
        flowPanel.add(ADDTOCART);
        flowPanel.setBackground(Color.decode("#004050"));
        ITEM.add(BorderLayout.PAGE_END, flowPanel);
        ADDTOCART.setVisible(false);
        
        
        //--------------------------------------
        
        
        
        
        //RECEIPT
        RECEIPT.add(BASKETContainer);
        BASKETContainer.setBounds(0, 0, 265, 50);
        RECEIPT.add(CATContainer);
        CATContainer.setBounds(0, 51, 265, 30);
        CATContainer.setBackground(Color.decode("#004050"));
        
        JPanel BASKETITEM = new JPanel(new GridBagLayout());
        JScrollPane scrollITEM = new JScrollPane(BASKETITEM);
        RECEIPT.add(scrollITEM);
        scrollITEM.setBounds(0, 80, 255, 320);
        scrollITEM.setBorder(BorderFactory.createEmptyBorder());
        BILLContainer.add(TOTALBILL);
        
                
        JCheckBox Senior = new JCheckBox("Senior/PWD Discount");
        Senior.setFont(new Font("Cambria", Font.PLAIN, 12));
        Senior.setBackground(Color.decode("#004050"));
        Senior.setForeground(Color.decode("#F1F1F1"));
        c.gridy = 1;
        BILLContainer.add(Senior, c);
          
         Senior.addItemListener(new ItemListener() {    
             public void itemStateChanged(ItemEvent e) {     
                if(e.getStateChange()==1)
                    TOTALBILL.setText("TOTAL BILL: ₱" + String.format("%.2f", bill*(1 - .20)));    
                else
                    TOTALBILL.setText("TOTAL BILL: ₱" + String.format("%.2f", bill));  
             }    
          });    
        
        RECEIPT.add(BILLContainer);
        
        BILLContainer.setBounds(0, 401, 265, 50);
        BILLContainer.setBackground(Color.decode("#004050"));
        
        
        JButton SHOWRECEIPT = new JButton("RECEIPT");
        SHOWRECEIPT.setBackground(Color.decode("#336673"));
        SHOWRECEIPT.setForeground(Color.decode("#F1F1F1"));
        SHOWRECEIPT.setFont(font);
        JPanel BASKETBUTTON = new JPanel(new FlowLayout());
        RECEIPT.add(BASKETBUTTON);
        BASKETBUTTON.setBounds(0, 451, 265, 50);


        JPanel Itempanel = new JPanel(new BorderLayout());
        c.fill = GridBagConstraints.HORIZONTAL;  

        BASKETITEM.add(Itempanel, c);
        JPanel Optionpanel = new JPanel(new BorderLayout());

        BASKETITEM.add(Optionpanel, c);
        JPanel Pricepanel = new JPanel(new BorderLayout());
        BASKETITEM.add(Pricepanel, c);
        
        
        item.setBackground(Color.decode("#557F8A"));
        item.setForeground(Color.decode("#F1F1F1"));
        item.setFont(new Font("Cambria", Font.BOLD, 14));
        option.setBackground(Color.decode("#557F8A"));
        option.setForeground(Color.decode("#C0C0C0"));
        option.setFont(new Font("Cambria", Font.PLAIN, 14));
        price.setBackground(Color.decode("#557F8A"));
        price.setForeground(Color.decode("#C0C0C0"));
        price.setFont(new Font("Cambria", Font.PLAIN, 14));
        
        
        Itempanel.add(item);
        Optionpanel.add(option);
        Pricepanel.add(price);
               
        JButton REMOVE = new JButton("REMOVE");
        REMOVE.setBackground(Color.decode("#336673"));
        REMOVE.setForeground(Color.decode("#F1F1F1"));
        REMOVE.setFont(font);
        BASKETBUTTON.add(REMOVE);
        BASKETBUTTON.add(SHOWRECEIPT);
        
        ADDTOCART.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            ItemList.addElement(OPTION2.getText());
            int count = (OPTION2.getText().length() - 
                    OPTION2.getText().replace("<br>", "").length())/4;
            String temp = "<html>";
            for(int i = 0; i < count; i++){
                temp += "<br>";
            }
            if(RBUTTON1.isSelected()){
                OrderOption.addElement(temp + RBUTTON1.getText() + "</html>");
                PriceList.addElement(temp + "&emsp ₱" + priceArray[CT - 1][0] + "<html>");    
                bill += priceArray[CT - 1][0];
            }
            else if(RBUTTON2.isSelected()){
                OrderOption.addElement(temp + RBUTTON2.getText() + "</html>");
                PriceList.addElement(temp + "&emsp ₱" + priceArray[CT - 1][1] + "<html>");
                bill += priceArray[CT - 1][1];
            }
            else if(RBUTTON3.isSelected()){
                OrderOption.addElement(temp + RBUTTON3.getText() + "</html>");
                PriceList.addElement(temp + "&emsp ₱" + priceArray[CT - 1][2] + "<html>");
                bill += priceArray[CT - 1][2];
            }
            scrollITEM.getVerticalScrollBar().setValue(scrollITEM.getVerticalScrollBar().getMaximum());
            
            if(Senior.isSelected())
                TOTALBILL.setText("TOTAL BILL: ₱" + String.format("%.2f", bill*(1 - .20)));
            else
                TOTALBILL.setText("TOTAL BILL: ₱" + String.format("%.2f", bill));
        }
        });

        item.addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent e){
            int index = item.getSelectedIndex();
            option.setSelectedIndex(index);
            price.setSelectedIndex(index);
        }
        });  
        
        option.addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent e){
            int index = option.getSelectedIndex();
            item.setSelectedIndex(index);
            price.setSelectedIndex(index);
        }
        });  
        
        price.addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent e){
            int index = price.getSelectedIndex();
            item.setSelectedIndex(index);
            option.setSelectedIndex(index);
        }
        });  
        

        
        REMOVE.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            int index = item.getSelectedIndex();
            if(index > -1){
                bill -= Double.parseDouble(PriceList.getElementAt(index)
                        .replace("₱","").replace(" ","").replace("<html>","")
                        .replace("</html>","").replace("<br>","").replace("&emsp",""));
                ItemList.removeElementAt(index);
                OrderOption.removeElementAt(index);
                PriceList.removeElementAt(index);
            }
            if(Senior.isSelected())
                TOTALBILL.setText("TOTAL BILL: ₱" + String.format("%.2f", bill*(1 - .20)));
            else
                TOTALBILL.setText("TOTAL BILL: ₱" + String.format("%.2f", bill));
        }
        
        });
        
        //PRINTING
        File file = new File("C:\\test\\Hadjipogi.txt");
        Formatter fos = new Formatter(new FileOutputStream(file));
        
        //--------------------------------------      
        
        SHOWRECEIPT.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent e){
            int confirmed = JOptionPane.showConfirmDialog(null, 
                "Proceed printing the receipt and exiting the program?", "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                fos.format("%-30s%-18s%10s%n", "ITEM NAME", "OPTION", "PRICE");
                for(int x = 0; x <  ItemList.getSize(); x++){
                    fos.format("%-30s%-18s%10s%n",ItemList.getElementAt(x).replace("<html>","")
                            .replace("</html>","").replace("<br>"," "), 
                            OrderOption.getElementAt(x).replace("<html>","").replace("</html>","")
                            .replace("<br>",""), PriceList.getElementAt(x).replace("&emsp","")
                            .replace("<html>","").replace("</html>","").replace("<br>",""));
                }
                if(Senior.isSelected())
                    fos.format("%-30s%-18s%10s%n", "SENIOR/PWD", "20% DISCOUNT", " ₱-" + 
                            String.format("%.2f", bill*.20));                    
                for(int i = 0; i < 58; i++){
                    fos.format("-");
                }
                if(Senior.isSelected())
                    fos.format("%n%-30s%-18s%10s", "", "TOTAL BILL: ", "₱" + 
                            String.format("%.2f", bill*(1 - .20)));
                else
                    fos.format("%n%-30s%-18s%10s", "", "TOTAL BILL: ", "₱" + 
                            String.format("%.2f", bill));
                
                fos.close();
                JOptionPane.showMessageDialog(POS, "Receipt saved at " + file);
                POS.dispose(); 
            }
        }
        });
        
                
        //SHOW PRICE
        RBUTTON1.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            
            ITEMPRICE.setText("₱" + priceArray[CT - 1][0]);
        }
        });
        RBUTTON2.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            ITEMPRICE.setText("₱" + priceArray[CT - 1][1]);
        }
        });
        RBUTTON3.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            ITEMPRICE.setText("₱" + priceArray[CT - 1][2]);
        }
        });

        //--------------------------------------
        
        OPTIONS.setBounds(0, 0, 599, 36);
        RECEIPT.setBounds(600, 0, 265, 501);
        RECEIPT.setLayout(null);
       
        HOME.setBounds(0, 36, 400, 465);
        
        MENU1.setBounds(0, 36, 401, 465);
        MENU2.setBounds(0, 36, 401, 465);
        MENU3.setBounds(0, 36, 401, 465);
        MENU4.setBounds(0, 36, 401, 465);      
        MENU5.setBounds(0, 36, 401, 465);
        
        ITEM.setBounds(401, 36, 198, 465);
        HOME.add(HOMELABEL);
        HOME.add(SLOGAN, c);
       
        
        JButton[] B = new JButton[5];
                
        for(int i = 0; i < 5; i++){
            B[i] = new JButton();
            B[i].setBackground(Color.decode("#336673"));
            B[i].setFont(new Font("Cambria", Font.PLAIN, 13));
            B[i].setForeground(Color.decode("#F1F1F1"));
        }
        
        B[0].setText("SPECIALTY PIZZAS");
        B[1].setText("CLASSIC PIZZAS");
        B[2].setText("PASTAS");
        B[3].setText("SIDE ITEMS");
        B[4].setText("BEVERAGES");

        for(int i = 0; i < 5; i++){
            OPTIONS.add(B[i]);
        }

        
        MENU1.setVisible(false);
        MENU2.setVisible(false);
        MENU3.setVisible(false);
        MENU4.setVisible(false); 
        MENU5.setVisible(false);
        
        B[0].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            HOME.setVisible(false);
            MENU1.setVisible(true);
            MENU2.setVisible(false);
            MENU3.setVisible(false);
            MENU4.setVisible(false); 
            MENU5.setVisible(false);
            RBUTTON1.setText("Regular  (9″)");
            RBUTTON2.setText("Family   (12″)");
            RBUTTON3.setText("Party     (14″)");
            RBUTTON1.setVisible(false);
            RBUTTON2.setVisible(false);
            RBUTTON3.setVisible(false);;
            RBUTTON1.setSelected(true);
            OPTION2.setText("<html>SPECIALTY<br>PIZZAS</html>");
        }
        });
        B[1].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            HOME.setVisible(false);
            MENU1.setVisible(false);
            MENU2.setVisible(true);
            MENU3.setVisible(false);
            MENU4.setVisible(false); 
            MENU5.setVisible(false);
            RBUTTON1.setText("Regular  (9″)");
            RBUTTON2.setText("Family   (12″)");
            RBUTTON3.setText("Party     (14″)");
            RBUTTON1.setVisible(false);
            RBUTTON2.setVisible(false);
            RBUTTON3.setVisible(false);
            RBUTTON1.setSelected(true);
            OPTION2.setText("<html>CLASSIC<br>PIZZAS</html>");
        }
        });
        B[2].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            HOME.setVisible(false);
            MENU1.setVisible(false);
            MENU2.setVisible(false);
            MENU3.setVisible(true);
            MENU4.setVisible(false); 
            MENU5.setVisible(false);
            RBUTTON1.setText("Single");
            RBUTTON2.setText("Platter");
            RBUTTON1.setVisible(false);
            RBUTTON2.setVisible(false);
            RBUTTON3.setVisible(false);
            RBUTTON1.setSelected(true);
            OPTION2.setText("PASTAS");
        }
        });
        B[3].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            HOME.setVisible(false);
            MENU1.setVisible(false);
            MENU2.setVisible(false);
            MENU3.setVisible(false);
            MENU4.setVisible(true);
            MENU5.setVisible(false);
            RBUTTON1.setText("Single");
            RBUTTON1.setVisible(false);
            RBUTTON2.setVisible(false);
            RBUTTON3.setVisible(false);
            RBUTTON1.setSelected(true);
            OPTION2.setText("SIDE ITEMS");
        }
        });
        B[4].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            HOME.setVisible(false);
            MENU1.setVisible(false);
            MENU2.setVisible(false);
            MENU3.setVisible(false);
            MENU4.setVisible(false);
            MENU5.setVisible(true);
            RBUTTON1.setSelected(true);
            RBUTTON1.setVisible(false);
            RBUTTON2.setVisible(false);
            RBUTTON3.setVisible(false);
            OPTION2.setText("BEVERAGES");
        }
        });
                
        JButton[] SPIZZA = new JButton[7];
        for(int i = 0; i < 7; i++){
            SPIZZA[i] = new JButton();
            SPIZZA[i].setBackground(Color.decode("#336673"));
            SPIZZA[i].setFont(font2);
            SPIZZA[i].setForeground(Color.decode("#F1F1F1"));
        }
        
        
        SPIZZA[0].setText("Honey Bae");
        SPIZZA[1].setText("Super Papa");
        SPIZZA[2].setText("Texas Heat");
        SPIZZA[3].setText("Cheesy Bacon Melt");
        SPIZZA[4].setText("Chicken BBQ");
        SPIZZA[5].setText("Chicken Bacon Ranch");
        SPIZZA[6].setText("All The Meats");
        for(int i = 0; i < 7; i++){
            MENU1.add(SPIZZA[i]);
        }
        
        
        SPIZZA[0].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Honey Bae");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 1;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SPIZZA[1].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Super Papa");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 2;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SPIZZA[2].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Texas Heat");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 3;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SPIZZA[3].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Cheesy Bacon<br>Melt</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 4;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SPIZZA[4].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Chicken BBQ");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 5;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SPIZZA[5].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Chicken Bacon<br>Ranch</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 6;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SPIZZA[6].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("All The Meats");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 7;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        
        JButton[] CPIZZA = new JButton[4];
        for(int i = 0; i < 4; i++){
            CPIZZA[i] = new JButton();
            CPIZZA[i].setBackground(Color.decode("#336673"));
            CPIZZA[i].setFont(font2);
            CPIZZA[i].setForeground(Color.decode("#F1F1F1"));
        
        }
        CPIZZA[0].setText("Simply Cheese");
        CPIZZA[1].setText("Garden Special");
        CPIZZA[2].setText("Pepperoni");
        CPIZZA[3].setText("The Hawaiian");
        
        for(int i = 0; i < 4; i++){
            MENU2.add(CPIZZA[i]);
        }        
        
        CPIZZA[0].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Simply<br>Cheese</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 8;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        CPIZZA[1].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Garden<br>Special</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 9;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        CPIZZA[2].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Pepperoni");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 10;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        CPIZZA[3].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>The<br>Hawaiian</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 11;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        JButton[] PASTA = new JButton[12];
        for(int i = 0; i < 12; i++){
            PASTA[i] = new JButton();
            PASTA[i].setBackground(Color.decode("#336673"));
            PASTA[i].setFont(font2);
            PASTA[i].setForeground(Color.decode("#F1F1F1"));
            PASTA[i].setPreferredSize(new Dimension(100, 50));
        }
        PASTA[0].setText("Three Cheese");
        PASTA[1].setText("<html>Mediterranean Seafood<br><center>(Carbonara sauce)</html>");
        PASTA[2].setText("Spicy Mushroom");
        PASTA[3].setText("Spinach Alfredo");
        PASTA[4].setText("Creamy Pesto");
        PASTA[5].setText("Cheesy Mushroom");
        PASTA[6].setText("<html>Cheese & Bacon<br><center>in Red Sauce</html>");
        PASTA[7].setText("Carbonara");
        PASTA[8].setText("<html>Mediterranean Seafood<br><center>(Tomato sauce)</html>");
        PASTA[9].setText("Classic Spaghetti");
        PASTA[10].setText("Italian Style");
        PASTA[11].setText("Mushroom Alfredo");
        
        for(int i = 0; i < 12; i++){
            MENU3.add(PASTA[i]);
        }
        
        
        PASTA[0].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Three Cheese");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 12;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[1].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Mediterranean<br>Seafood(CS)</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 13;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[2].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Spicy<br>Mushroom</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 14;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[3].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Spinach<br>Alfredo</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 15;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[4].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Creamy Pesto");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 16;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[5].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Cheesy<br>Mushroom</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 17;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[6].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Cheese &<br>Bacon in<br>Red Sauce</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 18;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[7].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Carbonara");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(false);
            RBUTTON1.setSelected(true);
            ADDTOCART.setVisible(true);
            CT = 19;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[8].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Mediterranean<br>Seafood(TS)</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(false);
            RBUTTON1.setSelected(true);
            ADDTOCART.setVisible(true);
            CT = 20;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[9].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Classic<br>Spaghetti</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(false);
            RBUTTON1.setSelected(true);
            ADDTOCART.setVisible(true);
            CT = 21;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[10].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Italian Style");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(false);
            RBUTTON1.setSelected(true);
            ADDTOCART.setVisible(true);
            CT = 22;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        PASTA[11].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Mushroom<br>Alfredo</html>");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(false);
            RBUTTON1.setSelected(true);
            ADDTOCART.setVisible(true);
            CT = 23;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        JButton[] SIDES = new JButton[10];
        for(int i = 0; i < 10; i++){
            SIDES[i] = new JButton();
            SIDES[i].setBackground(Color.decode("#336673"));
            SIDES[i].setFont(font2);
            SIDES[i].setForeground(Color.decode("#F1F1F1"));
            SIDES[i].setPreferredSize(new Dimension(100, 50));
        }
        
        SIDES[0].setText("<html>Pepperoni & Mushroom<br><center>Calzone</html>");
        SIDES[1].setText("<html>Chicken & Mushroom<br><center>Calzone</html>");
        SIDES[2].setText("Ultimate Potato Wedges");
        SIDES[3].setText("Seasoned Potato Wedges");
        SIDES[4].setText("Pepperoncini Dynamite");
        SIDES[5].setText("<html>Herbed Parmesan<br><center>Breadsticks</html>");
        SIDES[6].setText("Cheesesticks");
        SIDES[7].setText("Sweet Caramel Stromboli");
        SIDES[8].setText("Veggie Stromboli");
        SIDES[9].setText("Savory Stromboli");
        
        for(int i = 0; i < 10; i++){
            MENU4.add(SIDES[i]);
        }
        
        
        
        SIDES[0].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Pepperoni<br>& Mushroom<br>Calzone</html>");
            RBUTTON1.setText("Single");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 24;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[1].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Chicken<br>& Mushroom<br>Calzone</html>");
            RBUTTON1.setText("Single");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 25;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[2].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Ultimate<br>Potato<br>Wedges</html>");
            RBUTTON1.setText("Single");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 26;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[3].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Seasoned<br>Potato<br>Wedges</html>");
            RBUTTON1.setText("Single");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 27;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[4].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Pepperoncini<br>Dynamite</html>");
            RBUTTON1.setText("Single");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 28;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[5].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Herbed<br>Parmesan<br>Breadsticks</html>");
            RBUTTON1.setText("Single");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 29;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[6].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Cheesesticks");
            RBUTTON1.setText("Single");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 30;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[7].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Sweet<br>Caramel<br>Stromboli</html>");
            RBUTTON1.setText("7pcs");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 31;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[8].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Veggie<br>Stromboli</html>");
            RBUTTON1.setText("7pcs");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 32;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        SIDES[9].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Savory<br>Stromboli</html>");
            RBUTTON1.setText("7pcs");
            RBUTTON1.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 33;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        JButton[] BEV = new JButton[4];
        for(int i = 0; i < 4; i++){
            BEV[i] = new JButton();
            BEV[i].setBackground(Color.decode("#336673"));
            BEV[i].setFont(font2);
            BEV[i].setForeground(Color.decode("#F1F1F1"));
            
        }
        BEV[0].setText("Coca Cola");
        BEV[1].setText("Coca Cola Zero");
        BEV[2].setText("Sarsi");
        BEV[3].setText("Bottled Water");
        for(int i = 0; i < 4; i++){
            MENU5.add(BEV[i]);
        }
        
        BEV[0].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Coca Cola");
            RBUTTON1.setText("Single");
            RBUTTON2.setText("In Can");
            RBUTTON3.setText("1.5L");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(true);
            ADDTOCART.setVisible(true);
            CT = 34;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        BEV[1].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Coca Cola<br>Zero</html>");
            RBUTTON1.setText("Single");
            RBUTTON2.setText("In Can");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(false);
            RBUTTON1.setSelected(true);
            ADDTOCART.setVisible(true);
            CT = 35;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        BEV[2].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("Sarsi");
            RBUTTON1.setText("Single");
            RBUTTON2.setText("In Can");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(true);
            RBUTTON3.setVisible(false);
            RBUTTON1.setSelected(true);
            ADDTOCART.setVisible(true);
            CT = 36;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        BEV[3].addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
            OPTION2.setText("<html>Bottled<br>Water</html>");
            RBUTTON1.setText("(12 oz)");
            RBUTTON1.setVisible(true);
            RBUTTON2.setVisible(false);
            RBUTTON3.setVisible(false);
            RBUTTON1.setSelected(true);
            ADDTOCART.setVisible(true);
            CT = 37;
            selected(RBUTTON1, RBUTTON2, RBUTTON3, 
                    ITEMPRICE, priceArray);
        }
        });
        
        
        //PANEL COLORS
        OPTIONS.setBackground(Color.decode("#004050"));
        RECEIPT.setBackground(Color.decode("#557F8A"));
        HOME.setBackground(Color.decode("#004050"));
        MENU1.setBackground(Color.decode("#004050"));
        MENU2.setBackground(Color.decode("#004050"));
        MENU3.setBackground(Color.decode("#004050"));
        MENU4.setBackground(Color.decode("#004050"));
        MENU5.setBackground(Color.decode("#004050"));
        ITEM.setBackground(Color.decode("#004050"));
        BASKETITEM.setBackground(Color.decode("#004050"));
        BASKETBUTTON.setBackground(Color.decode("#004050"));
        BASKETContainer.setBackground(Color.decode("#004050"));
        POS.getContentPane().setBackground(Color.decode("#557F8A"));
        POS.setLayout(null);
        POS.add(OPTIONS);
        POS.add(RECEIPT);
        POS.add(HOME);
        POS.add(MENU1);
        POS.add(MENU2);
        POS.add(MENU3);
        POS.add(MENU4);
        POS.add(MENU5);
        POS.add(ITEM);
        POS.add(BLANK);
        POS.setResizable(false);
        POS.setSize(860, 530);
        POS.setVisible(true);
        POS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    static void selected(JRadioButton RBUTTON1, JRadioButton RBUTTON2, 
            JRadioButton RBUTTON3, JLabel ITEMPRICE, double[][] priceArray){
        
        if(RBUTTON1.isSelected())
            ITEMPRICE.setText("₱" + priceArray[CT - 1][0]);
        else if(RBUTTON2.isSelected())
            ITEMPRICE.setText("₱" + priceArray[CT - 1][1]);
        else if(RBUTTON3.isSelected())
            ITEMPRICE.setText("₱" + priceArray[CT - 1][2]);
    }
}