import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class CalculatorGUI extends MouseAdapter implements ActionListener {
    private JPanel calculatorCards;
    private JButton standardPanButton = new JButton();
    private JButton tempButton = new JButton();
    private JLabel inputOutput = new JLabel("0"); // TODO CHANGE TO TEXTFIELD
    private JLabel equation = new JLabel();
    private JLabel tempOutput = new JLabel();
    private Calculator calc = new Calculator();
    private JFrame historyWindow = new JFrame("History");
    private JButton historyButton = new JButton();
    private JPanel historyButtonsPanel = new JPanel();
    private boolean overwrite = true;
    private boolean opFlag = false;
    private boolean dotFlag = false;
    private boolean disabled = false;
    private boolean fromHistory = false;
    private String prevOp = "";
    JButton testButton = new JButton();
    private CalculatorGUI() throws IOException {
        JFrame frame = new JFrame("Calculator");
        frame.setLayout(new GridBagLayout());
        JScrollPane historyPane = new JScrollPane(historyButtonsPanel);
        JPanel calculatorButtons = new JPanel();
        JPanel standardPanel = new JPanel(new BorderLayout());
        JPanel standardTop = new JPanel();
        JPanel titleHistory = new JPanel(new BorderLayout());
        JPanel historyPanel = new JPanel(new BorderLayout());
        JPanel screenPanel = new JPanel();
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.PAGE_AXIS));
        JPanel standCalcButtons = new JPanel(new GridLayout(5, 4));
        String[] standardButtonName = {"Clear\nHistory", "C", "DEL", "/", "7", "8", "9", "*", "4", "5", "6", "-",
                "1", "2", "3", "+", "+/-", "0", ".", "="};
        standardCustomButtons[] standardButtons= new standardCustomButtons[20];
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
        //TODO improve exception handling
        BufferedImage myPicture = ImageIO.read(getClass().getResource("/images/standardLogo.PNG"));
        JLabel standardLabel = new JLabel(new ImageIcon(myPicture));
        myPicture = ImageIO.read(getClass().getResource("/images/historyLabel.PNG"));
        JLabel historyLabel = new JLabel(new ImageIcon(myPicture));
        //add(picLabel);

        //TODO MAKE HISTORYLABEL ANIMATION
        //historyPanel.add(historyLabel, BorderLayout.LINE_START);
        historyPanel.add(historyButton, BorderLayout.LINE_END);
        historyPanel.setPreferredSize(new Dimension(40, 40));
        historyPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
        JButton placeholder2 = new JButton(); //TODO DELETE
        JButton placeholder3 = new JButton("PLACEHOLDER3"); //TODO DELETE
        titleHistory.setBackground(Color.decode("#00ADEF"));
        titleHistory.add(standardLabel);
        titleHistory.add(historyPanel, BorderLayout.LINE_END);
        standardTop.add(titleHistory);
        standardTop.add(screenPanel);
        standardTop.setBackground(Color.decode("#4F4F4F"));
        standardPanel.add(standardTop, BorderLayout.PAGE_START);
        standardPanel.add(standCalcButtons);



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

        equation.setPreferredSize(new Dimension(13, 16));
        JPanel displayTempPanel = new JPanel(new BorderLayout());
        displayTempPanel.add(equation);
        screenPanel.add(displayTempPanel);

        inputOutput.setPreferredSize(new Dimension(32, 47));
        displayTempPanel = new JPanel(new BorderLayout());
        displayTempPanel.add(inputOutput);
        screenPanel.add(displayTempPanel);

        tempOutput.setPreferredSize(new Dimension(13, 16));
        displayTempPanel = new JPanel(new BorderLayout());
        displayTempPanel.add(tempOutput);
        screenPanel.add(displayTempPanel);



        //TODO MOVE SOMEWHERE
        for (int i = 0; i < 20; i++) {
            standardButtons[i] = new standardCustomButtons(standardButtonName[i]);
            standardButtons[i].setName(standardButtonName[i]);
            standardButtons[i].addActionListener( this);
            standardButtons[i].setBorder(null);
            standCalcButtons.add(standardButtons[i]);
        }


        JPanel tempPanel = new JPanel();
        tempPanel.setName("Temperature");

        calculatorCards = new JPanel(new CardLayout());
        calculatorCards.add(standardPanel, "Standard");
        calculatorCards.add(tempPanel, "Temperature");


        GridBagConstraints c = new GridBagConstraints();
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
        if (Character.digit(name.charAt(0), 10) >= 0) {
            if (fromHistory)
                calc.removeLast();
            if ((input.length() == 1 && input.toString().equals("0")) || overwrite) {
                input.setLength(0);
                overwrite = false;
                dotFlag = false;
            }
            input.append(name);
            if (input.length() > 14)
                input.setLength(input.length() - 1);
            if (dotFlag)
                inputOutput.setText(input.toString());
            else
                inputOutput.setText(calc.formatter(input.toString()));
            opFlag = false;
            fromHistory = false;
        }else if(name.equals("Clear\nHistory")) {
            calc.clearEqRes();
            updateHistory(0, true);
            return;
        } else {
            switch (name) {
                case "*":
                case "/":
                case "+":
                case "-":
                    if(!opFlag) {
                        if (overwrite)
                            calc.addToEquation(false, calc.getPrevAnswer());
                        else
                            calc.addToEquation(false, input.toString());
                    }
                    if (fromHistory) {
                        calc.addToEquation(false, name);
                        fromHistory = false;
                    }
                    else
                        calc.addToEquation(opFlag, name);
                    equation.setText(calc.getCurrEquation());
                    if(isDivisorZero(input))
                        disabled = true;
                    else
                        tempOutput.setText(calc.calculate(input.toString(), overwrite));
                    opFlag = true;
                    overwrite = true;
                    prevOp = name;
                    return;
                case ".":
                    if (!dotFlag) {
                        input.append(".");
                        inputOutput.setText(input.toString());
                    }
                    dotFlag = true;
                    overwrite = false;
                    return;
                case "=":
                    if (!calc.getCurrEquation().isEmpty())
                        if(isDivisorZero(input))
                            disabled = true;
                        else if(!tempOutput.getText().isEmpty())
                            inputOutput.setText(tempOutput.getText());
                    updateHistory(calc.addToHistory(input.toString(), fromHistory), false);
                    equation.setText("");
                    tempOutput.setText("");
                    overwrite = true;
                    prevOp = "";
                    opFlag = false;
                    fromHistory = false;
                    return;
                case "C":
                    calc.clearEquation();
                    inputOutput.setText("0");
                    tempOutput.setText("");
                    equation.setText("");
                    dotFlag = false;
                    overwrite = true;
                    prevOp = "";
                    fromHistory = false;
                    return;
                case "DEL":
                    if(!overwrite) {
                        if(input.charAt(input.length() - 1) == '.')
                            dotFlag = false;
                        input.setLength(input.length() - 1);
                        if (input.length() == 0)
                            input.append(0);
                        if(!dotFlag) {
                            inputOutput.setText(calc.formatter(input.toString()));
                        }
                        else
                            inputOutput.setText(input.toString());
                        if(calc.isZero(input)) {
                            tempOutput.setText("");
                            return;
                        }
                        break;
                    }
                    return;
                case "+/-":
                    if (inputOutput.getText().equals("0"))
                        return;
                    String negated = calc.negate(input, overwrite);
                    input.setLength(0);
                    input.append(negated);
                    inputOutput.setText(negated);
                    if(calc.getCurrEquation().length() == 0) {
                        tempOutput.setText("");
                        return;
                    }
                    break;
            }
        }
        if(!isDivisorZero(input))
            tempOutput.setText(calc.calculate(input.toString(), overwrite));
    }

    private boolean isDivisorZero(StringBuilder input){
        if(calc.isDividebyZero(input)) {
            tempOutput.setText("Cannot divide by 0");
            return true;
        }
        return false;
    }
    private void updateHistory(int offset, boolean clear){
        if (clear)
            historyButtonsPanel.removeAll();
        else {
            int size = calc.getResults().size() - 1;
            historyCustomButton history = new historyCustomButton(calc.getEquations().getLast() + " =", //TODO FIX DOUBLE SPACE
                    calc.getResults().getLast());
            historyButtonsPanel.add(history, 0);
            history.addActionListener(e -> {
                int x = Integer.parseInt(((JComponent) e.getSource()).getName()) - offset;
                opFlag = true;
                overwrite = true;
                fromHistory = true;
                calc.setCurrEquation(calc.getEquations().get(x));
                equation.setText(calc.getEquations().get(x));
                inputOutput.setText(calc.getResults().get(x));
            });
            history.setBorder(null);
            history.setName(Integer.toString(size + offset));

        }
        historyWindow.repaint();
    }
    public static void main(String[] args) throws IOException {
        new CalculatorGUI();
    }
}