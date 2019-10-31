package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main extends MouseAdapter implements DocumentListener, ActionListener, ItemListener {
    private JTextArea inField = new JTextArea();
    private JTextArea outField = new JTextArea();
    private JTextField caesarShift = new JTextField("1");
    private JTextField vignereKey = new JTextField("Encrypt");
    private JTextField affineKey_1 = new JTextField("1");
    private JTextField affineKey_2 = new JTextField("1");
    private JPanel optionCards;
    private JButton caesarSub = new JButton();
    private JButton caesarAdd = new JButton();
    private JButton affineSub1 = new JButton();
    private JButton affineAdd1 = new JButton();
    private JButton affineSub2 = new JButton();
    private JButton affineAdd2 = new JButton();
    private JLabel plainText = new JLabel("PLAINTEXT");
    private JLabel cipherText = new JLabel("CIPHERTEXT");
    private String letters;
    private JLabel error = new JLabel();
    private JComboBox<String> cipher;
    private JComboBox<String> encodeDecode;
        private Main(){
            JFrame Main = new JFrame("Text Encryptor");
            Main.setLayout( new GridBagLayout());

            JLabel caesarLabel = new JLabel(" Shift");
            JLabel vignereLabel = new JLabel(" Key");
            JLabel affineLabel = new JLabel(" Key 1                                  Key 2");

            caesarShift.setHorizontalAlignment(JTextField.CENTER);
            vignereKey.setHorizontalAlignment(JTextField.CENTER);
            affineKey_1.setHorizontalAlignment(JTextField.CENTER);
            affineKey_2.setHorizontalAlignment(JTextField.CENTER);

            caesarLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            vignereLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            affineLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

            String[] ciphers = {"Caesar Cipher", "Viegnere Cipher", "Atbash Cipher", "Affine Cipher"};
            String[] options = {"Encode", "Decode"};

            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            cipher = new JComboBox<>(ciphers);
            encodeDecode = new JComboBox<>(options);


            encodeDecode.addItemListener(this);
            cipher.addItemListener(this);
            inField.getDocument().addDocumentListener(this);

            caesarSub.addActionListener(this);
            caesarAdd.addActionListener(this);

            affineSub1.addActionListener(this);
            affineAdd1.addActionListener(this);
            affineSub2.addActionListener(this);
            affineAdd2.addActionListener(this);



            caesarSub.addMouseListener(this);
            caesarAdd.addMouseListener(this);
            affineSub1.addMouseListener(this);
            affineAdd1.addMouseListener(this);
            affineSub2.addMouseListener(this);
            affineAdd2.addMouseListener(this);


            caesarShift.getDocument().addDocumentListener(this);
            vignereKey.getDocument().addDocumentListener(this);
            affineKey_1.getDocument().addDocumentListener(this);
            affineKey_2.getDocument().addDocumentListener(this);


            caesarSub.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));
            caesarAdd.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
            affineSub1.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));
            affineAdd1.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
            affineSub2.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));
            affineAdd2.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));



            inField.setLineWrap(true);
            outField.setLineWrap(true);
            JScrollPane inScroll = new JScrollPane(inField);
            JScrollPane outScroll = new JScrollPane(outField);

            Dimension shiftKey = new Dimension(40, 26);

            caesarSub.setPreferredSize(shiftKey);
            caesarAdd.setPreferredSize(shiftKey);

            affineSub1.setPreferredSize(shiftKey);
            affineAdd1.setPreferredSize(shiftKey);
            affineAdd2.setPreferredSize(shiftKey);
            affineSub2.setPreferredSize(shiftKey);

            caesarShift.setPreferredSize(shiftKey);
            affineKey_1.setPreferredSize(shiftKey);
            affineKey_2.setPreferredSize(shiftKey);


            Font font = new Font("Cambria", Font.PLAIN, 15);

            caesarShift.setFont(font);
            vignereKey.setFont(font);
            inField.setFont(font);
            outField.setFont(font);
            cipher.setFont(font);
            cipher.setForeground(Color.BLACK);
            encodeDecode.setFont(font);
            encodeDecode.setForeground(Color.BLACK);


            optionCards = new JPanel(new CardLayout());
            

            JPanel caesarCard = new JPanel();
            caesarCard.setLayout(new BoxLayout(caesarCard, BoxLayout.PAGE_AXIS));

            JPanel caesarPanel = new JPanel();
            caesarPanel.add(caesarSub);
            caesarPanel.add(caesarShift);
            caesarPanel.add(caesarAdd);

            caesarCard.add(caesarLabel);
            caesarCard.add(caesarPanel);

            JPanel vignereCard = new JPanel();
            vignereCard.setLayout(new BoxLayout(vignereCard, BoxLayout.PAGE_AXIS));
            vignereKey.setHorizontalAlignment(vignereKey.CENTER);
            vignereCard.add(vignereLabel);
            vignereCard.add(vignereKey);

            JPanel atbashCard = new JPanel();
            JLabel hehe = new JLabel("ATBASH CIPHER");
            hehe.setFont( new Font("Cambria", Font.PLAIN, 25));
            atbashCard.add(hehe);

            JPanel affineCard = new JPanel();
            affineCard.setLayout(new BoxLayout(affineCard, BoxLayout.PAGE_AXIS));
            JPanel affinePanel = new JPanel();
            affinePanel.add(affineSub1);
            affinePanel.add(affineKey_1);
            affinePanel.add(affineAdd1);
            affinePanel.add(affineSub2);
            affinePanel.add(affineKey_2);
            affinePanel.add(affineAdd2);

            affineCard.add(affineLabel);
            affineCard.add(affinePanel);

            optionCards.add(caesarCard, ciphers[0]);
            optionCards.add(vignereCard, ciphers[1]);
            optionCards.add(atbashCard, ciphers[2]);
            optionCards.add(affineCard, ciphers[3]);

            JPanel topPanel = new JPanel();
            topPanel.setLayout(new GridBagLayout());
            JPanel optionPanel1 = new JPanel();
            optionPanel1.setLayout(new FlowLayout());
            optionPanel1.add(cipher);
            optionPanel1.add(encodeDecode);

            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

            JPanel textPanel = new JPanel();
            textPanel.add(error);

            plainText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            cipherText.setAlignmentX(JComponent.CENTER_ALIGNMENT);

            JPanel plainPanel = new JPanel();
            JPanel cipherPanel = new JPanel();

            plainPanel.setLayout(new BoxLayout(plainPanel, BoxLayout.PAGE_AXIS));
            cipherPanel.setLayout(new BoxLayout(cipherPanel, BoxLayout.PAGE_AXIS));

            plainPanel.add(plainText);
            plainPanel.add(inScroll);
            cipherPanel.add(cipherText);
            cipherPanel.add(outScroll);

            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.ipady = 0;
            c.gridwidth = 3;
            c.gridheight = 1;
            c.gridx = 0;
            c.gridy = 0;
            topPanel.add(optionPanel1, c);

            c.gridy = 1;
            topPanel.add(optionCards, c);

            c.gridy = 2;
            topPanel.add(textPanel, c);

            c.gridy = 0;
            Main.add(topPanel, c);

            c.weighty = 0.5;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(0,15,0,15);
            c.gridy = 1;
            Main.add(plainPanel, c);

            c.insets = new Insets(0,15,15,15);
            c.gridy = 2;
            Main.add(cipherPanel, c);






            optionPanel1.setBackground(Color.decode("#77D9FF"));
            labelPanel.setBackground(Color.decode("#77D9FF"));
            caesarCard.setBackground(Color.decode("#77D9FF"));
            caesarPanel.setBackground(Color.decode("#77D9FF"));
            vignereCard.setBackground(Color.decode("#77D9FF"));
            atbashCard.setBackground(Color.decode("#77D9FF"));
            affineCard.setBackground(Color.decode("#77D9FF"));
            affinePanel.setBackground(Color.decode("#77D9FF"));
            textPanel.setBackground(Color.decode("#77D9FF"));
            plainPanel.setBackground(Color.decode("#77D9FF"));
            cipherPanel.setBackground(Color.decode("#77D9FF"));
            Main.getContentPane().setBackground(Color.decode("#77D9FF"));


            affineSub1.setBackground(Color.decode("#D1F2FF"));
            affineAdd1.setBackground(Color.decode("#D1F2FF"));
            affineSub2.setBackground(Color.decode("#D1F2FF"));
            affineAdd2.setBackground(Color.decode("#D1F2FF"));


            Main.setResizable(true);
            Main.setSize(370, 450);
            Main.setMinimumSize(new Dimension(300, 300));
            Main.setVisible(true);
            Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void cipherDecipher(){
        if (validateShiftOrKey()) {
            String text = "";
            switch (cipher.getSelectedIndex()) {
                case 0:
                    text = caesarCipher();
                    break;
                case 1:
                    text = vigenereCipher();
                    break;
                case 2:
                    text = atbashCipher();
                    break;
                case 3:
                    text = affineCipher();
                    break;
            }
            outField.setText(text);
        }
    }
    private String caesarCipher(){
        StringBuilder text;
        int cShift = Integer.parseInt(caesarShift.getText());
        text = new StringBuilder(inField.getText());
        for (int i = 0; i < inField.getText().length(); i++) {
            int val = text.charAt(i);
            if(isAlpha(Character.toString(text.charAt(i)))) {
                if (encodeDecode.getSelectedIndex() == 0)
                    val = text.charAt(i) + cShift;
                else
                    val = text.charAt(i) - cShift;
                if (text.charAt(i) > 64 && text.charAt(i) < 91) {
                    while (val > 90)
                        val -= 26;
                    while (val <= 64)
                        val += 26;
                } else if (text.charAt(i) > 96 && text.charAt(i) < 123) {
                    while (val > 122)
                        val -= 26;
                    while (val <= 96)
                        val += 26;
                }
            }
            text.setCharAt(i, (char) val);
        }
        return text.toString();

    }
    private String vigenereCipher(){
        StringBuilder text = new StringBuilder();
        int x = 0;
        for(int i = 0; i < inField.getText().length(); i++){
            if(isAlpha(Character.toString(inField.getText().charAt(i)))) {
                text.append(vignereKey.getText().charAt(x));
                x++;
                if (x == vignereKey.getText().length())
                    x = 0;
            }
            else
                text.append(inField.getText().charAt(i));
        }
        for(int i = 0; i < inField.getText().length(); i++) {
            int index, index2;
            index = letters.indexOf(text.charAt(i));
            if (index == -1)
                continue;
            else if(index >= 26)
                index -= 26;
            index2 = letters.indexOf(inField.getText().charAt(i));
            if (index2 != -1){
                if (encodeDecode.getSelectedIndex() == 0) {
                    index = index2 + index;
                    while (index > 25)
                        index -= 26;
                }else{
                    index = index2 - index;
                    if (index < 0)
                        index += 26;
                    else if (index > 25)
                        index -= 26;
                }
                if (index2 >= 26)
                    index += 26;
                text.setCharAt(i, letters.charAt(index));
            }
        }
        return text.toString();
    }
    private String atbashCipher(){
        StringBuilder text = new StringBuilder();
        StringBuilder text2 = new StringBuilder(letters);
        text2.reverse();

        for(int i = 0;i < inField.getText().length(); i++) {
            int index;
            if (isAlpha(Character.toString(inField.getText().charAt(i)))) {
                if (Character.isUpperCase(inField.getText().charAt(i))) {
                    index = letters.indexOf(inField.getText().charAt(i)) - 26;
                    text.append(text2.charAt(index));
                } else {
                    index = letters.indexOf(inField.getText().charAt(i)) + 26;
                    text.append(text2.charAt(index));
                }
            }
            else
                text.append(inField.getText().charAt(i));
        }
        return text.toString();
    }
    private String affineCipher(){
        //ENCRYPT (Ax+B) % 26
        //DECRPYT c(x - B) mod 26
        // c =  (X * KEY)% 26 == 1
        StringBuilder text = new StringBuilder();

        if (encodeDecode.getSelectedIndex() == 1){
            int inverse = 0;
            for(int i = 0; i < 26; i++){
                inverse = (i * Integer.parseInt(affineKey_1.getText())) % 26;
                if (inverse == 1) {
                    inverse = i;
                    break;
                }
            }
            for(int i = 0; i < inField.getText().length(); i++) {
                int index = letters.indexOf(inField.getText().charAt(i));
                int val = 0;
                if (index == -1) {
                    text.append(inField.getText().charAt(i));
                    continue;
                }
                if(index <= 25){
                    val = (inverse * (index - Integer.parseInt(affineKey_2.getText()))) % 26;
                    if (val < 0)
                        val += 26;
                }
                else if (index <= 51){
                    index -= 26;
                    val = (inverse * (index - Integer.parseInt(affineKey_2.getText()))) % 26;
                    if (val < 0)
                        val += 26;
                    val += 26;
                }
                text.append(letters.charAt(val));
            }
            return text.toString();
        }
        for(int i = 0;i < inField.getText().length(); i++) {
            int index = letters.indexOf(inField.getText().charAt(i));
            if (index == -1) {
                text.append(inField.getText().charAt(i));
                continue;
            }
            int val = 0;
            if(index <= 25){
                val = Integer.parseInt(affineKey_1.getText()) * index +
                        Integer.parseInt(affineKey_2.getText()) % 26;
                while(val >= 26)
                    val -= 26;
            }else if (index <= 51){
                index -= 26;
                val = Integer.parseInt(affineKey_1.getText()) * index +
                        Integer.parseInt(affineKey_2.getText()) % 26;
                while(val >= 26)
                    val -= 26;
                val+= 26;
            }
            text.append(letters.charAt(val));
        }
        return text.toString();
    }
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == caesarSub)
            addSub(0, caesarShift);
        else if(e.getSource() == caesarAdd)
            addSub(1, caesarShift);
        else if(e.getSource() == affineSub1)
            addSub(0, affineKey_1);
        else if(e.getSource() == affineAdd1)
            addSub(1, affineKey_1);
        else if(e.getSource() == affineSub2)
            addSub(2, affineKey_2);
        else if(e.getSource() == affineAdd2)
            addSub(3, affineKey_2);
        cipherDecipher();
    }
    private void addSub(int e, JTextField shiftOrKey){
        if(!validateShiftOrKey()) {
            shiftOrKey.setText("1");
            return;
        }
        shiftOrKey.getDocument().removeDocumentListener(this);
        int x = Integer.parseInt(shiftOrKey.getText());
        if(cipher.getSelectedIndex() == 0){
            if (e == 1)
                shiftOrKey.setText(String.valueOf(x + 1));
            else if(e == 0)
                shiftOrKey.setText(String.valueOf(x - 1));
        }
        else if(cipher.getSelectedIndex() == 3){
            int[] factors  = {2, 13, 26};
            if (e == 0){
                for(x -= 1; x > 0; x--){
                    boolean bool = true;
                    for(Integer i : factors){
                        if(x % i == 0){
                            bool = false;
                            break;
                        }
                    }
                    if (bool) {
                        shiftOrKey.setText(String.valueOf(x));
                        break;
                    }
                }
            }
            else if (e == 1){
                x++;
                while(true){
                    boolean bool = true;
                    for(Integer i : factors){
                        if(x % i == 0){
                            bool = false;
                            x++;
                            break;
                        }
                    }
                    if (bool) {
                        shiftOrKey.setText(String.valueOf(x));
                        break;
                    }
                }
            }
            else if (e == 2){
                if (x > 1)
                    shiftOrKey.setText(String.valueOf(x - 1));
            }
            else if (e == 3){
                shiftOrKey.setText(String.valueOf(x + 1));
            }
        }
        shiftOrKey.getDocument().addDocumentListener(this);
    }
    private boolean validateShiftOrKey(){
        switch (cipher.getSelectedIndex()) {
            case 0:
                if (caesarShift.getText().isEmpty() || isNotNumeric(caesarShift.getText())) {
                    caesarShift.setBorder(new LineBorder(Color.red,1));
                    error.setText("Shift must not be blank and must be numeric");
                    return false;
                }
                caesarShift.setBorder(new JTextField().getBorder());
                break;
            case 1:
                if (!isAlpha(vignereKey.getText())) {
                    vignereKey.setBorder(new LineBorder(Color.red,1));
                    error.setText("Key must not be blank and must contain letters only");
                    return false;
                }
                vignereKey.setBorder(new JTextField().getBorder());
                break;
            case 3:
                boolean key1 = true;
                boolean key2 = true;
                if (isNotNumeric(affineKey_1.getText())) {
                    affineKey_1.setBorder(new LineBorder(Color.red,1));
                    error.setText("Shift must not be blank and must be numeric");
                    key1 = false;
                }
                else if (Integer.parseInt(affineKey_1.getText()) <= 0){
                    affineKey_1.setBorder(new LineBorder(Color.red,1));
                    error.setText("The value must be greater than 0");
                    key1 = false;
                }
                else {
                    int x = Integer.parseInt(affineKey_1.getText());
                    for (int i = 0; i < affineKey_1.getDocument().getLength(); i++) {
                        int[] factors = {2, 13, 26};
                        for (Integer j : factors) {
                            if (x % j == 0) {
                                affineKey_1.setBorder(new LineBorder(Color.red, 1));
                                error.setText("The value must be coprime to the size of the alphabet(26)");
                                return false;
                            }
                        }
                    }
                }
                if(key1)
                    affineKey_1.setBorder(new JTextField().getBorder());
                if (isNotNumeric(affineKey_2.getText())) {
                    affineKey_2.setBorder(new LineBorder(Color.red,1));
                    error.setText("Shift must not be blank and must be numeric");
                    key2 = false;
                }
                else if (Integer.parseInt(affineKey_2.getText()) <= 0) {
                    affineKey_2.setBorder(new LineBorder(Color.red,1));
                    error.setText("The value must be greater than 0");
                    return false;
                }
                if (!(key1 && key2))
                    return false;
                affineKey_2.setBorder(new JTextField().getBorder());
                break;
        }
        error.setText("");
        return true;
    }

    private boolean isNotNumeric(String string){
        if (string.isEmpty())
                return true;
        for (int i = 0; i < string.length(); i++){
            if(i == 0 && string.charAt(i) == '-')
                if(string.length() == 1)
                    return true;
                else continue;
            if(Character.digit(string.charAt(i), 10) < 0) return true;
        }
        return false;
    }

    private boolean isAlpha(String string){
        if (string.isEmpty())
            return false;
        for (int i = 0; i < string.length(); i++){
            char x = string.charAt(i);
            if(!((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')))
                return false;
        }
        return true;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        cipherDecipher();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        cipherDecipher();

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        cipherDecipher();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == cipher) {
                CardLayout cl = (CardLayout)(optionCards.getLayout());
                cl.show(optionCards, (String)e.getItem());

                cipherDecipher();
            } else if (e.getSource() == encodeDecode) {
                
                inField.getDocument().removeDocumentListener(this);
                inField.setText(outField.getText());
                
                if (encodeDecode.getSelectedIndex() == 0) {
                    plainText.setText("PLAINTEXT");
                    cipherText.setText("CIPHERTEXT");
                }
                else if (encodeDecode.getSelectedIndex() == 1) {
                    plainText.setText("CIPHERTEXT");
                    cipherText.setText("PLAINTEXT");
                }
                cipherDecipher();
                inField.getDocument().addDocumentListener(this);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
            if (e.getSource() == caesarSub)
                caesarSub.setIcon(new ImageIcon(getClass().getResource("/images/subHover.JPG")));
            else if(e.getSource() == caesarAdd)
                caesarAdd.setIcon(new ImageIcon(getClass().getResource("/images/addHover.JPG")));
            else if(e.getSource() == affineSub1)
                affineSub1.setIcon(new ImageIcon(getClass().getResource("/images/subHover.JPG")));
            else if(e.getSource() == affineAdd1)
                affineAdd1.setIcon(new ImageIcon(getClass().getResource("/images/addHover.JPG")));
            else if(e.getSource() == affineSub2)
                affineSub2.setIcon(new ImageIcon(getClass().getResource("/images/subHover.JPG")));
            else if(e.getSource() == affineAdd2)
                affineAdd2.setIcon(new ImageIcon(getClass().getResource("/images/addHover.JPG")));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == caesarSub)
            caesarSub.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));
        else if(e.getSource() == caesarAdd)
            caesarAdd.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
        else if(e.getSource() == affineSub1)
            affineSub1.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));
        else if(e.getSource() == affineAdd1)
            affineAdd1.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
        else if(e.getSource() == affineSub2)
            affineSub2.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));
        else if(e.getSource() == affineAdd2)
            affineAdd2.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
    }

    public static void main(String[] args) {

        new Main();

    }
}