import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

class Calculator {
    private LinkedList<String> equations = new LinkedList<>();
    private LinkedList<String> results = new LinkedList<>();
    private LinkedList<String> currEquation = new LinkedList<>();
    private BigDecimal prevAnswer = new BigDecimal(0);
    private Iterator iterCurr;
    private DecimalFormat df = new DecimalFormat();
    private int offset = 0;

    Calculator() {

    }

    String calculate() {
        Stack<BigDecimal> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        iterCurr = currEquation.iterator();
        while (iterCurr.hasNext()) {
            String x = (String) iterCurr.next();
            if (x.equals("*") || x.equals("/") || x.equals("+") || x.equals("-"))
                if (operators.empty())
                    operators.push(x);
                else {
                    if (!hasPrecedence(operators.peek(), x)) {
                        numbers.push(applyOp(numbers.pop(), numbers.pop(), operators.pop()));
                        operators.push(x);
                    } else
                        operators.push(x);
                }
            else if (!x.equals("")) {
                numbers.push(new BigDecimal(x));
            }
        }
        while (!operators.empty())
            numbers.push(applyOp(numbers.pop(), numbers.pop(), operators.pop()));
        //Last in the equation is the text in the inputOutput field which is most of the time temporary
        currEquation.removeLast();
        prevAnswer = new BigDecimal(numbers.pop().toString())
                .stripTrailingZeros();
        return formatter(new BigDecimal(prevAnswer.toString()).toPlainString());
    }

    private BigDecimal applyOp(BigDecimal secondNum, BigDecimal firstNum, String operator){
        firstNum = firstNum.setScale(50, RoundingMode.HALF_UP);
        secondNum = secondNum.setScale(50, RoundingMode.HALF_UP);
        switch (operator) {
            case "*":
                return firstNum.multiply(secondNum);
            case "/":
                return firstNum.divide(secondNum, RoundingMode.HALF_UP);
            case "+":
                return firstNum.add(secondNum);
            default:
                return firstNum.subtract(secondNum);
        }
    }

    private boolean hasPrecedence(String stackOp, String listOp){
        return ((stackOp.equals("+") || stackOp.equals("-")) && (listOp.equals("*") || listOp.equals("/")));
    }

    void addToEquation(boolean op, String name){
        if (op)
            currEquation.removeLast();
        currEquation.addLast(name.replaceAll(",", ""));
    }

    LinkedList<String> getEquations() {
        return equations;
    }

    LinkedList<String> getResults() {
        return results;
    }

    void clearEqRes(){
        equations.clear();
        results.clear();
        offset = 0;
    }

    String getPrevAnswer() {
        return prevAnswer.toPlainString();
    }

    void clearEquation() {
        currEquation.clear();
    }

    void clearPrevAnswer() {
        prevAnswer = new BigDecimal(0);
    }

    void removeLast() {
        currEquation.removeLast();
    }

    String getCurrEquation() {
        StringBuilder current = new StringBuilder();
        iterCurr = currEquation.iterator();
        while(iterCurr.hasNext()){
            String x = (String) iterCurr.next();
            if(!x.equals("")) {
                current.append(" ");
                if (isOperator(x))
                    current.append(x);
                else
                    current.append(formatter(x));
            }
        }
        current.append(" ");
        return current.toString();
    }

    void setCurrEquation(String currEquation) {
        this.currEquation.clear();
        for (String x : currEquation.split(" "))
            addToEquation(false, x);
    }

    int addToHistory(String input, boolean fromHistory, boolean divideByZero) {
        if (divideByZero) {
            equations.addLast(getCurrEquation() + input);
            results.addLast("Cannot divide by 0");
        } else if (fromHistory) {
            equations.addLast(getCurrEquation());
            results.addLast(calculate());
        }
        else {
            equations.addLast(getCurrEquation() + input);
            results.addLast(formatter(prevAnswer.toPlainString()));
        }
        if (results.size() > 20) {
            equations.removeFirst();
            results.removeFirst();
            offset++;
        }
        return offset;
    }

    boolean isDivideByZero(StringBuilder input) {
        BigDecimal in = new BigDecimal(input.toString().replaceAll(",", ""));
        if (currEquation.isEmpty())
            return false;
        return currEquation.getLast().equals("/") && in.compareTo(BigDecimal.ZERO) == 0;
    }

    private boolean isOperator(String x) {
        return x.equals("*") || x.equals("/") || x.equals("+") || x.equals("-");
    }


    String formatter(String input) {
        BigDecimal in = new BigDecimal(input.replaceAll(",", ""));

        if (in.toString().length() > 15)
            df.applyPattern("0.00000000E0");
        else
            df.applyPattern("#,###.###############");
        return df.format(in);
    }

    String formatter(double input) {
        BigDecimal in = new BigDecimal(input);
        df.applyPattern("#,###.#");
        return df.format(in);
    }

    String negate(StringBuilder input, boolean same){
        BigDecimal in;
        if(same) {
            prevAnswer = prevAnswer.negate();
            return formatter(prevAnswer.toString());
        }
        else
            in = new BigDecimal(input.toString().replaceAll(",", ""));
        return formatter(in.negate().toString());
    }
    String calculateTemp(String toConvert, String convertTo, String valueToConvert) {
        double valueToCompute = Double.parseDouble(valueToConvert.replaceAll(",",""));
        double computedValue = valueToCompute;
        if (toConvert.equals(convertTo))
            return valueToConvert;
        if (toConvert.equals("Celsius")){
            if (convertTo.equals("Fahrenheit"))
                computedValue = (valueToCompute * 9/5) + 32;
            else if (convertTo.equals("Kelvin"))
                computedValue = valueToCompute + 273.15;
        }
        else if (toConvert.equals("Fahrenheit")) {
            computedValue = (valueToCompute - 32) * 5 / 9;
            if (convertTo.equals("Kelvin"))
                computedValue += 273.15;
            return String.valueOf(computedValue);
        }
        else if (toConvert.equals("Kelvin")) {
            computedValue -= 273.15;
            if (convertTo.equals("Fahrenheit"))
                computedValue = (computedValue * 9/5) + 32;
        }
        return formatter(computedValue);
    }

    double calculateExRate(Double toConvert, Double convertTo, String valueToConvert) {
        double valueToCompute = Double.parseDouble(valueToConvert.replaceAll(",",""));
        return (convertTo / toConvert) * valueToCompute;
    }
}