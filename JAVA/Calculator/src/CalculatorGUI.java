import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;

class CalculatorGUI extends MouseAdapter implements ActionListener {
    private JPanel calculatorCards;
    private JButton standardPanButton = new JButton();
    private JButton tempButton = new JButton();
    private JLabel inputOutput = new JLabel("0"); // TODO CHANGE TO TEXTFIELD
    private JLabel equation = new JLabel(" "); //TODO REMOVE " " AFTER CHANGING SCREENPANEL LAYOUT
    private JLabel tempOutput = new JLabel(" ");
    private Calculator calc = new Calculator();
    private JFrame historyWindow = new JFrame("History");
    private JButton historyButton = new JButton();
    private JPanel historyButtonsPanel = new JPanel();
    private boolean overwrite = true;
    private boolean opFlag = false;
    private boolean dotFlag = false;
    private boolean disabled = false;
    private String prevOp = "";

    private CalculatorGUI() throws IOException {
        JFrame frame = new JFrame("Calculator");
        frame.setLayout(new GridBagLayout());
        JScrollPane historyPane = new JScrollPane(historyButtonsPanel);
        JPanel calculatorButtons = new JPanel();
        JPanel standardPanel = new JPanel(new BorderLayout());
        JPanel standardTop = new JPanel();
        JPanel titleHistory = new JPanel(new BorderLayout());
        JPanel historyPanel = new JPanel(new BorderLayout());
        JPanel screenPanel = new JPanel(new GridBagLayout());
        JPanel standCalcButtons = new JPanel(new GridLayout(5, 4));
        String[] standardButtonName = {"CLR Hist", "C", "DEL", "/", "7", "8", "9", "*", "4", "5", "6", "-",
                "1", "2", "3", "+", "+/-", "0", ".", "="};
        JButton[] standardButtons = new JButton[20];
        //TODO ARRANGE DECLARATIONS
        standardPanButton.setActionCommand("STANDARD BUTTON");
        standardPanButton.addActionListener(this);
        tempButton.addActionListener(this);

        calculatorButtons.setLayout(new BoxLayout(calculatorButtons, BoxLayout.PAGE_AXIS));

        standardPanButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        standardPanButton.setBorder(null);
        standardPanButton.addMouseListener(this);
        standardPanButton.setIcon(new ImageIcon(getClass().getResource("/images/standard.PNG")));
        standardPanButton.setPreferredSize(new Dimension(70, 70));

        tempButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tempButton.setBorder(null);
        tempButton.addMouseListener(this);
        tempButton.setIcon(new ImageIcon(getClass().getResource("/images/temp.PNG")));
        tempButton.setPreferredSize(new Dimension(70, 70));

        historyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        historyButton.addActionListener(this);
        historyButton.addMouseListener(this);
        historyButton.setBorder(null);
        historyButton.setIcon(new ImageIcon(getClass().getResource("/images/history.PNG")));

        standardPanel.setName("Standard");
        standardTop.setLayout(new BoxLayout(standardTop, BoxLayout.PAGE_AXIS));
        equation.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        inputOutput.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        tempOutput.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        screenPanel.setBackground(Color.decode("#2D2D2D"));
        screenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        standCalcButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        standCalcButtons.setBackground(Color.decode("#2D2D2D"));

        calculatorButtons.add(standardPanButton);
        calculatorButtons.add(tempButton);
        //-----------STANDARD CALCULATOR PANEL
        GridBagConstraints c = new GridBagConstraints();

        BufferedImage myPicture = ImageIO.read(getClass().getResource("/images/standardLogo.PNG"));
        JLabel standardLabel = new JLabel(new ImageIcon(myPicture));
        myPicture = ImageIO.read(getClass().getResource("/images/historyLabel.PNG"));
        JLabel historyLabel = new JLabel(new ImageIcon(myPicture));
        //add(picLabel);

        //TODO MAKE HISTORYLABEL ANIMATION
        //historyPanel.add(historyLabel, BorderLayout.LINE_START);
        historyPanel.add(historyButton, BorderLayout.LINE_END);
        historyPanel.setPreferredSize(new Dimension(40, 40));

        historyButtonsPanel.setLayout(new BoxLayout(historyButtonsPanel, BoxLayout.PAGE_AXIS));
        //standardTop.setMaximumSize(new Dimension(360, 200));


        JPanel standardLabelPanel = new JPanel();
        standardLabelPanel.add(standardLabel);
        standardLabelPanel.setMaximumSize(new Dimension(260, 40));
        //standardLabelPanel.setIcon(new ImageIcon(getClass().getResource("/images/standardLogo.PNG")));
        //standardLabel.setFont(new Font("Cambria", Font.PLAIN, 25));
        //standardLabel.setForeground(Color.WHITE);
        //standardLabel.setHorizontalAlignment(JLabel.CENTER);
        JButton placeholder1 = new JButton("PLACEHOLDER1"); //TODO DELETE
        JButton placeholder2 = new JButton("PLACEHOLDER2"); //TODO DELETE
        titleHistory.setBackground(Color.decode("#00ADEF"));
        titleHistory.add(standardLabel);
        titleHistory.add(historyPanel, BorderLayout.LINE_END);
        standardTop.add(titleHistory);
        standardTop.add(screenPanel);
        standardPanel.add(standardTop, BorderLayout.PAGE_START);
        standardPanel.add(standCalcButtons);
        //TODO CHANGE SCREENPANEL LAYOUT TO BORDERLAYOUT



        equation.setHorizontalAlignment(SwingConstants.RIGHT);
        equation.setOpaque(true);
        equation.setBackground(Color.decode("#EEEEEE"));
        inputOutput.setHorizontalAlignment(SwingConstants.RIGHT);
        inputOutput.setOpaque(true);
        inputOutput.setBackground(Color.decode("#EEEEEE"));
        tempOutput.setHorizontalAlignment(SwingConstants.RIGHT);
        tempOutput.setOpaque(true);
        tempOutput.setBackground(Color.decode("#EEEEEE"));
        JPanel tempPanel2 = new JPanel();
        tempPanel2.add(placeholder1);
        inputOutput.setFont(new Font("Cambria", Font.PLAIN,40));
        inputOutput.setMinimumSize(new Dimension(28, 45));
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 0;
        screenPanel.add(equation, c);
        c.weighty = 1;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        screenPanel.add(inputOutput, c);
        c.weighty = .25;
        c.weightx = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 2;
        screenPanel.add(tempOutput, c);

        /*
        c = new GridBagConstraints();
        //standardLabel.setBackground(Color.decode("#444444"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 1;
        standardPanel.add(standardLabelPanel, c);
        c.fill = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 0;
        c.weighty = .5;
        standardPanel.add(historyButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 0, 10);
        c.ipady = 20;
        c.weightx = 0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0;
        standardPanel.add(screenPanel, c);

        JPanel standCalcButtons = new JPanel(new GridLayout(5, 4));


        for (int i = 0; i < 20; i++) {
            standardButtons[i] = new JButton(standardPlaceholder[i]);
            standardButtons[i].setName(standardPlaceholder[i]);
            standardButtons[i].addActionListener( this);
            standCalcButtons.add(standardButtons[i]);
        }


        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 2;
        standardPanel.add(standCalcButtons, c);


         */



        //TODO MOVE SOMEWHERE
        for (int i = 0; i < 20; i++) {
            standardButtons[i] = new JButton(standardButtonName[i]);
            standardButtons[i].setName(standardButtonName[i]);
            standardButtons[i].addActionListener( this);
            standCalcButtons.add(standardButtons[i]);
        }

        JPanel tempPanel = new JPanel();
        tempPanel.setName("Temperature");

        calculatorCards = new JPanel(new CardLayout());
        calculatorCards.add(standardPanel, "Standard");
        calculatorCards.add(tempPanel, "Temperature");

        c = new GridBagConstraints();
        //--------------------MAIN FRAME
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0;
        c.weightx = 0;
        c.ipady = 0;
        c.gridx = 0;

        frame.add(calculatorButtons, c);
        c.gridwidth = 2;
        c.weighty = 1;
        c.weightx = 1;
        c.gridx = 1;

        frame.add(calculatorCards, c);

        calculatorButtons.setBackground(Color.decode("#222222"));
        standardPanel.setBackground(Color.decode("#2D2D2D"));


        historyWindow.add(historyPane);
        historyWindow.setMinimumSize(new Dimension(400, 500));
        historyWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setMinimumSize(new Dimension(400, 500));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == standardPanButton)
            standardPanButton.setIcon(new ImageIcon(getClass().getResource("/images/standardHover2.PNG")));
        else if (e.getSource() == tempButton)
            tempButton.setIcon(new ImageIcon(getClass().getResource("/images/tempHover2.PNG")));
        else if (e.getSource() == historyButton)
            historyButton.setIcon(new ImageIcon(getClass().getResource("/images/historyHover.PNG")));
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == standardPanButton)
            standardPanButton.setIcon(new ImageIcon(getClass().getResource("/images/standard.PNG")));
        else if (e.getSource() == tempButton)
            tempButton.setIcon(new ImageIcon(getClass().getResource("/images/temp.PNG")));
        else if (e.getSource() == historyButton)
            historyButton.setIcon(new ImageIcon(getClass().getResource("/images/history.PNG")));
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == standardPanButton)
            standardPanButton.setIcon(new ImageIcon(getClass().getResource("/images/standardPressed.PNG")));
        else if (e.getSource() == tempButton)
            tempButton.setIcon(new ImageIcon(getClass().getResource("/images/tempPressed.PNG")));
        else if (e.getSource() == historyButton)
            historyButton.setIcon(new ImageIcon(getClass().getResource("/images/historyPressed.PNG")));
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == standardPanButton)
            standardPanButton.setIcon(new ImageIcon(getClass().getResource("/images/standard.PNG")));
        else if (e.getSource() == tempButton)
            tempButton.setIcon(new ImageIcon(getClass().getResource("/images/temp.PNG")));
        else if (e.getSource() == historyButton)
            historyButton.setIcon(new ImageIcon(getClass().getResource("/images/history.PNG")));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("STANDARD BUTTON")) {
            CardLayout c1 = (CardLayout) calculatorCards.getLayout();
            c1.show(calculatorCards, "Standard");
        } else if (e.getSource() == tempButton) {
            CardLayout c1 = (CardLayout) calculatorCards.getLayout();
            c1.show(calculatorCards, "Temperature");
        }
        else if(e.getSource() == historyButton){
            if (!historyWindow.isVisible())
                historyWindow.setVisible(true);
            else
                historyWindow.setVisible(false);
        }
        else{
            Component visible = new JPanel();
            for (Component comp : calculatorCards.getComponents() ) {
                if (comp.isVisible())
                    visible = comp;
            }
            if (visible.getName().equals("Standard")) {
                if(disabled) {
                    inputOutput.setText("0");
                    disabled = false;
                }
                screenUpdate(((JComponent) e.getSource()).getName());
            }
        }
    }
    private void screenUpdate(String name){
        StringBuilder input = new StringBuilder(inputOutput.getText());
        StringBuilder toComp = new StringBuilder(equation.getText());
        if (Character.digit(name.charAt(0), 10) >= 0) {
            if ((input.length() == 1 && input.toString().equals("0")) || overwrite) {
                input.setLength(0);
                overwrite = false;
                dotFlag = false;
            }
            input.append(name);
            if (input.length() > 14)
                input.setLength(input.length() - 1);
            inputOutput.setText(input.toString());
            if (prevOp.equals("/") && inputOutput.getText().equals("0")) {
                tempOutput.setText("Cannot divide by 0");
                disabled = true;
                return;
            }
            opFlag = false;
        } else {
            switch (name) {
                case "*":
                case "/":
                case "+":
                case "-":
                    if (equation.getText().length() > 2 && opFlag) {
                        toComp.setLength(toComp.length() - 3);
                        switch (name) {
                            case "*":
                                toComp.append(" * ");
                                break;
                            case "/":
                                toComp.append(" / ");
                                break;
                            case "+":
                                toComp.append(" + ");
                                break;
                            default:
                                toComp.append(" - ");
                                break;
                        }
                    } else if(input.length() != 1 || Integer.parseInt(input.toString()) != 0) {
                        toComp.append(input.toString()).append(" ").append(name).append(" ");
                    }
                    else
                        return;
                    equation.setText(toComp.toString());
                    opFlag = true;
                    overwrite = true;
                    prevOp = name;
                    break;
                case ".":
                    if (!dotFlag) {
                        input.append(".");
                        inputOutput.setText(input.toString());
                    }
                    dotFlag = true;
                    overwrite = false;
                    return;
                case "=":
                    toComp = new StringBuilder(equation.getText() + input);
                    if(prevOp.equals("/") && inputOutput.getText().equals("0")) {
                        inputOutput.setText("Cannot divide by 0");
                        disabled = true;
                    }
                    else if(!tempOutput.getText().equals(" ")) //TODO FIX ZERO TEMPOUTPUT BUG BY REPLACING SCREEN LAYOUT
                        inputOutput.setText(tempOutput.getText());

                    //TODO replace with method call
                    calc.equations.addFirst(toComp.toString() + " =");
                    calc.results.addFirst(inputOutput.getText());
                    if(calc.results.size() > 20) {
                        calc.equations.removeLast();
                        calc.results.removeLast();
                    }
                    updateHistory();

                    equation.setText(" ");
                    tempOutput.setText(" ");
                    overwrite = true;
                    prevOp = "";
                    return;
                case "C":
                    input.setLength(0);
                    input.append(0);
                    toComp.setLength(0);
                    equation.setText(" ");
                    dotFlag = false;
                    overwrite = true;
                    prevOp = "";
                    break;
                case "DEL":
                    if(!overwrite) {
                        if(input.charAt(input.length() - 1) == '.')
                            dotFlag = false;
                        input.setLength(input.length() - 1);
                        if (input.length() == 0)
                            input.append(0);
                        inputOutput.setText(input.toString());
                    }
                    break;
                case "+/-":
                    if (inputOutput.getText().equals("0"))
                        return;
                    String negated = calc.negate(input);
                    input.setLength(0);
                    input.append(negated);
                    inputOutput.setText(negated);
                    if(toComp.length() == 1)
                        return;
                    break;
                case "CLR Hist":
                    calc.equations.clear();
                    calc.results.clear();
                    updateHistory();
                    break;
            }
        }
        if(toComp.length() != 1) {
            toComp.append(input.toString());
            String outputString = calc.calculate(toComp);
            if (dotFlag)
                inputOutput.setText(input.toString());
            else
                inputOutput.setText(calc.formatter(input.toString()));
            if (!outputString.equals("") && !outputString.equals("0")) //TODO REMOVE AFTER CHANGING LAYOUT
                tempOutput.setText(outputString);
            else
                tempOutput.setText(" ");
        }
    }

    private void updateHistory(){
        historyButtonsPanel.removeAll();
        historyCustomButton[] history = new historyCustomButton[calc.results.size()];
        int ctr = 0;
        while(ctr < calc.results.size()){
            history[ctr] = new historyCustomButton(calc.equations.get(ctr), calc.results.get(ctr));
            historyButtonsPanel.add(history[ctr]);
            history[ctr].addActionListener(this);
            history[ctr].setBorder(null);
            history[ctr].setName(Integer.toString(ctr));
            ctr++;
        }

        /*
        for (int i = 0; i < calc.results.size(); i++) {
            history[i] = new historyCustomButton();
            historyButtonsPanel.add(history[i]);
            history[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }

         */
        historyWindow.revalidate();
    }
    public static void main(String[] args) throws IOException {
        new CalculatorGUI();
    }
}