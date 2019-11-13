import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class CalculatorGUI extends MouseAdapter implements ActionListener {
    private Calculator calc = new Calculator();
    private JPanel calculatorCards;
    private JPanel historyButtonsPanel = new JPanel();
    private JTextField inputOutput = new JTextField("0");
    private JTextField equation = new JTextField();
    private JTextField tempOutput = new JTextField();
    private JDialog historyWindow = new JDialog();
    private JButton standardPanButton = new JButton();
    private JButton tempButton = new JButton();
    private JButton historyButton = new JButton();
    private boolean overwrite = true;
    private boolean opFlag = false;
    private boolean dotFlag = false;
    private boolean disabled = false;
    private boolean fromHistory = false;
    private int offset = 0;

    private CalculatorGUI() throws IOException {
        //-----Frame and Panels-----
        JFrame frame = new JFrame("Calculator");
        historyWindow.setTitle("History");
        JScrollPane historyPane = new JScrollPane(historyButtonsPanel);
        JPanel calculatorButtons = new JPanel();
        JPanel standardPanel = new JPanel(new BorderLayout());
        JPanel tempPanel = new JPanel();
        JPanel standardTop = new JPanel();
        JPanel titleHistory = new JPanel(new BorderLayout());
        JPanel historyPanel = new JPanel(new BorderLayout());
        JPanel screenPanel = new JPanel();
        JPanel standardLabelPanel = new JPanel();
        JPanel standCalcButtons = new JPanel(new GridLayout(5, 4));
        String[] standardButtonName = {"Clear\nHistory", "C", "DEL", "/", "7", "8", "9", "*", "4", "5", "6", "-",
                "1", "2", "3", "+", "+/-", "0", ".", "="};


        //-----SetLayout-----

        frame.setLayout(new GridBagLayout());
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.PAGE_AXIS));
        calculatorButtons.setLayout(new BoxLayout(calculatorButtons, BoxLayout.PAGE_AXIS));
        standardTop.setLayout(new BoxLayout(standardTop, BoxLayout.PAGE_AXIS));
        historyButtonsPanel.setLayout(new BoxLayout(historyButtonsPanel, BoxLayout.PAGE_AXIS));


        //-----Buttons-----

        standardPanButton.setActionCommand("STANDARD BUTTON");
        standardPanButton.addActionListener(this);
        standardPanButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        standardPanButton.setBorder(null);
        standardPanButton.addMouseListener(this);
        standardPanButton.setIcon(new ImageIcon(getClass().getResource("/images/standard.PNG")));
        standardPanButton.setPreferredSize(new Dimension(70, 70));

        tempButton.addActionListener(this);
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


        //The "keypad"
        standardCustomButtons[] standardButtons = new standardCustomButtons[20];
        for (int i = 0; i < 20; i++) {
            standardButtons[i] = new standardCustomButtons(standardButtonName[i]);
            standardButtons[i].setName(standardButtonName[i]);
            standardButtons[i].addActionListener(this);
            standardButtons[i].setBorder(null);
            standCalcButtons.add(standardButtons[i]);
        }

        standardPanel.setName("Standard");
        standardPanel.add(standardTop, BorderLayout.PAGE_START);
        standardPanel.add(standCalcButtons);

        //-----Background Color-----

        standardPanel.setBackground(Color.decode("#2D2D2D"));
        screenPanel.setBackground(Color.decode("#2D2D2D"));
        standCalcButtons.setBackground(Color.decode("#2D2D2D"));
        standardTop.setBackground(Color.decode("#4F4F4F"));
        calculatorButtons.setBackground(Color.decode("#222222"));
        titleHistory.setBackground(Color.decode("#00ADEF"));
        equation.setBackground(Color.decode("#EEEEEE"));
        inputOutput.setBackground(Color.decode("#EEEEEE"));
        tempOutput.setBackground(Color.decode("#EEEEEE"));

        //-----SetBorders-----

        equation.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        inputOutput.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        tempOutput.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        screenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        standCalcButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        historyPanel.add(historyButton, BorderLayout.LINE_END);
        historyPanel.setPreferredSize(new Dimension(40, 40));
        historyPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        standardLabelPanel.add(standardLabel);
        standardLabelPanel.setMaximumSize(new Dimension(260, 40));
        titleHistory.add(standardLabel);
        titleHistory.add(historyPanel, BorderLayout.LINE_END);
        standardTop.add(titleHistory);
        standardTop.add(screenPanel);


        equation.setHorizontalAlignment(SwingConstants.RIGHT);
        equation.setOpaque(true);
        equation.setEditable(false);
        equation.addMouseListener(this);

        inputOutput.setEditable(false);
        inputOutput.setHorizontalAlignment(SwingConstants.RIGHT);
        inputOutput.setOpaque(true);
        inputOutput.addMouseListener(this);
        inputOutput.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                printToInputOutput(inputOutput.getText());
            }
        });

        tempOutput.setHorizontalAlignment(SwingConstants.RIGHT);
        tempOutput.setOpaque(true);
        tempOutput.setEditable(false);
        tempOutput.addMouseListener(this);
        inputOutput.setFont(new Font("Cambria", Font.PLAIN,40));


        JPanel displayTempPanel = new JPanel(new BorderLayout());
        displayTempPanel.add(equation);
        screenPanel.add(displayTempPanel);

        displayTempPanel = new JPanel(new BorderLayout());
        displayTempPanel.add(inputOutput);
        screenPanel.add(displayTempPanel);

        displayTempPanel = new JPanel(new BorderLayout());
        displayTempPanel.add(tempOutput);
        screenPanel.add(displayTempPanel);


        calculatorCards = new JPanel(new CardLayout());
        calculatorCards.add(standardPanel, "Standard");
        calculatorCards.add(tempPanel, "Temperature");


        //--------------------MAIN FRAME
        GridBagConstraints c = new GridBagConstraints();
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
        else{
            ((JTextField)e.getSource()).setCursor(new Cursor(Cursor.TEXT_CURSOR));
        }
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
            if (input.length() > 19)
                input.setLength(input.length() - 1);
            if (dotFlag)
                printToInputOutput(input.toString());
            else
                printToInputOutput(calc.formatter(input.toString()));
            opFlag = false;
            fromHistory = false;
        }else if(name.equals("Clear\nHistory")) {
            calc.clearEqRes();
            updateHistory(true);
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
                    else {
                        calc.addToEquation(false, input.toString());
                        tempOutput.setText(calc.calculate());
                    }
                    opFlag = true;
                    overwrite = true;
                    return;
                case ".":
                    if (!dotFlag) {
                        input.append(".");
                        printToInputOutput(input.toString());
                    }
                    dotFlag = true;
                    overwrite = false;
                    return;
                case "=":
                    if (!calc.getCurrEquation().isEmpty()) {
                        if (isDivisorZero(input))
                            disabled = true;
                        if (!tempOutput.getText().isEmpty())
                            printToInputOutput(tempOutput.getText());
                    }
                    offset = calc.addToHistory(input.toString(), fromHistory, isDivisorZero(input));
                    updateHistory(false);
                    equation.setText("");
                    tempOutput.setText("");
                    overwrite = true;
                    opFlag = false;
                    fromHistory = false;
                    return;
                case "C":
                    calc.clearEquation();
                    calc.clearPrevAnswer();
                    inputOutput.setText("0");
                    tempOutput.setText("");
                    equation.setText("");
                    opFlag = false;
                    dotFlag = false;
                    overwrite = true;
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
                            printToInputOutput(calc.formatter(input.toString()));
                        }
                        else
                            printToInputOutput(input.toString());
                        break;
                    }
                    return;
                case "+/-":
                    if (inputOutput.getText().equals("0"))
                        return;
                    String negated = calc.negate(input, overwrite);
                    input.setLength(0);
                    input.append(negated);
                    printToInputOutput(negated);
                    if(calc.getCurrEquation().length() == 0) {
                        tempOutput.setText("");
                        return;
                    }
                    break;
            }
        }
        if(!isDivisorZero(input)) {
            if (overwrite)
                calc.addToEquation(false, calc.getPrevAnswer());
            else
                calc.addToEquation(false, input.toString());
            tempOutput.setText(calc.calculate());

        }
    }

    private void printToInputOutput(String text){
        inputOutput.setFont(new Font("Cambria", Font.PLAIN,40));
        while(inputOutput.getGraphics().getFontMetrics().stringWidth(text) > inputOutput.getWidth() - 10){
            Font font = inputOutput.getFont();
            font = font.deriveFont((float) (font.getSize() - 1));
            inputOutput.setFont(font);
        }
        inputOutput.setText(text);
    }

    private boolean isDivisorZero(StringBuilder input){
        if(calc.isDivideByZero(input)) {
            calc.clearPrevAnswer();
            tempOutput.setText("Cannot divide by 0");
            return true;
        }
        return false;
    }

    private void updateHistory(boolean clear){
        if (clear)
            historyButtonsPanel.removeAll();
        else {
            int size = calc.getResults().size();
            historyCustomButton history = new historyCustomButton(calc.getEquations().getLast() + " =",
                    calc.getResults().getLast());
            historyButtonsPanel.add(history, 0);
            history.addActionListener(e -> {
                int x = Integer.parseInt(((JComponent) e.getSource()).getName()) - offset;
                opFlag = true;
                overwrite = true;
                fromHistory = true;
                calc.setCurrEquation(calc.getEquations().get(x));
                equation.setText(calc.getEquations().get(x));
                printToInputOutput(calc.getResults().get(x));
            });
            history.setBorder(null);
            history.setName(Integer.toString(size - 1 + offset));
            if (offset > 0)
                historyButtonsPanel.remove(historyButtonsPanel.getComponentCount() - 1);
        }
        historyWindow.repaint();
        historyWindow.revalidate();
    }

    public static void main(String[] args) throws IOException {
        new CalculatorGUI();
    }
}