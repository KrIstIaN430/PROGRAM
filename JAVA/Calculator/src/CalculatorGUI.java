import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

class CalculatorGUI extends MouseAdapter {
    private JPanel calculatorCards;
    private JButton standardPanButton;
    JButton tempButton;
    JButton currencyButton;
    StandardCalculator standard;
    Temperature temperature;
    Currency currency;

    CalculatorGUI() {

        //-----Frame and Panels-----
        JFrame frame = new JFrame("Calculator");
        JPanel calculatorButtons = new JPanel();
        standardPanButton = new JButton();
        tempButton = new JButton();
        currencyButton = new JButton();

        standard = new StandardCalculator();
        temperature = new Temperature();
        currency = new Currency();

        calculatorCards = new JPanel(new CardLayout());
        calculatorCards.add(standard, "Standard");
        calculatorCards.add(temperature, "Temperature");
        calculatorCards.add(currency, "Currency");


        standardPanButton.setIcon(new ImageIcon(getClass().getResource("/images/standard.PNG")));
        standardPanButton.addActionListener(e -> ((CardLayout) calculatorCards.getLayout())
                                                    .show(calculatorCards, "Standard"));
        standardPanButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        standardPanButton.setBorder(null);
        standardPanButton.addMouseListener(this);
        standardPanButton.setPreferredSize(new Dimension(70, 70));


        tempButton.setIcon(new ImageIcon(getClass().getResource("/images/temp.PNG")));
        tempButton.addActionListener(e -> ((CardLayout) calculatorCards.getLayout())
                .show(calculatorCards, "Temperature"));
        tempButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tempButton.setBorder(null);
        tempButton.addMouseListener(this);
        tempButton.setPreferredSize(new Dimension(70, 70));

        currencyButton.setIcon(new ImageIcon(getClass().getResource("/images/currEx.PNG")));
        currencyButton.addActionListener(e -> {
            ((CardLayout) calculatorCards.getLayout())
                    .show(calculatorCards, "Currency");
            //currency.updateExRate(); TODO: remove comment
        });
        currencyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        currencyButton.setBorder(null);
        currencyButton.addMouseListener(this);
        currencyButton.setPreferredSize(new Dimension(70, 70));

        //-----SetLayout-----

        frame.setLayout(new BorderLayout());


        calculatorButtons.setLayout(new BoxLayout(calculatorButtons, BoxLayout.PAGE_AXIS));
        calculatorButtons.setBackground(Color.decode("#222222"));
        calculatorButtons.add(standardPanButton);
        calculatorButtons.add(tempButton);
        calculatorButtons.add(currencyButton);

        //--------------------MAIN FRAME
        frame.add(calculatorButtons, BorderLayout.WEST);
        frame.add(calculatorCards, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(400, 500));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    private static boolean checkFiles() {
        JFrame popUp = new JFrame(); //TODO: update files to check
        String[] filesToCheck = {"calculator.java", "StandardCustomButton.java", "HistoryCustomButton.java",
                "GoogleSans-Regular.ttf", "Roboto-Regular.ttf", "images/standard.png", "images/standardHover2.png",
                "images/standardPressed.png", "images/temp.png", "images/tempHover2.png", "images/tempPressed.png",
                "images/history.png", "images/historyHover.png", "images/HistoryPressed.png"};
        File f;
        StringBuilder missingFiles = new StringBuilder();
        for (String file : filesToCheck) {
            f = new File("src", file);
            if (!f.exists()) {
                missingFiles.append(file).append("\n");
            }
        }
        if (missingFiles.length() != 0) {
            JOptionPane.showMessageDialog(popUp, "These files are missing: \n" + missingFiles,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public void mouseEntered(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        if (e.getSource() == standardPanButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/standardHover2.PNG")));
        else if (e.getSource() == tempButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/tempHover2.PNG")));
        else if (e.getSource() == currencyButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/currExHover.PNG")));
    }

    public void mouseExited(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        if (e.getSource() == standardPanButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/standard.PNG")));
        else if (e.getSource() == tempButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/temp.PNG")));
        else if (e.getSource() == currencyButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/currEx.PNG")));
    }

    public void mousePressed(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        if (e.getSource() == standardPanButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/standardPressed.PNG")));
        else if (e.getSource() == tempButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/tempPressed.PNG")));
        else if (e.getSource() == currencyButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/currExPressed.PNG")));
    }

    public void mouseReleased(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        if (e.getSource() == standardPanButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/standard.PNG")));
        else if (e.getSource() == tempButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/temp.PNG")));
        else if (e.getSource() == currencyButton)
            source.setIcon(new ImageIcon(getClass().getResource("/images/currEx.PNG")));

    }

    public static class StandardCalculator extends JPanel implements ActionListener, MouseListener {
        Calculator calc;
        JTextField inputOutput; //The main field where input/output is shown
        JTextField equation; //The field where the current equation is shown
        JTextField tempOutput; //The field where the output of the current equation is shown
        JButton historyButton;
        JPanel historyButtonsPanel;
        StandardCustomButton[] standardButtons;
        JDialog historyWindow;
        private boolean overwrite;
        private boolean opFlag;
        private boolean dotFlag;
        private boolean disabled;
        private boolean fromHistory;
        private int offset;

        StandardCalculator() {
            calc = new Calculator();
            inputOutput = new JTextField("0");
            equation = new JTextField();
            tempOutput = new JTextField();
            historyButton = new JButton();
            historyButtonsPanel = new JPanel();
            historyWindow = new JDialog();
            standardButtons = new StandardCustomButton[20];
            overwrite = true;
            opFlag = false;
            dotFlag = false;
            disabled = false;
            fromHistory = false;
            offset = 0;


            setLayout(new BorderLayout());

            historyWindow.setTitle("History");
            JScrollPane historyPane = new JScrollPane(historyButtonsPanel);
            JPanel standardTop = new JPanel();
            JPanel titleHistory = new JPanel(new BorderLayout());
            JPanel historyPanel = new JPanel(new BorderLayout());
            JPanel screenPanel = new JPanel();
            JPanel standardLabelPanel = new JPanel();
            JPanel standCalcButtons = new JPanel(new GridLayout(5, 4));
            String[] standardButtonName = {"Clear\nHistory", "C", "DEL", "/", "7", "8", "9", "*", "4", "5", "6", "-",
                    "1", "2", "3", "+", "+/-", "0", ".", "="};

            screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.PAGE_AXIS));
            standardTop.setLayout(new BoxLayout(standardTop, BoxLayout.PAGE_AXIS));
            historyButtonsPanel.setLayout(new BoxLayout(historyButtonsPanel, BoxLayout.PAGE_AXIS));

            //-----set Images-----

            historyButton.setIcon(new ImageIcon(getClass().getResource("/images/history.PNG")));

            //-----Buttons-----


            historyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            historyButton.addActionListener(this);
            historyButton.addMouseListener(this);
            historyButton.setBorder(null);

            createKeyBindings();

            //The "keypad"
            for (int i = 0; i < 20; i++) {
                standardButtons[i] = new StandardCustomButton(standardButtonName[i]);
                standardButtons[i].setName(standardButtonName[i]);
                standardButtons[i].addActionListener(this);
                standardButtons[i].setBorder(null);
                standCalcButtons.add(standardButtons[i]);
            }

            setName("Standard");
            add(standardTop, BorderLayout.PAGE_START);
            add(standCalcButtons);

            //-----Background Color-----

            setBackground(Color.decode("#2D2D2D"));
            screenPanel.setBackground(Color.decode("#2D2D2D"));
            standCalcButtons.setBackground(Color.decode("#2D2D2D"));
            standardTop.setBackground(Color.decode("#4F4F4F"));
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



            //-----------STANDARD CALCULATOR PANEL
            //TODO: MAKE HISTORYLABEL ANIMATION
            //BufferedImage myPicture = ImageIO.read(getClass().getResource("/images/standardLogo.PNG"));
            JLabel standardLabel = new JLabel(new ImageIcon(getClass().getResource("/images/standardLogo.PNG")));
            //myPicture = ImageIO.read(getClass().getResource("/images/historyLabel.PNG"));
            JLabel historyLabel = new JLabel(new ImageIcon(getClass().getResource("/images/historyLabel.PNG")));
            //add(picLabel);


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
            equation.setFont(new Font("Cambria", Font.PLAIN, 12));
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
            tempOutput.setFont(new Font("Cambria", Font.PLAIN, 12));


            JPanel displayTempPanel = new JPanel(new BorderLayout());
            displayTempPanel.add(equation);
            screenPanel.add(displayTempPanel);

            displayTempPanel = new JPanel(new BorderLayout());
            displayTempPanel.add(inputOutput);
            screenPanel.add(displayTempPanel);

            displayTempPanel = new JPanel(new BorderLayout());
            displayTempPanel.add(tempOutput);
            screenPanel.add(displayTempPanel);


            historyWindow.add(historyPane);
            historyWindow.setMinimumSize(new Dimension(400, 500));
            historyWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            historyWindow.setResizable(false);
        }

        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == historyButton)
                historyButton.setIcon(new ImageIcon(getClass().getResource("/images/historyHover.PNG")));
            else{
                ((JTextField)e.getSource()).setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }
        }

        public void mouseExited(MouseEvent e) {
            if (e.getSource() == historyButton)
                historyButton.setIcon(new ImageIcon(getClass().getResource("/images/history.PNG")));
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
            if (e.getSource() == historyButton)
                historyButton.setIcon(new ImageIcon(getClass().getResource("/images/historyPressed.PNG")));
        }

        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == historyButton)
                historyButton.setIcon(new ImageIcon(getClass().getResource("/images/history.PNG")));
        }

        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == historyButton){
                if (!historyWindow.isVisible())
                    historyWindow.setVisible(true);
                else
                    historyWindow.setVisible(false);
            }
            else {
                screenUpdate(((JComponent) e.getSource()).getName());
            }
        }

        private void screenUpdate(String name){
            if (disabled)
                printToInputOutput("0");
            StringBuilder input = new StringBuilder(inputOutput.getText());
            if (Character.digit(name.charAt(0), 10) >= 0) {
                if (fromHistory)
                    calc.removeLast();
                if ((input.length() == 1 && input.toString().equals("0")) || overwrite) {
                    input.setLength(0);
                    overwrite = false;
                    dotFlag = false;
                }
                if (input.length() < 19)
                    input.append(name);
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
                        if (disabled) {
                            disabled = false;
                        }
                        if (isDivisorZero(input)) {
                            printToInputOutput(tempOutput.getText());
                            tempOutput.setText("");
                            disabled = true;
                            calc.clearEquation();
                            return;
                        }
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
                        if (overwrite) {
                            input.setLength(0);
                            input.append("0");
                        }
                        if (!dotFlag) {
                            input.append(".");
                            printToInputOutput(input.toString());
                        }
                        dotFlag = true;
                        overwrite = false;
                        return;
                    case "=":
                        if (disabled){
                            printToInputOutput("0");
                            disabled = false;
                        }
                        else {
                            if (!calc.getCurrEquation().isEmpty()) {
                                if (isDivisorZero(input))
                                    disabled = true;
                                if (!tempOutput.getText().isEmpty())
                                    printToInputOutput(tempOutput.getText());
                            }
                            if (!disabled) {
                                offset = calc.addToHistory(input.toString(), fromHistory, isDivisorZero(input));
                                updateHistory(false);
                            }
                        }
                        calc.clearEquation();
                        equation.setText("");
                        tempOutput.setText("");
                        overwrite = true;
                        opFlag = false;
                        dotFlag = false;
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

        private boolean isDivisorZero(StringBuilder input) {
            if (calc.isDivideByZero(input)) {
                calc.clearPrevAnswer();
                tempOutput.setText("Cannot divide by 0");
                return true;
            }
            return false;
        }

        private void updateHistory(boolean clear) {
            //TODO: add No history message
            if (clear)
                historyButtonsPanel.removeAll();
            else {
                int size = calc.getResults().size();
                HistoryCustomButton history = new HistoryCustomButton(calc.getEquations().getLast() + " =",
                        calc.getResults().getLast()); //create a button based on the last equation and result
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
                if (offset > 0) //remove the last button in the list
                    historyButtonsPanel.remove(historyButtonsPanel.getComponentCount() - 1);
            }
            historyWindow.repaint();
            historyWindow.revalidate();
        }

        private void createKeyBindings() {
            addKeyBinding(KeyEvent.VK_0, 0, "0");
            addKeyBinding(KeyEvent.VK_1, 0, "1");
            addKeyBinding(KeyEvent.VK_2, 0, "2");
            addKeyBinding(KeyEvent.VK_3, 0, "3");
            addKeyBinding(KeyEvent.VK_4, 0, "4");
            addKeyBinding(KeyEvent.VK_5, 0, "5");
            addKeyBinding(KeyEvent.VK_6, 0, "6");
            addKeyBinding(KeyEvent.VK_7, 0, "7");
            addKeyBinding(KeyEvent.VK_8, 0, "8");
            addKeyBinding(KeyEvent.VK_9, 0, "9");
            addKeyBinding(KeyEvent.VK_NUMPAD0, 0, "0");
            addKeyBinding(KeyEvent.VK_NUMPAD1, 0, "1");
            addKeyBinding(KeyEvent.VK_NUMPAD2, 0, "2");
            addKeyBinding(KeyEvent.VK_NUMPAD3, 0, "3");
            addKeyBinding(KeyEvent.VK_NUMPAD4, 0, "4");
            addKeyBinding(KeyEvent.VK_NUMPAD5, 0, "5");
            addKeyBinding(KeyEvent.VK_NUMPAD6, 0, "6");
            addKeyBinding(KeyEvent.VK_NUMPAD7, 0, "7");
            addKeyBinding(KeyEvent.VK_NUMPAD8, 0, "8");
            addKeyBinding(KeyEvent.VK_NUMPAD9, 0, "9");
            addKeyBinding(KeyEvent.VK_EQUALS, KeyEvent.SHIFT_DOWN_MASK, "+");
            addKeyBinding(KeyEvent.VK_ADD, 0, "+");
            addKeyBinding(KeyEvent.VK_UNDERSCORE, KeyEvent.SHIFT_DOWN_MASK, "-");
            addKeyBinding(KeyEvent.VK_SUBTRACT, 0, "-");
            addKeyBinding(KeyEvent.VK_8, KeyEvent.SHIFT_DOWN_MASK, "*");
            addKeyBinding(KeyEvent.VK_MULTIPLY, 0, "*");
            addKeyBinding(KeyEvent.VK_SLASH, 0, "/");
            addKeyBinding(KeyEvent.VK_DIVIDE, 0, "/");
            addKeyBinding(KeyEvent.VK_BACK_SPACE, 0, "DEL");
            addKeyBinding(KeyEvent.VK_PERIOD, 0, ".");
            addKeyBinding(KeyEvent.VK_ENTER, 0, "=");

        }

        private void addKeyBinding(int keyCode, int mod, String id) {
            InputMap im = inputOutput.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = inputOutput.getActionMap();
            im.put(KeyStroke.getKeyStroke(keyCode, mod), id);
            am.put(id, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    screenUpdate(id);
                }
            });
        }

        private void printToInputOutput(String text) { //resize the font for the output based on the length of the input
            inputOutput.setFont(new Font("Cambria", Font.PLAIN,40));
            while (inputOutput.getGraphics().getFontMetrics().stringWidth(text) > inputOutput.getWidth() - 10) {
                Font font = inputOutput.getFont();
                font = font.deriveFont((float) (font.getSize() - 1));
                inputOutput.setFont(font);
            }
            inputOutput.setText(text);
        }

    }

    public static class Temperature extends JPanel implements ActionListener, ItemListener {
        ImageIcon celsiusIcon = new ImageIcon(getClass().getResource("/images/celsius.PNG"));
        ImageIcon fahrenheitIcon = new ImageIcon(getClass().getResource("/images/fahrenheit.PNG"));
        ImageIcon kelvinIcon = new ImageIcon(getClass().getResource("/images/kelvin.PNG"));
        ImageIcon celsiusIconPressed = new ImageIcon(getClass().getResource("/images/celsiusPressed.PNG"));
        ImageIcon fahrenheitIconPressed = new ImageIcon(getClass().getResource("/images/fahrenheitPressed.PNG"));
        ImageIcon kelvinIconPressed = new ImageIcon(getClass().getResource("/images/kelvinPressed.PNG"));
        ImageIcon celsiusIconHover = new ImageIcon(getClass().getResource("/images/celsiusHover.PNG"));
        ImageIcon fahrenheitIconHover = new ImageIcon(getClass().getResource("/images/fahrenheitHover.PNG"));
        ImageIcon kelvinIconHover = new ImageIcon(getClass().getResource("/images/kelvinHover.PNG"));
        JLabel toConvertLabel;
        JLabel convertToLabel;
        Calculator calc;

        Font googleFont;
        JTextField toConvertField;
        JTextField convertToField;

        TempCustomRadioButton toConvertC;
        TempCustomRadioButton toConvertF;
        TempCustomRadioButton toConvertK;
        TempCustomRadioButton convertToC;
        TempCustomRadioButton convertToF;
        TempCustomRadioButton convertToK;

        Buttons buttons;

        private boolean overwrite = true;
        Temperature() {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            calc = new Calculator();

            //TODO: add Title
            try {
                googleFont = Font.createFont(Font.TRUETYPE_FONT,
                        new File(getClass().getResource("GoogleSans-Regular.ttf").toURI()))
                        .deriveFont(Font.PLAIN, 15);
            } catch (Exception e) {
                e.printStackTrace();
            }

            JPanel conversionPanel = new JPanel();
            JPanel toConvertPanel = new JPanel();
            JPanel convertToPanel = new JPanel();
            conversionPanel.setLayout(new BoxLayout(conversionPanel, BoxLayout.PAGE_AXIS));
            toConvertPanel.setLayout(new BoxLayout(toConvertPanel, BoxLayout.PAGE_AXIS));
            convertToPanel.setLayout(new BoxLayout(convertToPanel, BoxLayout.PAGE_AXIS));


            //FIXME: layout
            JPanel buttonPanel1 = new JPanel(new GridBagLayout());
            toConvertLabel = new JLabel("Choose a temperature unit");
            toConvertLabel.setFont(googleFont);
            toConvertLabel.setAlignmentX(CENTER_ALIGNMENT);
            toConvertField = new JTextField("0");
            toConvertField.setEditable(false);
            toConvertField.setPreferredSize(new Dimension(314, 40));
            toConvertField.setMinimumSize(new Dimension(314, 25));
            toConvertField.setMaximumSize(new Dimension(toConvertField.getMaximumSize().width, 40));
            toConvertField.setHorizontalAlignment(SwingConstants.CENTER);
            toConvertField.setFont(new Font("Cambria", Font.PLAIN, 20));

            JPanel buttonPanel2 = new JPanel(new GridBagLayout());
            convertToLabel = new JLabel("Choose a temperature unit");
            convertToLabel.setFont(googleFont);
            convertToLabel.setAlignmentX(CENTER_ALIGNMENT);
            convertToField = new JTextField("0");
            convertToField.setEditable(false);
            convertToField.setHorizontalAlignment(SwingConstants.CENTER);
            convertToField.setPreferredSize(new Dimension(314, 40));
            convertToField.setMinimumSize(new Dimension(314, 25));
            convertToField.setMaximumSize(new Dimension(convertToField.getMaximumSize().width, 40));
            convertToField.setFont(new Font("Cambria", Font.PLAIN, 20));

            toConvertPanel.add(buttonPanel1);
            toConvertPanel.add(toConvertLabel);
            toConvertPanel.add(toConvertField);

            convertToPanel.add(buttonPanel2);
            convertToPanel.add(convertToLabel);
            convertToPanel.add(convertToField);


            //-----To Convert------
            toConvertC = new TempCustomRadioButton(celsiusIcon, celsiusIconPressed, celsiusIconHover);
            toConvertF = new TempCustomRadioButton(fahrenheitIcon, fahrenheitIconPressed, fahrenheitIconHover);
            toConvertK = new TempCustomRadioButton(kelvinIcon, kelvinIconPressed, kelvinIconHover);





            addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent evt) {
                    Dimension convSize = new Dimension(Integer.MAX_VALUE,
                            (getHeight() - buttons.getHeight()) /2);
                    Dimension buttonSize = new Dimension(70 + ((getHeight() - 461) / 10),
                            70 + ((getHeight() - 461) / 10));
                    Dimension spacingSize = new Dimension(10 + ((getWidth() - 461) / 40), 0);
                    Font labelFont = googleFont.deriveFont((float)(15 + ((getHeight() - 461) / 40)));
                    Font IOFont = googleFont.deriveFont((float)(20 + ((getHeight() - 461) / 40)));
                    buttonPanel1.getComponent(1).setPreferredSize(spacingSize);
                    buttonPanel1.getComponent(3).setPreferredSize(spacingSize);
                    buttonPanel2.getComponent(1).setPreferredSize(spacingSize);
                    buttonPanel2.getComponent(3).setPreferredSize(spacingSize);
                    toConvertPanel.setSize(convSize);
                    toConvertPanel.setMaximumSize(convSize);
                    convertToPanel.setSize(convSize);
                    convertToPanel.setMaximumSize(convSize);
                    toConvertLabel.setFont(labelFont);
                    toConvertField.setFont(IOFont);
                    toConvertLabel.setPreferredSize(new Dimension(getWidth(), 40 + ((getHeight() - 461) / 40)));
                    convertToLabel.setFont(labelFont);
                    convertToField.setFont(IOFont);
                    convertToLabel.setPreferredSize(new Dimension(getWidth(), 40 + ((getHeight() - 461) / 40)));
                    buttonReize(buttonSize);
                    repaint();
                    revalidate();
                }
                });


            ButtonGroup toConvertBG = new ButtonGroup();
            toConvertBG.add(toConvertC);
            toConvertBG.add(toConvertF);
            toConvertBG.add(toConvertK);

            buttonPanel1.add(toConvertC);
            buttonPanel1.add(Box.createRigidArea(new Dimension(10,0)));
            buttonPanel1.add(toConvertF);
            buttonPanel1.add(Box.createRigidArea(new Dimension(10,0)));
            buttonPanel1.add(toConvertK);

            //-----Convert To------
            convertToC = new TempCustomRadioButton(celsiusIcon, celsiusIconPressed, celsiusIconHover);
            convertToF = new TempCustomRadioButton(fahrenheitIcon, fahrenheitIconPressed, fahrenheitIconHover);
            convertToK = new TempCustomRadioButton(kelvinIcon, kelvinIconPressed, kelvinIconHover);

            //-----ItemListeners-----
            toConvertC.addItemListener(this);
            toConvertF.addItemListener(this);
            toConvertK.addItemListener(this);
            convertToC.addItemListener(this);
            convertToF.addItemListener(this);
            convertToK.addItemListener(this);

            ButtonGroup convertToBG = new ButtonGroup();
            convertToBG.add(convertToC);
            convertToBG.add(convertToF);
            convertToBG.add(convertToK);


            buttonPanel2.add(convertToC);
            buttonPanel2.add(Box.createRigidArea(new Dimension(10,0)));
            buttonPanel2.add(convertToF);
            buttonPanel2.add(Box.createRigidArea(new Dimension(10,0)));
            buttonPanel2.add(convertToK);


            conversionPanel.add(toConvertPanel);
            conversionPanel.add(convertToPanel);
            add(conversionPanel);

            conversionPanel.setPreferredSize(new Dimension(314, 310));
            buttons = new Buttons(toConvertField, this);
            add(buttons);
            buttons.setMinimumSize(new Dimension(314, 151));
            buttons.setPreferredSize(new Dimension(314, 151));
            buttons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        }

        private void buttonReize(Dimension buttonSize) {

            toConvertC.setPreferredSize(buttonSize);
            toConvertF.setPreferredSize(buttonSize);
            toConvertK.setPreferredSize(buttonSize);
            convertToC.setPreferredSize(buttonSize);
            convertToF.setPreferredSize(buttonSize);
            convertToK.setPreferredSize(buttonSize);

            toConvertC.update();
            toConvertF.update();
            toConvertK.update();
            convertToC.update();
            convertToF.update();
            convertToK.update();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionName = ((JButton)e.getSource()).getName();
            if (overwrite && !actionName.equals("+/-") && !actionName.equals("."))
                toConvertField.setText("");
            if (overwrite)
                overwrite = false;
            buttonAction(actionName, toConvertField);
            if(toConvertField.getText().equals("0"))
                overwrite = true;
            else if (!toConvertField.getText().contains("."))
                formatter(toConvertField.getText());
            String defaultText = "Choose a temperature unit";
            if (!toConvertLabel.getText().equals(defaultText) && !convertToLabel.getText().equals(defaultText))
                convertToField.setText(
                        calc.calculateTemp(
                                toConvertLabel.getText(),
                                convertToLabel.getText(),
                                toConvertField.getText()));
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == toConvertC) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    toConvertLabel.setText("Celsius");
            }
            else if (e.getSource() == toConvertF) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    toConvertLabel.setText("Fahrenheit");
            }
            else if (e.getSource() == toConvertK) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    toConvertLabel.setText("Kelvin");
            }
            else if (e.getSource() == convertToC) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    convertToLabel.setText("Celsius");
            }
            else if (e.getSource() == convertToF) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    convertToLabel.setText("Fahrenheit");
            }
            else if (e.getSource() == convertToK) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    convertToLabel.setText("Kelvin");
            }
            String defaultText = "Choose a temperature unit";
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (!toConvertLabel.getText().equals(defaultText) && !convertToLabel.getText().equals(defaultText))
                    convertToField.setText(
                            calc.calculateTemp(
                                    toConvertLabel.getText(),
                                    convertToLabel.getText(),
                                    toConvertField.getText()));
            }
        }

        private void formatter(String numbers) {
            BigDecimal in = new BigDecimal(numbers.replaceAll(",", ""));
            DecimalFormat df = new DecimalFormat("#,###.#");
            toConvertField.setText(df.format(in));
        }
    }

    private static void buttonAction(String actionName, JTextField textField){
        switch (actionName){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                if (textField.getText().length() <= 20)
                    textField.setText(textField.getText() + actionName);
                break;
            case "C":
                textField.setText("0");
            case "DEL":
                if (textField.getText().length() > 1)
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                else
                    textField.setText("0");
                break;
            case ".":
                if(!textField.getText().contains("."))
                    textField.setText(textField.getText() + ".");
                break;
            case "+/-":
                if (textField.getText().charAt(0) == '-')
                    textField.setText(textField.getText().substring(1));
                else
                    textField.setText("-" + textField.getText());
                break;
        }
    }


    public static class Currency extends JPanel implements ActionListener, ItemListener{
        Calculator calc;
        HttpClient client = HttpClient.newHttpClient();
        final String base_link = "http://data.fixer.io/api/";
        final String access_key = "?access_key=c320677a465dffd061ec821f724a37b3";
        JSONObject symbols;
        JSONObject currencyRates;
        JButton update;
        Buttons buttons;
        JComboBox<String> toConvertBox;
        JComboBox<String> convertToBox;
        JLabel exRateLabel;
        JLabel dateUpdated;
        JTextField toConvertField;
        JTextField convertToField;
        boolean overwrite = true;
        Currency() {
            setLayout(new GridBagLayout());
            JPanel conversionPanel = new JPanel();
            //TODO: add Title
            calc = new Calculator();

            JPanel toConvertPanel = new JPanel();
            toConvertPanel.setLayout(new BoxLayout(toConvertPanel, BoxLayout.PAGE_AXIS));

            JPanel toConvertComboPanel = new JPanel(new GridBagLayout());
            toConvertBox = new JComboBox<>();
            //toConvertBox.setMaximumSize(new Dimension(250, toConvertBox.getHeight()));
            toConvertBox.addItemListener(this);

            toConvertComboPanel.add(toConvertBox);

            //toConvertBox.setPreferredSize(new Dimension(200, 25));
            //toConvertBox.setMinimumSize(new Dimension(200, 25));



            toConvertField = new JTextField("0");
            toConvertField.setEditable(false);
            toConvertField.setHorizontalAlignment(SwingConstants.CENTER);
            toConvertField.setPreferredSize(new Dimension(314, 40));
            toConvertField.setMinimumSize(new Dimension(314, 25));
            toConvertField.setMaximumSize(new Dimension(toConvertField.getMaximumSize().width, 40));
            //convertToField.setFont(new Font("Cambria", Font.PLAIN, 20));


            toConvertPanel.add(toConvertComboPanel);
            toConvertPanel.add(toConvertField);



            JPanel convertToPanel = new JPanel();
            convertToPanel.setLayout(new BoxLayout(convertToPanel, BoxLayout.PAGE_AXIS));

            JPanel convertToComboPanel = new JPanel(new GridBagLayout());
            convertToBox = new JComboBox<>();
            convertToBox.addItemListener(this);

            convertToComboPanel.add(convertToBox);

            convertToField = new JTextField("0");
            convertToField.setEditable(false);
            convertToField.setHorizontalAlignment(SwingConstants.CENTER);
            convertToField.setPreferredSize(new Dimension(314, 40));
            convertToField.setMinimumSize(new Dimension(314, 25));
            convertToField.setMaximumSize(new Dimension(convertToField.getMaximumSize().width, 40));
            //convertToField.setFont(new Font("Cambria", Font.PLAIN, 20));

            convertToPanel.add(convertToComboPanel);
            convertToPanel.add(convertToField);

            JPanel ratePanel = new JPanel(new GridBagLayout());

            GridBagConstraints c = new GridBagConstraints();
            exRateLabel = new JLabel("exchange rate");
            dateUpdated = new JLabel("date updated");
            update = new JButton("Update rates");
            update.addActionListener(this);
            c.weightx = 1;
            c.gridwidth = 2;
            ratePanel.add(exRateLabel, c);
            c.gridy = 1;
            c.gridwidth = 2;
            ratePanel.add(dateUpdated, c);
            c.weightx = 0;
            c.gridx = 2;
            c.gridy = 0;
            c.gridheight = 2;
            ratePanel.add(update, c);



            buttons = new Buttons(toConvertField, this);
            c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.5;
            c.weighty = 0.5;
            c.gridy = 1;
            add(toConvertPanel, c);
            c.gridy = 2;
            add(convertToPanel, c);
            c.gridy = 3;
            c.weighty = 0;
            add(ratePanel, c);
            c.gridy = 4;
            c.weighty = 0.2;
            add(buttons, c);

            buttons.setMinimumSize(new Dimension(314, 151));
            buttons.setPreferredSize(new Dimension(314, 151));
            buttons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
            //setBackground(Color.green);
        }

        private void updateCurrencyList() {
            symbols = new JSONObject(getUpdatedJSON("symbols")).getJSONObject("symbols");
            SortedSet<String> countrySet = new TreeSet<>();

            Iterator<String> keys = symbols.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                countrySet.add(symbols.getString(key) + " - " + key);
            }
            Object selected1 = toConvertBox.getSelectedItem();
            Object selected2 = convertToBox.getSelectedItem();
            toConvertBox.removeAllItems();
            convertToBox.removeAllItems();
            for (String item : countrySet) {
                toConvertBox.addItem(item);
                convertToBox.addItem(item);
            }
            if (selected1 != null) {
                toConvertBox.setSelectedItem(selected1);
                convertToBox.setSelectedItem(selected2);
            }
        }

        private void updateExRate() {
            currencyRates = new JSONObject(getUpdatedJSON("latest"));
            //TODO: validate using "success : true/false"
            //TODO: add check if within the same hour
            dateUpdated.setText("Updated: " + currencyRates.getString("date"));//TODO: add time
        }


        private String getUpdatedJSON(String key) {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(base_link + key + access_key)).build();
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == update) {
                updateExRate();
                updateCurrencyList();
            } else {
                String actionName = ((JButton) e.getSource()).getName();
                if (overwrite && !actionName.equals("+/-") && !actionName.equals("."))
                    toConvertField.setText("");
                if (overwrite)
                    overwrite = false;
                buttonAction(actionName, toConvertField);
                if (toConvertField.getText().equals("0"))
                    overwrite = true;
                else if (!toConvertField.getText().contains("."))
                    formatter(toConvertField.getText());
                if(Double.parseDouble(toConvertField.getText()) > 0)
                    convertToField.setText(convert(toConvertField.getText(),15));
            }
        }
        private void formatter(String numbers) {
            BigDecimal in = new BigDecimal(numbers.replaceAll(",", ""));
            DecimalFormat df = new DecimalFormat("#,###.#");
            toConvertField.setText(df.format(in));
        }
        String convert(String amount, int decimal) {
            JSONObject rates = currencyRates.getJSONObject("rates");

            if (!getFirstCurr().equals("") && !getSecondCurr().equals("")) {
                BigDecimal bd = new BigDecimal(calc.calculateExRate(
                        rates.getDouble(getFirstCurr()),
                        rates.getDouble(getSecondCurr()), amount))
                        .setScale(decimal, RoundingMode.HALF_UP);
                return bd.toString();
            }
            return "0";
        }
        String getFirstCurr() {
            String selected1 = (String) toConvertBox.getSelectedItem();
            if (selected1 != null)
                return selected1.substring(selected1.length() - 3);
            return "";
        }
        String getSecondCurr() {
            String selected2 = (String) convertToBox.getSelectedItem();
            if (selected2 != null)
                return selected2.substring(selected2.length() - 3);
            return "";
        }
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (!getFirstCurr().equals("") && !getSecondCurr().equals(""))
                    exRateLabel.setText("1 " + getFirstCurr() + " = " + convert("1", 6) + " " + getSecondCurr());
                if(Double.parseDouble(toConvertField.getText()) > 0)
                    convertToField.setText(convert(toConvertField.getText(),15));
            }
        }
    }

    public static class Buttons extends JPanel {
        StandardCustomButton[] numbersOnly;
        ActionListener actionListener;

        Buttons(JTextField textField, ActionListener actionListener) {
            this.actionListener = actionListener;
            setLayout(new GridLayout(5, 3));
            setBackground(Color.decode("#00ADEF"));
            numbersOnly = new StandardCustomButton[14];
            String[] buttonNames = {"C", "DEL", "7", "8", "9", "4", "5", "6", "1", "2", "3", "+/-", "0", "."};

            add(Box.createRigidArea(new Dimension(73, 62)));
            for (int i = 0; i < 14; i++) {
                numbersOnly[i] = new StandardCustomButton(buttonNames[i]);
                numbersOnly[i].setName(buttonNames[i]);
                numbersOnly[i].addActionListener(actionListener);
                numbersOnly[i].setBorder(null);
                add(numbersOnly[i]);
            }

            addKeyBinding(textField, KeyEvent.VK_PERIOD, ".");
            addKeyBinding(textField, KeyEvent.VK_BACK_SPACE, "DEL");
            addKeyBinding(textField, KeyEvent.VK_0, "0");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD0, "0");
            addKeyBinding(textField, KeyEvent.VK_1, "1");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD1, "1");
            addKeyBinding(textField, KeyEvent.VK_2, "2");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD2, "2");
            addKeyBinding(textField, KeyEvent.VK_3, "3");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD3, "3");
            addKeyBinding(textField, KeyEvent.VK_4, "4");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD4, "4");
            addKeyBinding(textField, KeyEvent.VK_5, "5");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD5, "5");
            addKeyBinding(textField, KeyEvent.VK_6, "6");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD6, "6");
            addKeyBinding(textField, KeyEvent.VK_7, "7");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD7, "7");
            addKeyBinding(textField, KeyEvent.VK_8, "8");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD8, "8");
            addKeyBinding(textField, KeyEvent.VK_9, "9");
            addKeyBinding(textField, KeyEvent.VK_NUMPAD9, "9");
        }

        private void addKeyBinding(JTextField textField, int keyCode, String id) {
            InputMap im = textField.getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = textField.getActionMap();
            im.put(KeyStroke.getKeyStroke(keyCode, 0), id);
            JButton button = new JButton();
            button.setName(id);
            am.put(id, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    e.setSource(button);
                    actionListener.actionPerformed(e);
                }
            });
        }
    }
    public static void main(String[] args) {
        if (checkFiles())
            new CalculatorGUI();
    }
}