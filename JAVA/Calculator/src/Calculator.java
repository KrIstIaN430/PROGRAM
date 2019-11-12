import javax.print.attribute.EnumSyntax;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.io.File;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.math.BigDecimal;

class Calculator {
    private LinkedList<String> equations = new LinkedList<>();
    private LinkedList<String> results = new LinkedList<>();
    private LinkedList<String> currEquation = new LinkedList<>();
    private BigDecimal prevAnswer = new BigDecimal(0);
    private Iterator iterCurr;
    private DecimalFormat df = new DecimalFormat();
    private int offset = 0;
    Calculator(){

    }
    String calculate(String input, boolean same) {
        Stack<BigDecimal> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();
        if(same)
            addToEquation(false, prevAnswer.toPlainString());
        else
            addToEquation(false, input);
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

    LinkedList<String> getEquations(){
        return equations;
    }
    LinkedList<String> getResults(){
        return results;
    }
    void clearEqRes(){
        equations.clear();
        results.clear();
        offset = 0;
    }
    String getPrevAnswer(){
        return prevAnswer.toPlainString();
    }

    void clearEquation(){
        currEquation.clear();
    }

    void removeLast(){
        currEquation.removeLast();
    }
    void setCurrEquation(String currEquation){
        this.currEquation.clear();
        for(String x: currEquation.split(" "))
            addToEquation(false, x);
    }
    String getCurrEquation(){
        StringBuilder current = new StringBuilder();
        iterCurr = currEquation.iterator();
        while(iterCurr.hasNext()){
            String x = (String) iterCurr.next();
            if (isOperator(x))
                current.append(x);
            else
                current.append(formatter(x));
            current.append(" ");
        }
        return current.toString();
    }

    int addToHistory(String input, boolean fromHistory){
        results.addLast(formatter(prevAnswer.toPlainString()));
        if(fromHistory)
            equations.addLast(getCurrEquation());
        else
            equations.addLast(getCurrEquation() + input);
        if(results.size() > 20) {
            equations.removeFirst();
            results.removeFirst();
            offset++;
        }
        clearEquation();
        return offset;
    }
    boolean isZero(StringBuilder input){
        BigDecimal in = new BigDecimal(input.toString().replaceAll(",", ""));
        return in.compareTo(BigDecimal.ZERO) == 0;
    }
    boolean isDividebyZero(StringBuilder input){
        BigDecimal in = new BigDecimal(input.toString().replaceAll(",", ""));
        if (currEquation.isEmpty())
            return false;
        return currEquation.getLast().equals("/") && in.compareTo(BigDecimal.ZERO) == 0;
    }
    private boolean isOperator(String x){
        return x.equals("*") || x.equals("/") || x.equals("+") || x.equals("-");
    }


    String formatter(String input) {
        BigDecimal in = new BigDecimal(input.replaceAll(",", ""));
        if (in.toString().length()> 11)
            df.applyPattern("0.00000000E0");
        else
            df.applyPattern("#,###.###############");
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
}