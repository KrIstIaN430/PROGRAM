package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main extends MouseAdapter implements DocumentListener, ActionListener, ItemListener {
    private JTextArea inField = new JTextArea();
    private JTextArea outField = new JTextArea();
    private JTextField shiftOrKey_1 = new JTextField("1");
    private JTextField shiftOrKey_2 = new JTextField("1");
    private JLabel shiftOrkey1Label = new JLabel("Shift");
    private JLabel shiftOrkey2Label = new JLabel("Key 2");
    private String letters;
    private JLabel error = new JLabel();
    private JButton add1 = new JButton();
    private JButton add2 = new JButton();
    private JButton sub1 = new JButton();
    private JButton sub2 = new JButton();
    private JComboBox<String> cipher;
    private JComboBox<String> encodeDecode;
    private JPanel optionPanel2 = new JPanel();
    private Component rigid = Box.createRigidArea(new Dimension(110,0));
        private Main(){
            JFrame Main = new JFrame("Text Encryptor");
            Main.setLayout( new GridBagLayout());

            String[] ciphers = {"Caesar Cipher", "Viegnere Cipher", "Atbash Cipher", "Affine Cipher"};
            String[] options = {"Encode", "Decode"};

            letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            cipher = new JComboBox<>(ciphers);
            encodeDecode = new JComboBox<>(options);


            encodeDecode.addItemListener(this);
            cipher.addItemListener(this);
            inField.getDocument().addDocumentListener(this);
            add1.addActionListener(this);
            sub1.addActionListener(this);
            add2.addActionListener(this);
            sub2.addActionListener(this);
            add1.addMouseListener(this);
            sub1.addMouseListener(this);
            add2.addMouseListener(this);
            sub2.addMouseListener(this);

            shiftOrKey_1.getDocument().addDocumentListener(this);
            shiftOrKey_2.getDocument().addDocumentListener(this);
            add1.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
            sub1.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));
            add2.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
            sub2.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));


            inField.setLineWrap(true);
            outField.setLineWrap(true);
            JScrollPane inScroll = new JScrollPane(inField);
            JScrollPane outScroll = new JScrollPane(outField);

            add1.setPreferredSize(new Dimension(40, 26));
            sub1.setPreferredSize(new Dimension(40, 26));
            add2.setPreferredSize(new Dimension(40, 26));
            sub2.setPreferredSize(new Dimension(40, 26));
            shiftOrKey_1.setPreferredSize(new Dimension(40, 26));
            shiftOrKey_2.setPreferredSize(new Dimension(40, 26));
            shiftOrKey_2.setVisible(false);
            Font font = new Font("Cambria", Font.PLAIN, 15);

            shiftOrKey_1.setFont(font);
            inField.setFont(font);
            outField.setFont(font);
            cipher.setFont(font);
            cipher.setForeground(Color.BLACK);
            encodeDecode.setFont(font);
            encodeDecode.setForeground(Color.BLACK);

            add2.setVisible(false);
            sub2.setVisible(false);
            shiftOrKey_1.setHorizontalAlignment(JTextField.CENTER);
            shiftOrKey_2.setHorizontalAlignment(JTextField.CENTER);
            error.setText("ERROR TEST");


            JPanel topPanel = new JPanel();
            topPanel.setLayout(new GridBagLayout());
            JPanel optionPanel1 = new JPanel();
            JPanel optionPanel2Cont = new JPanel();
            optionPanel2Cont.setLayout(new BoxLayout(optionPanel2Cont, BoxLayout.PAGE_AXIS));
            optionPanel1.setLayout(new FlowLayout());
            optionPanel1.add(cipher);
            optionPanel1.add(encodeDecode);

            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));

            labelPanel.add(shiftOrkey1Label);
            labelPanel.add(rigid);
            labelPanel.add(shiftOrkey2Label);
            rigid.setVisible(false);
            shiftOrkey2Label.setVisible(false);

            optionPanel2.add(sub1);
            optionPanel2.add(shiftOrKey_1);
            optionPanel2.add(add1);
            optionPanel2.add(sub2);
            optionPanel2.add(shiftOrKey_2);
            optionPanel2.add(add2);
            optionPanel2Cont.add(labelPanel);
            optionPanel2Cont.add(optionPanel2);

            JPanel textPanel = new JPanel();
            textPanel.add(error);

            JLabel plainText = new JLabel("PLAINTEXT");
            JLabel cipherText = new JLabel("CIPHERTEXT");
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
            topPanel.add(optionPanel2Cont, c);

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
            optionPanel2.setBackground(Color.decode("#77D9FF"));
            labelPanel.setBackground(Color.decode("#77D9FF"));
            optionPanel2Cont.setBackground(Color.decode("#77D9FF"));
            textPanel.setBackground(Color.decode("#77D9FF"));
            plainPanel.setBackground(Color.decode("#77D9FF"));
            cipherPanel.setBackground(Color.decode("#77D9FF"));
            Main.getContentPane().setBackground(Color.decode("#77D9FF"));
            add1.setBackground(Color.decode("#D1F2FF"));
            sub1.setBackground(Color.decode("#D1F2FF"));
            add2.setBackground(Color.decode("#D1F2FF"));
            sub2.setBackground(Color.decode("#D1F2FF"));


            Main.setResizable(true);
            Main.setSize(370, 450);
            Main.setMinimumSize(new Dimension(300, 300));
            Main.setVisible(true);
            Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void cipherDecipher(){
        if (validateShiftOrKey()) {
            error.setText("");
            String text = "";
            switch (cipher.getSelectedIndex()) {
                case 0:
                    text = caesarCipher(encodeDecode.getSelectedIndex());
                    break;
                case 1:
                    text = vigenereCipher(encodeDecode.getSelectedIndex());
                    break;
                case 2:
                    text = atbashCipher(encodeDecode.getSelectedIndex());
                    break;
                case 3:
                    text = affineCipher(encodeDecode.getSelectedIndex());
                    break;
            }
            if (encodeDecode.getSelectedIndex() == 0)
                outField.setText(text);
            else
                inField.setText(text);
        }
    }
    private String caesarCipher(int encodeDecode){
        StringBuilder text;
        String[] textField = new String[2];
        textField[0] = inField.getText();
        textField[1] = outField.getText();
        int cShift = Integer.parseInt(shiftOrKey_1.getText());
        text = new StringBuilder(textField[encodeDecode]);
        for (int i = 0; i < textField[encodeDecode].length(); i++) {
            int val = text.charAt(i);
            if(isAlpha(Character.toString(text.charAt(i)))) {
                if (encodeDecode == 0)
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
    private String vigenereCipher(int encodeDecode){
        StringBuilder text = new StringBuilder();
        int x = 0;
        String[] textField = new String[2];
        textField[0] = inField.getText();
        textField[1] = outField.getText();
        for(int i = 0; i < textField[encodeDecode].length(); i++){
            if(isAlpha(Character.toString(textField[encodeDecode].charAt(i)))) {
                text.append(shiftOrKey_1.getText().charAt(x));
                x++;
                if (x == shiftOrKey_1.getText().length())
                    x = 0;
            }
            else
                text.append(textField[encodeDecode].charAt(i));
        }
        for(int i = 0; i < textField[encodeDecode].length(); i++) {
            int index, index2;
            index = letters.indexOf(text.charAt(i));
            if (index == -1)
                continue;
            else if(index >= 26)
                index -= 26;
            index2 = letters.indexOf(textField[encodeDecode].charAt(i));
            if (index2 != -1){
                if (encodeDecode == 0) {
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
    private String atbashCipher(int encodeDecode){
        StringBuilder text = new StringBuilder();
        StringBuilder text2 = new StringBuilder(letters);
        text2.reverse();
        String[] textField = new String[2];
        textField[0] = inField.getText();
        textField[1] = outField.getText();

        for(int i = 0;i < textField[encodeDecode].length(); i++) {
            int index;
            if (isAlpha(Character.toString(textField[encodeDecode].charAt(i)))) {
                if (Character.isUpperCase(textField[encodeDecode].charAt(i))) {
                    index = letters.indexOf(textField[encodeDecode].charAt(i)) - 26;
                    text.append(text2.charAt(index));
                } else {
                    index = letters.indexOf(textField[encodeDecode].charAt(i)) + 26;
                    text.append(text2.charAt(index));
                }
            }
            else
                text.append(textField[encodeDecode].charAt(i));
        }
        return text.toString();
    }
    private String affineCipher(int encodeDecode){
        //ENCRYPT (Ax+B) % 26
        //DECRPYT c(x - B) mod 26
        // c =  (X * KEY)% 26 == 1
        StringBuilder text = new StringBuilder();
        String[] textField = new String[2];
        textField[0] = inField.getText();
        textField[1] = outField.getText();

        if (encodeDecode == 1){
            int inverse = 0;
            for(int i = 0; i < 26; i++){
                inverse = (i * Integer.parseInt(shiftOrKey_1.getText())) % 26;
                if (inverse == 1) {
                    inverse = i;
                    break;
                }
            }
            for(int i = 0; i < textField[encodeDecode].length(); i++) {
                int index = letters.indexOf(textField[encodeDecode].charAt(i));
                int val = 0;
                if (index == -1) {
                    text.append(textField[encodeDecode].charAt(i));
                    continue;
                }
                if(index <= 25){
                    val = (inverse * (index - Integer.parseInt(shiftOrKey_2.getText()))) % 26;
                    if (val < 0)
                        val += 26;
                }
                else if (index <= 51){
                    index -= 26;
                    val = (inverse * (index - Integer.parseInt(shiftOrKey_2.getText()))) % 26;
                    if (val < 0)
                        val += 26;
                    val += 26;
                }
                text.append(letters.charAt(val));
            }
            return text.toString();
        }
        for(int i = 0;i < textField[encodeDecode].length(); i++) {
            int index = letters.indexOf(textField[encodeDecode].charAt(i));
            if (index == -1) {
                text.append(textField[encodeDecode].charAt(i));
                continue;
            }
            int val = 0;
            if(index <= 25){
                val = Integer.parseInt(shiftOrKey_1.getText()) * index +
                        Integer.parseInt(shiftOrKey_2.getText()) % 26;
                while(val >= 26)
                    val -= 26;
            }else if (index <= 51){
                index -= 26;
                val = Integer.parseInt(shiftOrKey_1.getText()) * index +
                        Integer.parseInt(shiftOrKey_2.getText()) % 26;
                while(val >= 26)
                    val -= 26;
                val+= 26;
            }
            text.append(letters.charAt(val));
        }
        return text.toString();
    }
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == add1)
            addSub(0, shiftOrKey_1);
        else if(e.getSource() == sub1)
            addSub(1, shiftOrKey_1);
        else if(e.getSource() == add2)
            addSub(2, shiftOrKey_2);
        else if(e.getSource() == sub2)
            addSub(3, shiftOrKey_2);
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
        if (e == 0)
            shiftOrKey.setText(String.valueOf(x + 1));
        else if(e == 1)
            shiftOrKey.setText(String.valueOf(x - 1));
    }
    else if(cipher.getSelectedIndex() == 3){
        int[] factors  = {2, 13, 26};
        if (e == 0){
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
        else if (e == 1){
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
        else if (e == 2){
            shiftOrKey.setText(String.valueOf(x + 1));
        }
        else if (e == 3){
            if (x > 1)
               shiftOrKey.setText(String.valueOf(x - 1));
        }
    }
    shiftOrKey.getDocument().addDocumentListener(this);
    }
    private boolean validateShiftOrKey(){
        switch (cipher.getSelectedIndex()) {
            case 0:
                if (shiftOrKey_1.getText().isEmpty() || !isNumeric(shiftOrKey_1.getText())) {
                    error.setText("Shift must not be blank and must be numeric");
                    return false;
                }
                break;
            case 1:
                if (!isAlpha(shiftOrKey_1.getText())) {
                    error.setText("Key must not be blank and must contain letters only");
                    return false;
                }
                break;
            case 3:
                if (!(isNumeric(shiftOrKey_1.getText()) && isNumeric(shiftOrKey_1.getText()))) {
                    error.setText("Shift must not be blank and must be numeric");
                    return false;
                }
                int x = Integer.parseInt(shiftOrKey_1.getText());
                if (x <= 0 || Integer.parseInt(shiftOrKey_2.getText()) <= 0) {
                    error.setText("The value must be greater than 0");
                    return false;
                }
                for (int i = 0; i < shiftOrKey_1.getDocument().getLength(); i++) {
                    int[] factors = {2, 13, 26};
                    for (Integer j : factors) {
                        if (x % j == 0) {
                            error.setText("The value must be coprime to the size of the alphabet(26)");
                            return false;
                        }
                    }
                }
                break;
            }
        return true;
    }

    private boolean isNumeric(String string){
        if (string.isEmpty())
                return false;
        for (int i = 0; i < string.length(); i++){
            if(i == 0 && string.charAt(i) == '-')
                if(string.length() == 1)
                    return false;
                else continue;
            if(Character.digit(string.charAt(i), 10) < 0) return false;
        }
        return true;
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
        System.out.println("insert");
        cipherDecipher();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("remove");
        cipherDecipher();

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("changed");
        cipherDecipher();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == cipher) {
                shiftOrKey_1.getDocument().removeDocumentListener(this);
                shiftOrKey_2.getDocument().removeDocumentListener(this);
                switch (cipher.getSelectedIndex()) {
                    case 0:
                        shiftOrKey_1.setText("1");
                        shiftOrkey1Label.setText("Shift");
                        shiftOrKey_1.setPreferredSize(new Dimension(40, 26));
                        shiftOrKey_1.setVisible(true);
                        shiftOrKey_2.setVisible(false);
                        shiftOrkey1Label.setVisible(true);
                        rigid.setVisible(false);
                        shiftOrkey2Label.setVisible(false);
                        add1.setVisible(true);
                        sub1.setVisible(true);
                        add2.setVisible(false);
                        sub2.setVisible(false);
                        break;
                    case 1:
                        shiftOrKey_1.setText("Encrypt");
                        shiftOrkey1Label.setText("Key");
                        shiftOrKey_1.setPreferredSize(new Dimension(100, 26));
                        shiftOrKey_1.setVisible(true);
                        shiftOrKey_2.setVisible(false);
                        shiftOrkey1Label.setVisible(true);
                        rigid.setVisible(false);
                        shiftOrkey2Label.setVisible(false);
                        add1.setVisible(false);
                        sub1.setVisible(false);
                        add2.setVisible(false);
                        sub2.setVisible(false);
                        optionPanel2.revalidate();
                        break;
                    case 2:
                        shiftOrKey_1.setVisible(false);
                        shiftOrKey_2.setVisible(false);
                        shiftOrkey1Label.setVisible(false);
                        rigid.setVisible(false);
                        shiftOrkey2Label.setVisible(false);
                        add1.setVisible(false);
                        sub1.setVisible(false);
                        add2.setVisible(false);
                        sub2.setVisible(false);
                        break;
                    case 3:
                        shiftOrKey_1.setText("1");
                        shiftOrKey_2.setText("1");
                        shiftOrkey1Label.setText("Key 1");
                        shiftOrKey_1.setPreferredSize(new Dimension(40, 26));
                        shiftOrKey_1.setVisible(true);
                        shiftOrKey_2.setVisible(true);
                        shiftOrkey1Label.setVisible(true);
                        rigid.setVisible(true);
                        shiftOrkey2Label.setVisible(true);
                        add1.setVisible(true);
                        sub1.setVisible(true);
                        add2.setVisible(true);
                        sub2.setVisible(true);
                        break;
                }
                shiftOrKey_1.getDocument().addDocumentListener(this);
                shiftOrKey_2.getDocument().addDocumentListener(this);
                cipherDecipher();
            } else if (e.getSource() == encodeDecode) {
                if (encodeDecode.getSelectedIndex() == 0) {
                    outField.getDocument().removeDocumentListener(this);
                    inField.getDocument().addDocumentListener(this);
                }
                else if (encodeDecode.getSelectedIndex() == 1) {
                    inField.getDocument().removeDocumentListener(this);
                    outField.getDocument().addDocumentListener(this);
                }
                cipherDecipher();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

            if (e.getSource() == add1)
                add1.setIcon(new ImageIcon(getClass().getResource("/images/addHover.JPG")));
            else if(e.getSource() == add2)
                add2.setIcon(new ImageIcon(getClass().getResource("/images/addHover.JPG")));
            else if(e.getSource() == sub1)
                sub1.setIcon(new ImageIcon(getClass().getResource("/images/subHover.JPG")));
            else if(e.getSource() == sub2)
                sub2.setIcon(new ImageIcon(getClass().getResource("/images/subHover.JPG")));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == add1)
            add1.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
        else if(e.getSource() == add2)
            add2.setIcon(new ImageIcon(getClass().getResource("/images/add.JPG")));
        else if(e.getSource() == sub1)
            sub1.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));
        else if(e.getSource() == sub2)
            sub2.setIcon(new ImageIcon(getClass().getResource("/images/sub.JPG")));

    }

    public static void main(String[] args) {

        new Main();

    }
}